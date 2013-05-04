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

import javax.servlet.http.HttpServletRequest;

import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.dao.ResourceOwnMapper;
import com.huinfo.auth.as.issuer.domain.TrustedDomainType;
import com.huinfo.auth.as.model.ResourceOwn;
import com.huinfo.auth.as.utils.SecretDigest;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class PasswordIssue extends OAuthIssue {

    protected static final Logger logger = Logger.getLogger(PasswordIssue.class);
    private final String userName;
    private final String password;

    public PasswordIssue(HttpServletRequest request)
            throws OAuthProblemException {
        super(request, GrantType.PASSWORD);
        this.userName = request.getParameter(OAuth.OAUTH_USERNAME);
        this.password = request.getParameter(OAuth.OAUTH_PASSWORD);
    }

    @Override
    protected void validator()
            throws OAuthProblemException {
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            clientValidator(sqlSession);
            ResourceOwnMapper mapper = sqlSession.getMapper(ResourceOwnMapper.class);
            ResourceOwn resourceOwn = mapper.selectByUserName(userName);
            if (resourceOwn != null) {
                String digestClient = SecretDigest.digestClient(password);
                if (digestClient != null && digestClient.equals(resourceOwn.getPassword())) {
                    userID = resourceOwn.getUsername() + "@" + TrustedDomainType.Huinfo;
                    return;
                }
            }
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT);
        } finally {
            sqlSession.close();
        }
    }
}
