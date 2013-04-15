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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class SecretDigest {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SecretDigest.class);
    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            logger.error(null, ex);
        }
    }

    public static String digestClient(String secret) {
        try {
            digest.reset();
            String result = StringTools.byteArrayToHexString(digest.digest(secret.getBytes("UTF-8")));
            return result;
        } catch (UnsupportedEncodingException ex) {
            logger.error(null, ex);
        }
        return null;
    }
}
