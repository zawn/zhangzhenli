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

import javax.net.ssl.HttpsURLConnection;

import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.dao.ResourceOwnInfoMapper;
import com.huinfo.auth.as.model.ResourceOwnInfo;
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
            System.out.println(verifyURL + "?" + queryString);
            URL url = new URL(verifyURL);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream outputStream = urlConnection.getOutputStream();
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
            out.write(queryString);
            out.flush();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                InputStream in = urlConnection.getInputStream();
                String response = OAuthUtils.saveStreamAsString(in);
                logger.info(response);
                in.close();
                JSONObject jsonObject = new JSONObject(response);
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
            out.close();
            urlConnection.disconnect();
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
            if (owninfo == null) {
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
            logger.info(urlString);
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                InputStream in = urlConnection.getInputStream();
                String response = OAuthUtils.saveStreamAsString(in);
                logger.info(response);
                in.close();
                JSONObject dateJSONObject = new JSONObject(response);
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
}
