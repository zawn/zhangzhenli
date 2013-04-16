/*
 * Copyright 2013 ZhangZhenli <zhangzhenli@live.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huinfo.auth.as.issuer.domain;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.dao.ResourceOwnInfoMapper;
import com.huinfo.auth.as.model.ResourceOwnInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthRuntimeException;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class SinaWeibo extends TrustedValidator {

    private static final String verifyURL = "https://api.weibo.com/oauth2/get_token_info";
    private static final String userInfoURL = "https://api.weibo.com/2/users/show.json";

    public SinaWeibo(String trustedDomain, String trustedToken, String trustedUid, Long clientID) {
        super(trustedDomain, trustedToken, trustedUid, clientID);
    }

    @Override
    public void validatorFromDomain(Map<String, Object> at)
            throws OAuthProblemException, OAuthRuntimeException {
        try {
            Map<String, Object> attrib = new HashMap<String, Object>();
            attrib.put("access_token", trustedToken);
            String queryString = OAuthUtils.format(attrib.entrySet(), "UTF-8");
            Map<String, String> header = new HashMap<String, String>(1);
            header.put("Content-Type", "application/x-www-form-urlencoded");
            String httpContent = getHttpContent(verifyURL, header, queryString);
            if (httpContent != null) {
                logger.info(httpContent);
                JSONObject jsonObject = new JSONObject(httpContent);
                long jsUID = jsonObject.getLong("uid");
                String jsAppKey = jsonObject.getString("appkey");
                long jsCreateAt = jsonObject.getLong("create_at");
                long jsExpireIn = jsonObject.getLong("expire_in");
                long time = new Date().getTime();
                time = time - jsCreateAt - jsExpireIn;
                if (!String.valueOf(jsUID).equals(trustedUid)) {
                    logger.info("Returned by the server UID=" + jsUID + ",but the client is " + trustedUid);
                    throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT, "The authorized user ID does not match");
                }
                if (!at.get("AppKey").equals(jsAppKey)) {
                    throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT, "The authorized Client ID does not match");
                }
                if (time < 1) {
                    throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT, "The authorized token has expired");
                }
                saveUserInfo(jsonObject);
            } else {
                throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT);
            }
        } catch (JSONException ex) {
            logger.error(null, ex);
            throw new OAuthRuntimeException(ex);
        } catch (MalformedURLException ex) {
            logger.error(null, ex);
            throw new OAuthRuntimeException(ex);
        } catch (IOException ex) {
            logger.error(null, ex);
            throw new OAuthRuntimeException(ex);
        }
    }

    private void saveUserInfo(JSONObject jsonObject) {
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            ResourceOwnInfoMapper mapper = sqlSession.getMapper(ResourceOwnInfoMapper.class);
            ResourceOwnInfo owninfo = mapper.selectByUserID(trustedUid);
            if (owninfo != null) {
                return;
            } else {
                jsonObject.put(OAuth.OAUTH_TRUSTED_TOKEN, trustedToken);
                jsonObject.put(OAuth.OAUTH_TRUSTED_DOMAIN, trustedDomain);
                jsonObject.put("TRUSTED_TIME", (new Date()).getTime());
                mapper.insert(getUserInfoFromDomain(jsonObject));
                sqlSession.commit();
            }
        } catch (JSONException ex) {
            logger.error(null, ex);
            throw new OAuthRuntimeException(ex);
        } finally {
            sqlSession.close();
        }
    }

    private ResourceOwnInfo getUserInfoFromDomain(JSONObject jsonObject) {
        ResourceOwnInfo info = new ResourceOwnInfo();
        info.setDomain(trustedDomain);
        info.setUserid(trustedUid);
        info.setAge(0L);
        try {
            Map<String, Object> attrib = new HashMap<String, Object>();
            attrib.put("access_token", trustedToken);
            attrib.put("uid", trustedUid);
            String queryString = OAuthUtils.format(attrib.entrySet(), "UTF-8");
            final String urlString = userInfoURL + "?" + queryString;
            String content = getHttpContent(urlString);
            if (content != null) {
                logger.info(content);
                JSONObject dateJSONObject = new JSONObject(content);
                jsonObject.append("data", dateJSONObject);
                info.setExt(jsonObject.toString());
                info.setNickname(dateJSONObject.getString("screen_name"));
                info.setUsername(dateJSONObject.getString("name"));
                info.setPhoto(dateJSONObject.getString("profile_image_url"));
                String sex = dateJSONObject.getString("gender");
                if (sex.equals("n")) {
                    sex = "?";
                } else {
                    sex = sex.equals("m") ? "男" : "女";
                }
                info.setSex(sex);
                return info;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }

    private String getHttpContent(String httpUrl)
            throws IOException, OAuthProblemException {
        logger.info("SinaWeibo.getHttpContent(): HttpURL = " + httpUrl);
        String content = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpUriRequest request = new HttpGet(httpUrl);
        HttpResponse response = httpClient.execute(request);
        int sc = response.getStatusLine().getStatusCode();
        if (sc == 200) {
            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();
            content = OAuthUtils.saveStreamAsString(inputStream);
        } else if (sc == 401) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT);
        } else {
            throw new IOException("Unable to get remote data.");
        }
        return content;
    }

    private String getHttpContent(String httpUrl, Map<String, String> header, String postBody)
            throws IOException {
        logger.info("SinaWeibo.getHttpContent(): HttpURL = " + httpUrl + "\n postBody = " + postBody);
        String content = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(httpUrl);
        for (Map.Entry<String, String> entry : header.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
        }
        HttpEntity body = new StringEntity(postBody, "UTF-8");
        request.setEntity(body);
        HttpResponse response = httpClient.execute(request);
        int sc = response.getStatusLine().getStatusCode();
        if (sc == 200) {
            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();
            content = OAuthUtils.saveStreamAsString(inputStream);
        } else {
            throw new IOException("Unable to get remote data.");
        }
        return content;
    }
}
