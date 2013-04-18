/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huinfo.auth.as.utils;

/**
 *
 * @author Yiwork
 */
public class RegEx {

    // 手机号码
    public static final String PHONENUMBER = "^(((13[0-9])|(14[5,7])|(15[0-9])|(18[0-9]))[0-9]{8})$";
    // 匹配包含号码
    public static final String CONTAIN_PHONENUMBER = "((13[0-9])|(14[5,7])|(15[0-3,5-9])|(18[0,2,3,5-9]))[0-9]{8}$";
    //邮箱
    public static final String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    // 用户密码
    public static final String PASSWORD = "^((?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16})|(123456)$";
    // AccessToken
    public static final String ACCESSTOKEN = "^[A-F0-9]{32}$";
    // 通用放行的URL
    public static final String NO_NEED_TO_INTERCEPT = "^$";
}
