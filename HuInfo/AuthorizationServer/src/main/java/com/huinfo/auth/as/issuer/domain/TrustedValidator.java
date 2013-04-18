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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthRuntimeException;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public abstract class TrustedValidator {

    protected static final Logger logger = Logger.getLogger(TrustedValidator.class);
    protected final String trustedDomain;
    protected final String trustedUid;
    protected final Long clientID;
    protected final String trustedToken;

    /**
     *
     * @param trustedDomain
     * @param trustedToken
     * @param trustedUid
     * @param clientID
     */
    public TrustedValidator(String trustedDomain, String trustedToken, String trustedUid, Long clientID) {
        this.trustedDomain = trustedDomain;
        this.trustedUid = trustedUid;
        this.clientID = clientID;
        this.trustedToken = trustedToken;
    }

    /**
     * 
     * @param attrib 从数据库中查询到第三方相关参数如:AppKey等
     * 
     * @throws OAuthProblemException
     * @throws OAuthRuntimeException 
     */
    public abstract void validatorFromDomain(Map<String, Object> attrib)
            throws OAuthProblemException, OAuthRuntimeException;

    protected static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
