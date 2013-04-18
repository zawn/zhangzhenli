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

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.dao.ResourceOwnInfoMapper;
import com.huinfo.auth.as.model.ResourceOwnInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
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
public class TencentConnect extends TrustedValidator {

    public TencentConnect(String trustedDomain, String trustedToken, String trustedUid, Long clientID) {
        super(trustedDomain, trustedToken, trustedUid, clientID);
    }
    protected static final Logger logger = Logger
            .getLogger(TrustedValidator.class);
    private static final String verifyURL = "https://graph.qq.com/oauth2.0/me";
    private static final String userInfoURL = "https://graph.qq.com/user/get_simple_userinfo?format=json";

    @Override
    public void validatorFromDomain(Map<String, Object> at)
            throws OAuthProblemException, OAuthRuntimeException {
        try {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("access_token", trustedToken);
            String queryString = OAuthUtils.format(paramsMap.entrySet(), "UTF-8");
            String httpContent = getHttpContent(verifyURL + "?" + queryString);
            logger.info(httpContent);
            // eg:callback( {"client_id":"100422774","openid":"C48C8E8B67B9DC2BB2B9879486BD13BA"} );
            // eg:callback( {"error":100016,"error_description":"access token check failed"} );
            int begin = httpContent.indexOf("(");
            int end = httpContent.indexOf(")");
            String substring = httpContent.substring(begin + 1, end - 1);
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject(substring);
            } catch (JSONException ex) {
                logger.error(null, ex);
                throw new OAuthRuntimeException(ex);
            }
            try {
                String jsClientID = jSONObject.getString("client_id");
                String jsOpenID = jSONObject.getString("openid");
                if (!trustedUid.equals(jsOpenID) || !at.get("AppKey").equals(jsClientID)) {
                    throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT);
                }
                saveUserInfo(jSONObject);
            } catch (JSONException ex) {
                try {
                    int error = jSONObject.getInt("error");
                    if (error == 100016) {
                        throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT);
                    } else {
                        logger.error(null, ex);
                        throw new OAuthRuntimeException(ex);
                    }
                } catch (JSONException ex1) {
                    logger.error(null, ex);
                    throw new OAuthRuntimeException(ex);
                }
            }
        } catch (IOException ex) {
            logger.error(null, ex);
            throw new OAuthRuntimeException(ex);
        }
    }

    private String getHttpContent(String httpUrl)
            throws IOException {
        logger.info("TencentWeibo.getHttpContent(): HttpURL = " + httpUrl);
        String content = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpUriRequest request = new HttpGet(httpUrl);
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
            String jsClientID = jsonObject.getString("client_id");
            String jsOpenID = jsonObject.getString("openid");
            Map<String, Object> attrib = new HashMap<String, Object>();
            attrib.put("access_token", trustedToken);
            attrib.put("oauth_consumer_key", jsClientID);
            attrib.put("openid", jsOpenID);
            String queryString = OAuthUtils.format(attrib.entrySet(), "UTF-8");
            final String urlString = userInfoURL + "&" + queryString;
            String content = getHttpContent(urlString);
            if (content != null) {
                logger.info(content);
                String replace = content.replace("\\/", "/");
                JSONObject dateJSONObject = new JSONObject(replace);
                int ret = dateJSONObject.getInt("ret");
                if (ret == 0) {
                    jsonObject.append("data", dateJSONObject);
                    info.setExt(jsonObject.toString());
                    info.setNickname(dateJSONObject.getString("nickname"));
                    info.setUsername(dateJSONObject.getString("nickname"));
                    try {
                        info.setPhoto(dateJSONObject.getString("figureurl_qq_2"));
                    } catch (Exception e) {
                        info.setPhoto(dateJSONObject.getString("figureurl_2"));
                    }
                }
                return info;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }
}
