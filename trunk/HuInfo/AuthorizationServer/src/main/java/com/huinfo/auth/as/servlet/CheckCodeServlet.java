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
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huinfo.auth.as.error.ErrorMsg;
import com.huinfo.auth.as.utils.CheckCode;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class CheckCodeServlet extends HttpServlet {

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
        String phoneNumber = request.getParameter("phone");
        if (phoneNumber == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        boolean mobileNO = CheckCode.isMobileNO(phoneNumber);
        if (!mobileNO) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Map<String, String> checkCodeAndTempToken = CheckCode.generateCheckCodeAndTempToken(phoneNumber, request.getRemoteAddr());
        String checkCode = checkCodeAndTempToken.get("checkCode");
        String tempToken = checkCodeAndTempToken.get("tempToken");
        //通过短信网关发送短信验证码，如果失败则返回相应的错误码
        if (!CheckCode.sendSMSCheckCode(phoneNumber, checkCode, request.getRemoteAddr())) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            PrintWriter writer = response.getWriter();
            writer.print("{\"error\"=\"undone_request\",\"error_description\"=\"Unable to send verification code\"}");
            writer.flush();
            writer.close();
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("temp_token", tempToken);
            PrintWriter writer = response.getWriter();
            writer.print(jSONObject.toString());
            writer.flush();
            writer.close();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
