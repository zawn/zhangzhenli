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

import com.huinfo.auth.as.dao.DBSessionFactory;
import com.huinfo.auth.as.dao.ResourceOwnInfoMapper;
import com.huinfo.auth.as.dao.ResourceOwnMapper;
import com.huinfo.auth.as.model.ResourceOwn;
import com.huinfo.auth.as.model.ResourceOwnInfo;
import com.huinfo.auth.as.utils.CheckCode;
import com.huinfo.auth.as.utils.ParameterUtil;
import com.huinfo.auth.as.utils.ParameterUtil.ParameterException;
import com.huinfo.auth.as.utils.ResponseUtil;
import com.huinfo.auth.as.utils.SecretDigest;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;

/**
 * 账户注册与检查
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class AccountServlet extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AccountServlet.class);

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
//        traceRequest(request);
//        traceParam(request);
//        ServletInputStream inputStream = request.getInputStream();
//        String saveStreamAsString = OAuthUtils.saveStreamAsString(inputStream);
//        System.out.println("aaaaaaaaaaa:"+saveStreamAsString);
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
        try {
            String[] params = ParameterUtil.getParamNotNull(request, "username", "password", "phone", "checkcode", "temptoken");
            String username = params[0];
            String password = params[1];
            String phone = params[2];
            String checkcode = params[3];
            String temptoken = params[4];
            int tt = CheckCode.testCheckCodeAndTempToken(checkcode, temptoken, phone, request.getRemoteAddr());
            if (tt == 0) {
                SqlSession sqlSession = DBSessionFactory.getSession();
                try {
                    ResourceOwnMapper romapper = sqlSession.getMapper(ResourceOwnMapper.class);
                    ResourceOwn ro = new ResourceOwn();
                    ro.setUsername(username);
                    ro.setPassword(SecretDigest.digestClient(password));
                    ro.setExt(phone);
                    int insert = romapper.insert(ro);
                    Long id = ro.getId();
                    if (insert == 1 && id != null) {
                        ResourceOwnInfoMapper roimapper = sqlSession.getMapper(ResourceOwnInfoMapper.class);
                        ResourceOwnInfo roi = new ResourceOwnInfo();
                        roi.setUsername(username);
                        roi.setUserid(id.toString());
                        roi.setDomain("http://www.huinfo.com/");
                        roimapper.insert(roi);
                    }
                    sqlSession.commit();
                    ResponseUtil.handlerSuccess(response);
                } catch (PersistenceException ex) {
                    CheckCode.rollbackCheckCode(phone);
                    ResponseUtil.handlerError(response, "user already exists");
                } finally {
                    sqlSession.close();
                }
            } else {
                ResponseUtil.handlerError(response, "check code error");
            }
        } catch (ParameterException ex) {
            ResponseUtil.handlerError(response, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String[] params = ParameterUtil.getParamNotNull(request, "username", "newpassword", "oldpassword");
            String username = params[0];
            String newpassword = params[1];
            String oldpassword = params[2];
            SqlSession sqlSession = DBSessionFactory.getSession();
            try {
                ResourceOwnMapper romapper = sqlSession.getMapper(ResourceOwnMapper.class);
                ResourceOwn user = romapper.selectByUserName(username);
                if (user != null && username.equals(user.getUsername())
                        && SecretDigest.digestClient(oldpassword).equals(user.getPassword())) {
                    user.setPassword(SecretDigest.digestClient(newpassword));
                } else {
                    ResponseUtil.handlerError(response, "The user does not exist or the password does not match");
                }
                int insert = romapper.updateByPrimaryKeySelective(user);
                if (insert != 1) {
                    ResponseUtil.handlerError(response, "failed to modify user password");
                }
                sqlSession.commit();
                ResponseUtil.handlerSuccess(response);
            } catch (PersistenceException ex) {
                ResponseUtil.handlerError(response, "failed to modify user password");
            } finally {
                sqlSession.close();
            }
        } catch (ParameterException ex) {
            ResponseUtil.handlerError(response, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "AccountServlet";
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
