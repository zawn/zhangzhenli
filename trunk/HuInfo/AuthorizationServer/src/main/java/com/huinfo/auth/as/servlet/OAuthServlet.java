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

package com.huinfo.auth.as.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huinfo.auth.as.endpoint.AbstractEndpoint;
import com.huinfo.auth.as.endpoint.AuthorizationEndpoint;
import com.huinfo.auth.as.endpoint.TokenEndpoint;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class OAuthServlet extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OAuthServlet.class);
    private static final String ENDPOINT_URI_AUTHORIZATION = "/authorize";
    private static final String ENDPOINT_URI_TOKEN = "/token";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private AuthorizationEndpoint authorizationEndpoint;
    private TokenEndpoint tokenEndpoint;

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        authorizationEndpoint = new AuthorizationEndpoint();
        tokenEndpoint = new TokenEndpoint();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        traceRequest(req);

        AbstractEndpoint endpoint = getEndpoint(req);
        if (endpoint != null) {
            String method = req.getMethod();

            if (method.equals(METHOD_GET)) {
                endpoint.doGet(req, resp);
            } else if (method.equals(METHOD_POST)) {
                endpoint.doPost(req, resp);
            } else if (method.equals(METHOD_PUT)) {
                endpoint.doPut(req, resp);
            } else if (method.equals(METHOD_DELETE)) {
                endpoint.doDelete(req, resp);
            } else {
                super.service(req, resp);
            }
        } else {
            super.service(req, resp);
        }

    }

    private AbstractEndpoint getEndpoint(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        if (ENDPOINT_URI_AUTHORIZATION.equals(pathInfo)) {
            logger.info("Authorization Endpoint Request");
            return authorizationEndpoint;
        } else if (ENDPOINT_URI_TOKEN.equals(pathInfo)) {
            logger.info("Token Endpoint Request");
            return tokenEndpoint;
        }
        logger.info("Invalid Request");
        return null;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void traceRequest(HttpServletRequest req) {
        String CRLF = "\r\n";
        StringBuilder buffer = new StringBuilder("\r\n-----------------------------------------------\r\n")
                .append(req.getMethod()).append(" ").append(req.getRequestURI())
                .append(" ").append(req.getProtocol());

        Enumeration<String> reqHeaderEnum = req.getHeaderNames();

        while (reqHeaderEnum.hasMoreElements()) {
            String headerName = reqHeaderEnum.nextElement();
            buffer.append(CRLF).append(headerName).append(": ")
                    .append(req.getHeader(headerName));
        }
        buffer.append("\r\n-----------------------------------------------\r\n");
        logger.info(buffer.toString());
    }
}
