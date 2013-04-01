package cn.mimail.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpPost {

    public static final String USER_NAME = "JSMA301074";
    public static final String USER_PASSWORD = "389210";
    public static final String CHARSET_NAME = "GBK";
    public static final String SEND_SMS_URL = "http://www.mssms.cn:8000/msm/sdk/http/sendsms.jsp";
    /**
     * 连接超时
     */
    private static int connectTimeOut = 5000;

    /**
     * @return 连接超时(毫秒)
     */
    public static int getConnectTimeOut() {
        return HttpPost.connectTimeOut;
    }

    /**
     * @param connectTimeOut 连接超时(毫秒)
     */
    public static void setConnectTimeOut(int connectTimeOut) {
        HttpPost.connectTimeOut = connectTimeOut;
    }

    public static void main(String[] args) {
        String sendSMSCocde = "100#zhang";
        System.out.println(sendSMSCocde);
        if (!sendSMSCocde.matches("^[1][\\s\\S]*")) {
            System.out.println(11111);
        } else {
            String substring = sendSMSCocde.substring(0, 3);
            int errorCode = Integer.parseInt(substring);
            switch (errorCode) {
                case 100:
                //TODO 重发
                case 101:

            }
            System.out.println(substring);
        }

    }

    /**
     * 返回请求的根据提供的手机号和验证码组装正确的URL, 样例:<br> null null null null     {@code
     * http://www.mssms.cn:8000/msm/sdk/http/sendsms.jsp?username=NTY000000&scode=123456&mobile=13805100000&content=你好101540
     * }
     *
     * @param phoneNumber
     * @param checkCode
     * @return
     */
    private static String getRequestURL(String phoneNumber, String checkCode) throws UnsupportedEncodingException, Exception {
        if (phoneNumber == null || !phoneNumber.matches("^(((13[0-9])|(14[5,7])|(15[0-3,5-9])|(18[0-9]))[0-9]{8})$")) {
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
        sb.append(URLEncoder.encode("你好，欢迎注册呼应！你的验证码是：", CHARSET_NAME));
        sb.append(URLEncoder.encode(checkCode, CHARSET_NAME));
        return sb.toString();
    }

    private static String sendSMSCocde(String phoneNumber, String checkCode) throws IOException, UnsupportedEncodingException, Exception {
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