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

package com.huinfo.auth.as.endpoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huinfo.auth.as.issuer.ClientIssue;
import com.huinfo.auth.as.issuer.OAuthIssue;
import com.huinfo.auth.as.issuer.PasswordIssue;
import com.huinfo.auth.as.issuer.RefreshTokenIssue;
import com.huinfo.auth.as.issuer.TrustedTokenIssue;
import com.huinfo.auth.as.pojo.BaseAccessToken;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class TokenEndpoint extends AbstractEndpoint {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TokenEndpoint.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OAuthTokenRequest auth = null;
            BaseAccessToken accessToken;
            OAuthIssue oauthIssue = null;
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            try {
                auth = new OAuthTokenRequest(request);
                String grantTypeString = auth.getGrantType();
                GrantType grantType = GrantType.valueOf(grantTypeString.toUpperCase());
                switch (grantType) {
                    case TRUSTED_TOKEN:
                        oauthIssue = new TrustedTokenIssue(request);
                        break;
                    case PASSWORD:
                        oauthIssue = new PasswordIssue(request);
                        break;
                    case REFRESH_TOKEN:
                        oauthIssue = new RefreshTokenIssue(request);
                        break;
                    case CLIENT_CREDENTIALS:
                        oauthIssue = new ClientIssue(request);
                        break;
                    default:
                        throw OAuthUtils.handleOAuthProblemException("Current grant "
                                + "type may not be supported. Please try another type.");
                }
                accessToken = oauthIssue.getAccessToken();

                OAuthResponse r = OAuthASResponse
                        .tokenResponse(HttpServletResponse.SC_OK)
                        .setAccessToken(accessToken.getAccessToken())
                        .setExpiresIn(accessToken.getExpiresIn().toString())
                        .setRefreshToken(accessToken.getRefreshToken())
                        .setTokenType(accessToken.getTokenType())
                        .setScope(accessToken.getScope())
                        .buildJSONMessage();

                response.setStatus(r.getResponseStatus());
                PrintWriter pw = response.getWriter();
                pw.print(r.getBody());
                pw.flush();
                pw.close();

                //if something goes wrong
            } catch (OAuthProblemException ex) {

                OAuthResponse r = OAuthResponse
                        .errorResponse(401)
                        .error(ex)
                        .buildJSONMessage();

                response.setStatus(r.getResponseStatus());

                PrintWriter pw = response.getWriter();
                pw.print(r.getBody());
                pw.flush();
                pw.close();

                response.sendError(401);
            }
        } catch (OAuthSystemException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
