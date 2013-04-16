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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import com.huinfo.auth.as.dao.AccessTokenMapper;
import com.huinfo.auth.as.dao.AccesstokenScopesMapper;
import com.huinfo.auth.as.dao.ClientMapper;
import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.model.AccessToken;
import com.huinfo.auth.as.model.AccesstokenScopes;
import com.huinfo.auth.as.model.Client;
import com.huinfo.auth.as.pojo.BaseAccessToken;
import com.huinfo.auth.as.utils.SecretDigest;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
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
public abstract class OAuthIssue {

	protected static final Logger logger = Logger.getLogger(OAuthIssue.class);
	protected final HttpServletRequest request;
	protected final Set<String> scope;
	protected OAuthIssuer oauthIssuerImpl;
	protected final String clientSecret;
	protected Long clientId;
	protected String userID;
	protected final String grantType;

	/**
	 * 
	 * @param request
	 * @throws OAuthProblemException
	 */
	public OAuthIssue(HttpServletRequest request, GrantType grantType)
			throws OAuthProblemException {
		this.request = request;
		try {
			this.clientId = Long.valueOf(request
					.getParameter(OAuth.OAUTH_CLIENT_ID));
		} catch (Exception e) {
			throw OAuthProblemException
					.error(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT);
		}
		this.clientSecret = request.getParameter(OAuth.OAUTH_CLIENT_SECRET);
		this.scope = OAuthUtils.decodeScopes(request
				.getParameter(OAuth.OAUTH_SCOPE));
		this.grantType = grantType.toString();
	}

	protected Client clientValidator(SqlSession sqlSession)
			throws OAuthProblemException {
		ClientMapper mapper = sqlSession.getMapper(ClientMapper.class);
		Client client = mapper.selectByPrimaryKey(clientId);
		if (clientSecret != null && client != null
				&& client.getSecret() != null) {
			String digestClient = SecretDigest.digestClient(clientSecret);
			if (digestClient.equals(client.getSecret())) {
				clientId = client.getId();
				return client;
			}
		}
		throw OAuthProblemException
				.error(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT);
	}

	protected abstract void validator() throws OAuthProblemException;

	protected BaseAccessToken issue() throws OAuthSystemException {

		SqlSession sqlSession = DBSessionFactory.getSession();
		AccessToken accessToken = null;
		try {
			AccessTokenMapper mapper = sqlSession
					.getMapper(AccessTokenMapper.class);
			List<AccessToken> list;
			if (userID == null) {
				list = mapper.selectByClientId(clientId);
			} else {
				AccessToken atemp = new AccessToken();
				atemp.setClientId(clientId);
				atemp.setResourceownerid(userID);
				list = mapper.selectByClientIdandUserId(atemp);
			}
			if (!list.isEmpty()) {
				for (Iterator<AccessToken> it = list.iterator(); it.hasNext();) {
					accessToken = it.next();
					if (accessToken.getRefreshToken() != null) {
						break;
					}
				}
			}
		} finally {
			sqlSession.close();
		}
		if (accessToken == null) {
			oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
			BaseAccessToken at = new BaseAccessToken(
					oauthIssuerImpl.accessToken(), TokenType.BEARER.toString());
			at.setExpiresIn(302400L);
			at.setRefreshToken(oauthIssuerImpl.refreshToken());
			at.setScope(OAuthUtils.encodeScopes(scope));
			return at;
		} else {
			String encodeScopes = OAuthUtils.encodeScopes(scope);

			if (encodeScopes == null ? accessToken.getScope() == null
					: encodeScopes.equals(accessToken.getScope())) {
				accessToken.setScope(OAuthUtils.encodeScopes(scope));
			} else {
				accessToken.setScope(null);
			}
			return accessToken;
		}
	}

	protected void storage(BaseAccessToken at) throws OAuthSystemException {
		SqlSession sqlSession = DBSessionFactory.getSession();
		try {
			AccessTokenMapper mapper = sqlSession
					.getMapper(AccessTokenMapper.class);
			if (at instanceof AccessToken) {
				AccessToken accessToken = (AccessToken) at;
				AccessToken record = new AccessToken();
				record.setModificationdate(new Date());
				record.setGrantType(grantType);
				record.setPreRefreshToken(at.getRefreshToken());
				record.setScope(at.getScope());
				mapper.updateByRefreshTokenSelective(record);
				if (accessToken.getScope() != null
						&& !"".equals(accessToken.getScope())) {
					AccesstokenScopes scopes = accessToken.getScopes();
					if (scopes.getAccesstokenId() != 0L
							&& !"".equals(scopes.getElement())
							&& scopes.getElement() != null) {
						AccesstokenScopesMapper asmapper = sqlSession
								.getMapper(AccesstokenScopesMapper.class);
						asmapper.insert(scopes);
					}
				}
			} else {
				AccessToken record = new AccessToken(at, userID, clientId,
						grantType);
				mapper.insert(record);
				AccesstokenScopes scopes = record.getScopes();
				if (scopes.getAccesstokenId() != 0L
						&& !"".equals(scopes.getElement())
						&& scopes.getElement() != null) {
					AccesstokenScopesMapper asmapper = sqlSession
							.getMapper(AccesstokenScopesMapper.class);
					asmapper.insert(scopes);
				}
			}
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public BaseAccessToken getAccessToken() throws OAuthProblemException,
			OAuthSystemException {
		validator();
		BaseAccessToken sue = issue();
		storage(sue);
		return sue;
	}
}
