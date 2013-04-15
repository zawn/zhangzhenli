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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;

import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.dao.ResourceOwnInfoMapper;
import com.huinfo.auth.as.model.ResourceOwnInfo;
import com.huinfo.auth.as.model.ResourceOwnInfoExample;
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
public class TencentWeibo extends TrustedValidator {

    protected static final Logger logger = Logger.getLogger(TrustedValidator.class);
    private static final String verifyURL = "https://open.t.qq.com/api/user/info";

    public TencentWeibo(String trustedDomain, String trustedToken, String trustedUid, Long clientID) {
        super(trustedDomain, trustedToken, trustedUid, clientID);
    }

    @Override
    public void validatorFromDomain(Map<String, Object> at)
            throws OAuthProblemException, OAuthRuntimeException {
        try {
            Map<String, Object> attrib = new HashMap<String, Object>();
            attrib.put("format", "json");
            attrib.put("oauth_consumer_key", at.get("appkey"));
            attrib.put("access_token", trustedToken);
            attrib.put("openid", trustedUid);
            attrib.put("oauth_version", "2.a");
            String queryString = OAuthUtils.format(attrib.entrySet(), "UTF-8");
            System.out.println(verifyURL + "?" + queryString);
            URL url = new URL(verifyURL + "?" + queryString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            String response = OAuthUtils.saveStreamAsString(in);
            in.close();
            urlConnection.disconnect();
            logger.info(response);
            JSONObject jSONObject = new JSONObject(response);
            int ret = jSONObject.getInt("ret");
            if (ret != 0) {
                throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT);
            } else {
                saveUserInfo(jSONObject);
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
            ResourceOwnInfoExample condition = new ResourceOwnInfoExample();
            condition.createCriteria().andUseridEqualTo(trustedUid);
            List<ResourceOwnInfo> list = mapper.selectByExample(condition);
            if (!list.isEmpty()) {
                return;
            } else {
                jsonObject.put(OAuth.OAUTH_TRUSTED_TOKEN, trustedToken);
                jsonObject.put(OAuth.OAUTH_TRUSTED_DOMAIN, trustedDomain);
                jsonObject.put(OAuth.OAUTH_TRUSTED_UID, trustedUid);
                jsonObject.put("TRUSTED_TIME", (new Date()).getTime());
                JSONObject dataJSONObject = jsonObject.getJSONObject("data");
                int birth_year = dataJSONObject.getInt("birth_year");
                int birth_month = dataJSONObject.getInt("birth_month");
                int birth_day = dataJSONObject.getInt("birth_day");
                Calendar calendar = new GregorianCalendar();
                calendar.set(birth_year, birth_month - 1, birth_day, 0, 0, 0);
                calendar.setTimeZone(TimeZone.getDefault());
                Date time = calendar.getTime();
                ResourceOwnInfo info = new ResourceOwnInfo();
                info.setDomain(trustedDomain);
                info.setUserid(trustedUid);
                info.setAge(time.getTime());
                info.setExt(jsonObject.toString());
                info.setNickname(dataJSONObject.getString("nick"));
                info.setUsername(dataJSONObject.getString("name"));
                info.setPhoto(dataJSONObject.getString("head") + "/50");
                int aInt = dataJSONObject.getInt("sex");
                info.setSex(aInt == 1 ? "男" : "女");
                mapper.insert(info);
                sqlSession.commit();
            }
        } catch (JSONException ex) {
            logger.error(null, ex);
            throw new OAuthRuntimeException(ex);
        } finally {
            sqlSession.close();
        }
    }
}
