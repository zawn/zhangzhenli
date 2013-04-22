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

package com.huinfo.auth.as.issuer;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.dao.TrustedDomainMapper;
import com.huinfo.auth.as.issuer.domain.SinaWeibo;
import com.huinfo.auth.as.issuer.domain.TencentConnect;
import com.huinfo.auth.as.issuer.domain.TencentWeibo;
import com.huinfo.auth.as.issuer.domain.TrustedDomainType;
import com.huinfo.auth.as.issuer.domain.TrustedValidator;
import com.huinfo.auth.as.model.TrustedDomain;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class TrustedTokenIssue extends OAuthIssue {

    protected static final Logger logger = Logger.getLogger(TrustedTokenIssue.class);
    private final String trustedToken;
    private final String trustedDomain;
    private final String trustedUid;

    public TrustedTokenIssue(HttpServletRequest request)
            throws OAuthProblemException {
        super(request, GrantType.TRUSTED_TOKEN);
        this.trustedToken = request.getParameter(OAuth.OAUTH_TRUSTED_TOKEN);
        this.trustedDomain = request.getParameter(OAuth.OAUTH_TRUSTED_DOMAIN);
        this.trustedUid = request.getParameter(OAuth.OAUTH_TRUSTED_UID);
    }

    @Override
    protected void validator()
            throws OAuthProblemException {
        TrustedValidator validator;
        if (TrustedDomainType.SinaWeibo.toString().equals(trustedDomain)) {
            validator = new SinaWeibo(trustedDomain, trustedToken, trustedUid, clientId);
        } else if (TrustedDomainType.TencentWeibo.toString().equals(trustedDomain)) {
            validator = new TencentWeibo(trustedDomain, trustedToken, trustedUid, clientId);
        } else if (TrustedDomainType.TencentConnect.toString().equals(trustedDomain)) {
            validator = new TencentConnect(trustedDomain, trustedToken, trustedUid, clientId);
        } else {
            throw OAuthProblemException.error("unsupported_trusted_domain");
        }
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            clientValidator(sqlSession);
            TrustedDomain tee = new TrustedDomain();
            tee.setClientid(clientId);
            tee.setDomain(this.trustedDomain);
            TrustedDomainMapper instance = sqlSession.getMapper(TrustedDomainMapper.class);
            List<TrustedDomain> result = instance.selectClientIDandTrustedDomain(tee);
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            for (TrustedDomain td : result) {
                hashMap.put(td.getAttributeName(), td.getAttributeValue());
            }
            validator.validatorFromDomain(hashMap);
            userID = trustedUid + "@" + trustedDomain;
        } finally {
            sqlSession.close();
        }
    }
}
