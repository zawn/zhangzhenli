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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class ParameterUtil {

    public static String getParamNotNull(HttpServletRequest request, String param)
            throws ParameterException {
        String parameter = request.getParameter(param);
        if (parameter == null) {
            throw new ParameterException(parameter);
        }
        return parameter;
    }

    /**
     *
     * @param request
     * @param param
     * @param param
     * @return
     */
    public static String[] getParamNotNull(HttpServletRequest request, String... params)
            throws ParameterException {
        if (params == null) {
            return null;
        }
        String[] result = new String[params.length];
        ParameterException pe = null;
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            if (param == null) {
                result[i] = null;
            } else {
                String parameter = request.getParameter(param);
                if (parameter == null) {
                    if (pe == null) {
                        pe = new ParameterException(param);
                    } else {
                        pe.addErrorParam(param);
                    }
                } else {
                    result[i] = parameter;
                }
            }
        }
        if (pe != null) {
            throw pe;
        }
        return result;
    }

    public static class ParameterException extends Exception {

        private List<String> paramsList = new ArrayList<String>();

        public ParameterException(String param) {
            paramsList.add(param);
        }

        public ParameterException(String[] params) {
            paramsList.addAll(Arrays.asList(params));
        }

        public void addErrorParam(String param) {
            paramsList.add(param);
        }

        public void addErrorParam(String[] params) {
            paramsList.addAll(Arrays.asList(params));
        }

        public List<String> getErrorParams() {
            return paramsList;
        }

        @Override
        public String getMessage() {
            return this.toString();
        }

        @Override
        public String getLocalizedMessage() {
            return this.toString();
        }

        @Override
        public String toString() {
            return "Parameter" + paramsList.toString() + " is Null!";
        }
    }
}
