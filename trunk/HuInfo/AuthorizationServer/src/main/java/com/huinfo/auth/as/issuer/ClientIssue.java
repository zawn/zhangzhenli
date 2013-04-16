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

import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.huinfo.auth.as.dao.ClientScopesMapper;
import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.model.Client;
import com.huinfo.auth.as.pojo.BaseAccessToken;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.TokenType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class ClientIssue extends OAuthIssue {

    protected static final Logger logger = Logger.getLogger(ClientIssue.class);

    public ClientIssue(HttpServletRequest request) throws OAuthProblemException {
        super(request,GrantType.CLIENT_CREDENTIALS);
    }

    @Override
    protected void validator()
            throws OAuthProblemException {
        SqlSession sqlSession = DBSessionFactory.getSession();
        try {
            Client client = clientValidator(sqlSession);
            // TODO: 进行客户端访问范围验证
            ClientScopesMapper scopesMapper = sqlSession.getMapper(ClientScopesMapper.class);
            List<String> scopesList = scopesMapper.selectByClientPrimaryKey(client.getId());
            HashSet<String> set = new HashSet<String>(scopesList);
            for (String string : scope) {
                boolean contains = set.contains(string);
                if (!contains) {
                    scope.remove(string);
                }
                if (scope.isEmpty()) {
                    scope.addAll(set);
                }
            }
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
        at.setScope(OAuthUtils.encodeScopes(scope));
        return at;
    }
}
