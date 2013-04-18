/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.huinfo.auth.as.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.imageio.plugins.common.I18N;
import org.apache.ibatis.io.Resources;

/**
 *
 * @author Yutian
 */
public class SMSHelper {

    public static final String USER_NAME = "JSMA301073";
    public static final String USER_PASSWORD = "389210";
    public static final String CHARSET_NAME = "GBK";
    public static final String SEND_SMS_URL = "http://www.mssms.cn:8000/msm/sdk/http/sendsms.jsp";
    public static String[] SMS_TEMPLATE;
    /**
     * 连接超时
     */
    private static int connectTimeOut = 5000;

    /**
     * @return 连接超时(毫秒)
     */
    public static int getConnectTimeOut() {
        return SMSHelper.connectTimeOut;
    }

    /**
     * @param connectTimeOut 连接超时(毫秒)
     */
    public static void setConnectTimeOut(int connectTimeOut) {
        SMSHelper.connectTimeOut = connectTimeOut;
    }

    /**
     * 返回请求的根据提供的手机号和验证码组装正确的URL, 样例:<br> null null null null null null null
     * null null null null null null null null null null null null null null
     * null null     {@code
     * http://www.mssms.cn:8000/msm/sdk/http/sendsms.jsp?username=NTY000000&scode=123456&mobile=13805100000&content=你好101540
     * }
     *
     * @param phoneNumber
     * @param checkCode
     * @return
     */
    private static String getRequestURL(String phoneNumber, String checkCode)
            throws UnsupportedEncodingException, Exception {
        if (phoneNumber == null || !phoneNumber.matches(RegEx.PHONENUMBER)) {
            throw new Exception("手机号码不正确");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(SEND_SMS_URL);
        sb.append("?username=");
        sb.append(USER_NAME);
        sb.append("&scode=");
        sb.append(USER_PASSWORD);
        sb.append("&mobile=");
        sb.append(phoneNumber);
        sb.append("&content=");
        sb.append(URLEncoder.encode(getContent(checkCode), CHARSET_NAME));
        return sb.toString();
    }
    private static String CONFIGFILE = "i18N.properties";

    public static String getContent(String content) {
        if (SMS_TEMPLATE == null) {
            SMS_TEMPLATE = getContentTemplate();
        }

        return SMS_TEMPLATE[0].replace(SMS_TEMPLATE[1], content);
    }

    public static String[] getContentTemplate() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(CONFIGFILE);
            Properties properties = new Properties();
            properties.load(inputStream);
            String replace = properties.getProperty("sms.replace");
            String template = properties.getProperty("sms.template");
            return new String[]{template, replace};
        } catch (IOException ex) {
            Logger.getLogger(SMSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(SMSHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * 发送短信验证码,返回短信接口的返回码,以下是服务器可能返回的返回码,程序应妥善处理
     * <pre>
     * {@code
     * 0# 数字 #数字  提交成功，格式：返回值#提交计费条数 提交计费条数 提交计费条数 #提交成功号码数 号码数
     * 100 发送失败
     * 101 用户账号不存在或密码错误
     * 102 账号已禁用
     * 103 参数不正确
     * 104 用户访问时间间隔低于50毫秒
     * 105 短信内容超过300字、或为空、或内容编码格式不正确
     * 106 手机号码超过100个或合法的手机号码为空
     * 107 用户账户不支持使用扩展码
     * 108 余额不足
     * 109 指定访问ip地址错误
     * 110#( 敏感词 敏感词 A, 敏感词 敏感词 B) 短信内容存在系统保留关键词，如有多个词，使用逗号分隔：110#(110#( 李 老师 ,XX,XX,XX,成人 )
     * 111 超出当天限发条数超出
     * 112 预约发送时间格式错误
     * 113 短信签名为空或者格式错误
     * }
     * </pre>
     *
     * @param phoneNumber
     * @param checkCode
     * @return 返回码
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String sendSMSCocde(String phoneNumber, String checkCode)
            throws IOException, UnsupportedEncodingException, Exception {
        HttpURLConnection httpCon = null;
        String responseContent = null;
        try {
            URL url = new URL(getRequestURL(phoneNumber, checkCode));
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.setConnectTimeout(connectTimeOut);
            httpCon.setDoOutput(false);
            httpCon.setDoInput(true);
            InputStream in = httpCon.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                baos.write(b);
            }
            responseContent = baos.toString("GBK").trim();
            int code = httpCon.getResponseCode();
            if (code != 200) {
                responseContent = "ERROR:" + code;
            }
            in.close();
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpCon != null) {
                httpCon.disconnect();
            }
        }
        return responseContent;
    }
}
