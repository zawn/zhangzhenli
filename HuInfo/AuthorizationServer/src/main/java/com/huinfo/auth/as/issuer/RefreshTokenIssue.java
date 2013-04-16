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

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.huinfo.auth.as.dao.AccessTokenMapper;
import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.model.AccessToken;
import com.huinfo.auth.as.pojo.BaseAccessToken;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.TokenType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class RefreshTokenIssue extends OAuthIssue {
    protected static final Logger logger = Logger.getLogger(RefreshTokenIssue.class);

    private final String refreshToken;

    public RefreshTokenIssue(HttpServletRequest request) throws OAuthProblemException {
        super(request, GrantType.REFRESH_TOKEN);
        this.refreshToken = request.getParameter(OAuth.OAUTH_REFRESH_TOKEN);
    }

    @Override
    protected void validator()
            throws OAuthProblemException {
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            clientValidator(sqlSession);
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            AccessToken record = mapper.selectByRefreshToken(refreshToken);
            if (record != null && record.getClientId().equals(clientId)) {
                userID = record.getResourceownerid();
                return;
            }
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_GRANT);
        } finally {
            sqlSession.close();
        }
    }

    @Override
    protected BaseAccessToken issue()
            throws OAuthSystemException {
    	oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        BaseAccessToken at = new BaseAccessToken(oauthIssuerImpl.accessToken(), TokenType.BEARER.toString());
        at.setExpiresIn(302400L);
        at.setRefreshToken(null);
        at.setScope(OAuthUtils.encodeScopes(scope));
        return at;
    }
    
    

    @Override
    protected void storage(BaseAccessToken at)
            throws OAuthSystemException {
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            AccessTokenMapper mapper = sqlSession.getMapper(AccessTokenMapper.class);
            AccessToken record = new AccessToken(at.getAccessToken(), null);
            record.setRefreshToken(at.getRefreshToken());
            record.setModificationdate(new Date());
            record.setGrantType(grantType);
            record.setPreRefreshToken(refreshToken);
            int u = mapper.updateByRefreshTokenSelective(record);
            if (u != 1) {
                throw new OAuthSystemException("Not found refreshToken = " + refreshToken + ", not refresh token");
            }
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
