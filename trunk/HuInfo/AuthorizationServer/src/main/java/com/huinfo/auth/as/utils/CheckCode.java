/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.huinfo.auth.as.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class CheckCode {

    private static Map<String, Long> cachePhoneNumber = Collections.synchronizedMap(new PhoneNumberCacheMap());
    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CheckCode.class);

    /**
     * *
     * 检查用户详细信息的有效性
     *
     * @return 5 用户名手机号码不正确，4 照片格式不正确，3 职业格式不正确，2 公司格式不正确，
     */
    public static int testchangeUserProfile(String uInfoPhoto, String uInfoProfession, String uInfoCompany) {
        //验证图片的有效性
        if (!testuInfoPhoto(uInfoPhoto)) {
            return 4;
        }
        //验证职业的有效性
        if (!testuInfoProfession(uInfoProfession)) {
            return 3;
        }
        //公司格式不正确
        if (!testuInfoCompany(uInfoCompany)) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     *
     * 检查用户详细信息的有效性
     *
     * @return 6 用户名格式不正确，5 照片格式不正确，4 公司格式不正确，3 职业格式不正确，2 意向职业格式不正确，1 邮箱格式不正确
     *         ，0格式都正确
     */
    public static int testCheckuInfoNameAnduInfoPhotoAndOther(String uInfoName, String uInfoPhoto, String uInfoProfession, String uInfoCompany, String uInfoEmail, String uInfoLove) {
        //验证用户的有效性
        if (!testuInfoName(uInfoName)) {
            return 6;
        }
        //验证图片的有效性
        if (!testuInfoPhoto(uInfoPhoto)) {
            return 5;
        }
        //验证公司的有效性
        if (!testuInfoCompany(uInfoCompany)) {
            return 4;
        }
        //验证职业的有效性
        if (!testuInfoProfession(uInfoProfession)) {
            return 3;
        }
        //验证意向职业的有效性
        if (!testuInfoLove(uInfoLove)) {
            return 2;
        }
        if (isEmail(uInfoEmail)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     *
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile(RegEx.PHONENUMBER);
        Matcher m = p.matcher(mobiles);
        logger.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * **
     * 验证邮箱的有效性
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile(RegEx.EMAIL);
        Matcher m = p.matcher(email);
        logger.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * **
     *
     * @param userPassword 用户密码
     * @param newPassword  重新输入密码
     * @return 0 验证通过， 3 用户密码格式错误，2 重新输入密码格式错误，1 两次输入不匹配
     */
    public static int testUserPasswordAndNewPassword(String userPassword, String newPassword, String oldPassword) {
        logger.info("testUserPasswordAndOldPassword");
        if (testNewPassword(userPassword) && testNewPassword(newPassword) && testNewPassword(oldPassword)) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 生成一对验证码与临时令牌
     *
     * @return
     */
    public static Map<String, String> generateCheckCodeAndTempToken(String phoneNumber) {
        logger.info("generateCheckCodeAndTempToken:" + phoneNumber);
        Map<String, String> map = new HashMap<String, String>();
        //生成验证码
        String checkCode = generateCheckCode();
        //根据上面的验证码,和手机号码计算出对应的临时令牌
        String tempToken = generateTempToken(checkCode, phoneNumber);
        logger.info("checkCode: " + checkCode + ", tempToken: ", tempToken);
        map.put("checkCode", checkCode);
        map.put("tempToken", tempToken);
        return map;
    }

    public static Map<String, String> generateCheckCodeAndTempToken(String phoneNumber, String remoteAddr) {
        Map<String, String> map = new HashMap<String, String>();
        //生成验证码
        String checkCode = generateCheckCode();
        //根据上面的验证码,和手机号码计算出对应的临时令牌
        String tempToken = generateTempToken(checkCode, phoneNumber, remoteAddr);
        logger.info("checkCode: " + checkCode + ", tempToken: ", tempToken);
        map.put("checkCode", checkCode);
        map.put("tempToken", tempToken);
        return map;
    }

    /**
     * 根据提供的验证码与临时令牌验证其有效性
     *
     * @param checkCode 验证码
     * @param tempToken 临时令牌
     * @return 0 验证通过，1 验证不通过 ，2 验证码格式错误，3临时格式错误
     */
    public static int testCheckCodeAndTempToken(String checkCode, String tempToken, String phoneNumber) {
        logger.info("testCheckCodeAndTempToken");
        if (!testCheckCode(checkCode)) {
            return 2;
        }
        if (!testTempToken(tempToken)) {
            return 3;
        }
        //根据提供的验证计算出对应的临时令牌并与提供的临时令牌比较
        if (tempToken.equals(generateTempToken(checkCode, phoneNumber))) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 根据提供的验证码与临时令牌验证其有效性
     *
     * @param checkCode 验证码
     * @param tempToken 临时令牌
     * @return 0 验证通过，1 验证不通过 ，2 验证码格式错误，3临时格式错误
     */
    public static int testCheckCodeAndTempToken(String checkCode, String tempToken, String phoneNumber, String remoteAddr) {
        logger.info("testCheckCodeAndTempToken");
        if (!testCheckCode(checkCode)) {
            return 2;
        }
        if (!testTempToken(tempToken)) {
            return 3;
        }
        //根据提供的验证计算出对应的临时令牌并与提供的临时令牌比较
        if (tempToken.equals(generateTempToken(checkCode, phoneNumber, remoteAddr))) {
            if (cachePhoneNumber.containsKey(phoneNumber)) {
                Long remove = cachePhoneNumber.remove(phoneNumber);
                if (remove > 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * 进行验证码有效性验证
     *
     * @param checkCode
     * @return true 验证通过，false 验证码格式错误
     */
    public static void rollbackCheckCode(String phoneNumber) {
        cachePhoneNumber.put(phoneNumber, System.currentTimeMillis() - 1000L);
    }

    /**
     * 进行验证码有效性验证
     *
     * @param checkCode
     * @return true 验证通过，false 验证码格式错误
     */
    private static boolean testCheckCode(String checkCode) {
        //TODO   完成验证码有效性验证
        if (checkCode.length() == 6) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 进行临时令牌有效性验证
     *
     * @param tempToken
     * @return true 验证通过，false 验证码格式错误
     */
    private static boolean testTempToken(String tempToken) {
        if (tempToken.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 随进生成短信验证码
     *
     * @return 生成的短信验证码
     */
    private static String generateCheckCode() {
        //TODO generateCheckCode
        return beginInt(100000, 999999) + "";
    }

    /**
     * 根据提供的随机验证码生成对应的临时令牌
     *
     * @param checkCode
     * @return tempToken 与提供的验证码对应的临时令牌
     */
    private static String generateTempToken(String checkCode, String phoneNumber) {
        int code = Integer.parseInt(checkCode);
        int num = (code + 1989) * 1211 - 21119891;
        String str = num + "";
        String ss = str.substring(0, 6) + phoneNumber;
        String sb = CheckCode.md5(ss);
        return sb;
    }

    private static String generateTempToken(String checkCode, String phoneNumber, String remoteAddr) {
        String s = checkCode.toLowerCase() + phoneNumber.toLowerCase() + remoteAddr.toLowerCase();
        String md5 = CheckCode.md5(s);
        return md5;

    }

    public static int getCurrentMinute() {
        //得到当前时间的分钟数
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 向特定的手机号码发送短信验证码
     *
     * @param phoneNumber
     * @param checkCode
     * @return true 发送成功，false 发送失败，
     */
    public static boolean sendSMSCheckCode(String phoneNumber, String checkCode, String remoteAddr) {
        logger.info("sendSMSCheckCode phoneNumber = " + phoneNumber + " , checkCode = " + checkCode);
        final long currentTimeMillis = System.currentTimeMillis();
        Long preTimeMillis = cachePhoneNumber.put(phoneNumber, currentTimeMillis);
        if (preTimeMillis != null && ((currentTimeMillis - preTimeMillis < 60000L && preTimeMillis > 0) || preTimeMillis < -3L)) {
            logger.info("sendSMSCheckCode;false , requests are too frequent");
            return false;
        }
        String sendSMSCocde = null;
        try {
            sendSMSCocde = SMSHelper.sendSMSCocde(phoneNumber, checkCode);
            logger.info("sendSMSCocde = " + sendSMSCocde);
        } catch (UnsupportedEncodingException ex) {
            logger.error("编码格式错误：", ex);
        } catch (IOException ex) {
            logger.error("网络异常错误：", ex);
        } catch (Exception ex) {
            logger.error("网络异常错误：", ex);
        }
        logger.info("ResultCode:" + sendSMSCocde);
        if (!sendSMSCocde.matches("^[1][\\s\\S]*")) {
            logger.info("sendSMSCheckCode;true");
            return true;
        } else {
            logger.error("错误码处理,短信未发送成功");
            String substring = sendSMSCocde.substring(0, 3);
            int errorCode = Integer.parseInt(substring);
            switch (errorCode) {
                case 100:
                    if (cachePhoneNumber.get(phoneNumber) > 0L) {
                        cachePhoneNumber.put(phoneNumber, 0L);
                    } else {
                        cachePhoneNumber.put(phoneNumber, cachePhoneNumber.get(phoneNumber) - 1);
                    }
                    sendSMSCheckCode(phoneNumber, checkCode, remoteAddr);
                    break;
                case 101:
                    //TODO 提示管理员
                    break;
                case 102:
                    break;
                case 103:
                    break;
                case 104:
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException ex) {
                    }
                    if (cachePhoneNumber.get(phoneNumber) > 0L) {
                        cachePhoneNumber.put(phoneNumber, 0L);
                    } else {
                        cachePhoneNumber.put(phoneNumber, cachePhoneNumber.get(phoneNumber) - 1);
                    }
                    sendSMSCheckCode(phoneNumber, checkCode, remoteAddr);
                    break;
                case 105:
                    break;
                case 106:
                    break;
                case 107:
                    break;
                case 108:
                    break;
                case 109:
                    break;
                case 110:
                    break;
                case 111:
                    break;
                case 112:
                    break;
                case 113:
                    break;
                default:
                    break;
                //TODO 
                }
        }
        logger.info("sendSMSCheckCode;false");
        return false;
    }

    /**
     * *
     * 得到一个新的六位数(即是，临时令牌)
     *
     * @return
     */
    public static int endInt() {
        int count = nextInt();
        int num = (count + 1989) * 1211 - 21119891;
        String str = num + "";
        String ss = str.substring(0, 6);
        count = Integer.parseInt(ss);
        return count;
    }

    /**
     * *
     * 得到一个六位数随机数
     *
     * @return
     */
    public static int nextInt() {
        return beginInt(100000, 999999);
    }

    /**
     * *
     * 得到一个随机数
     *
     * @param min 最小范围
     * @param max 最大范围
     * @return
     */
    public static int beginInt(final int min, final int max) {
        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());
        return tmp % (max - min + 1) + min;
    }
    //十六进制下数字到字符的映射数组 
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 把inputString加密
     */
    public static String md5(String inputString) {
        return encodeByMD5(inputString);
    }

    /**
     * 验证输入的密码是否正确
     *
     * @param password    真正的密码（加密后的真密码）
     * @param inputString 输入的字符串
     * @return 验证结果，boolean类型
     */
    public static boolean compare(String md5Str, String inputString) {
        if (md5Str.equals(encodeByMD5(inputString))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 对字符串进行MD5编码
     */
    private static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                //创建具有指定算法名称的信息摘要 
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算 
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回 
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 轮换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * *
     * 验证用户密码的有效性
     *
     * @param userPassword
     * @return
     */
    public static boolean testUserPassword(String userPassword) {
        Pattern p = Pattern.compile(RegEx.PASSWORD);
        Matcher m = p.matcher(userPassword);
        logger.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * *
     * 验证
     *
     * @param oldPassword
     * @return
     */
    public static boolean testNewPassword(String newPassword) {
        Pattern p = Pattern.compile(RegEx.PASSWORD);
        Matcher m = p.matcher(newPassword);
        logger.info(m.matches() + "---");
        return m.matches();
    }

    /**
     * *
     * 用户的有效性
     *
     * @param uInfoName
     * @return
     */
    private static boolean testuInfoName(String uInfoName) {
        if (uInfoName.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     * 验证图片的有效性
     *
     * @param uInfoPhoto
     * @return
     */
    private static boolean testuInfoPhoto(String uInfoPhoto) {
        if (uInfoPhoto.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     * 验证公司的有效性
     *
     * @param uInfoCompany
     * @return
     */
    private static boolean testuInfoCompany(String uInfoCompany) {
        if (uInfoCompany.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     * 验证职业的有效性
     *
     * @param uInfoProfession
     * @return
     */
    private static boolean testuInfoProfession(String uInfoProfession) {
        if (uInfoProfession.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * *
     * 验证意向职业的有效性
     *
     * @param uInfoLove
     * @return
     */
    private static boolean testuInfoLove(String uInfoLove) {
        if (uInfoLove.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static class PhoneNumberCacheMap extends LinkedHashMap<String, Long> {

        private static final int MAX_TIMEOUT = 6000;

        public PhoneNumberCacheMap() {
            super();
        }

        @Override
        protected boolean removeEldestEntry(Entry<String, Long> eldest) {
            long i = eldest.getValue() - System.currentTimeMillis();
            return i > MAX_TIMEOUT;
        }
    }
}
