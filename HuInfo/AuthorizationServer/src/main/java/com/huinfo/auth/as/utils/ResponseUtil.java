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

package com.huinfo.auth.as.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class ResponseUtil {

    public static void handlerError(HttpServletResponse response, ParameterUtil.ParameterException ex)
            throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("{\"error\"=\"invalid_request\",\"error_description\"=\"" + ex.getMessage() + "\"}");
        writer.flush();
        writer.close();
    }

    public static void handlerError(HttpServletResponse response, String msg)
            throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("{\"error\"=\"invalid_request\",\"error_description\"=\"" + msg + "\"}");
        writer.flush();
        writer.close();
    }

    public static void handlerSuccess(HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print("{\"error\"=\"0\"}");
        writer.flush();
        writer.close();
    }

    public static void handlerSuccess(HttpServletResponse response, JSONObject jSONObject)
            throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(jSONObject.toString());
        writer.flush();
        writer.close();
    }
}
