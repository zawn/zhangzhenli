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
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.utils.OAuthUtils;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class UserServlet extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserServlet.class);

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        traceRequest(request);
        traceParam(request);
        ServletInputStream inputStream = request.getInputStream();
        String saveStreamAsString = OAuthUtils.saveStreamAsString(inputStream);
        System.out.println("doGet:" + saveStreamAsString);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        traceRequest(request);
        traceParam(request);
        ServletInputStream inputStream = request.getInputStream();
        String saveStreamAsString = OAuthUtils.saveStreamAsString(inputStream);
        System.out.println("doPost:" + saveStreamAsString);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        traceRequest(request);
        traceParam(request);
        ServletInputStream inputStream = request.getInputStream();
        String saveStreamAsString = OAuthUtils.saveStreamAsString(inputStream);
        System.out.println("doPut:" + saveStreamAsString);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        traceRequest(request);
        traceParam(request);
        ServletInputStream inputStream = request.getInputStream();
        String saveStreamAsString = OAuthUtils.saveStreamAsString(inputStream);
        System.out.println("doDelete:" + saveStreamAsString);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "UserServlet";
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

    private void traceParam(HttpServletRequest req) {
        String CRLF = "\r\n";
        StringBuilder buffer = new StringBuilder("\r\n-----------------------------------------------\r\n");
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String string = entry.getKey();
            String[] strings = entry.getValue();
            buffer.append(string).append(":").append(Arrays.toString(strings)).append(CRLF);
        }
        buffer.append("\r\n-----------------------------------------------\r\n");
        logger.info(buffer.toString());
    }
}
