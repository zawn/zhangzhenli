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

package com.huinfo.auth.as.error;

/**
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class ErrorMsg {

    public static final int ERROR_VERSION = 1000000001;//版本号的错误
    public static final int DOUBLE_VERSION = 2000000001;//版本号重复
    public static final int ERROR_USERPWD = 2000000002;//原密码有误
    // 拦截器可返回的错误码
    public static final int TOKEN_INVALID = 1100000001;//Token无效
    public static final int PERMISSIONS_ERROR = 1100000002;//权限不足
    public static final int URL_INVALID = 1100000004;//访问错误
    public static final int ACCESS_ERROR = 1100000005;//访问错误
    public static final int SERVER_ERROR = 1100000006;//未知错误
    public static final int ERROR_MSM = 1000000002;//验证码信息发送失败register
    public static final int ERROR_REGISTERCHECK = 1000000003;//验证码验证失败
    public static final int ERROR_PHONRNUBER = 1000000004;//手机号码输入有误,或者格式错误
    public static final int ERROR_PHONENULL = 1000000005;//手机号码不存在
    public static final int ERROR_PASSWORD = 1000000006;//手机密码错误，
    public static final int ERROR_ACCESSTOKEN = 1000000007;//AccessToken删除失败
    public static final int ERROR_UPDATEPASSWORD = 1000000008;//密码修改失败
    public static final int ERROR_ACCESSTOKENFULL = 1000000009;//accessToken不存在
    public static final int ERROR_UPDATEACCESSTOKEN = 1000000010;//重置accessToken失败
    /**
     * 用户不存在
     */
    public static final int ERROR_USERNULL = 1000000011;
    public static final int ERROR_PASSWORDFORM = 1000000012;//输入密码格式错误
    public static final int ERROR_UPDATEPASSWORDFAIL = 1000000013;//修改密码失败
    public static final int ERROR_REGISTER = 1000000014;//注册失败
    public static final int ERROR_TOKENINSERT = 1000000015;//accessToken添加失败
    public static final int ERROR_USERINFO = 1000000016;//用户详细信息格式错误
    public static final int ERROR_SELECTUSERINFOBYID = 1000000017;//根据id查询用户不存在
    public static final int ERROR_QUERYNAME = 1000000018;//根据用户输入的值，uInfoName没有匹配的值
    public static final int ERROR_QUERYPROSESSION = 1000000019;//根据用户输入的值，uInfoProsession没有匹配的
    public static final int ERROR_QUERYCOMPANY = 1000000020;//根据用户输入的值，uInfoCompany没有匹配的值
    public static final int ERROR_QUERYINFO = 1000000021;//没有和参数匹配的
    public static final int ERROR_DELETE = 1000000022;//删除关系状态失败
    public static final int ERROR_USERFRIENDS = 1000000023;//他的好友显示失败(无权限看他的好友)
    public static final int ERROR_RECOMMENDFRIEND = 1000000024;//无推荐的好友
    public static final int ERROR_UPDATEUSERPROFILE = 1000000025;//更新我的简介失败
    public static final int ERROR_IMAGEFORM = 1000000026;//更新我的简介失败
    public static final int ERROR_UINFONAMENULL = 1000000027;//用户真实姓名为空
    public static final int ERROR_PHONENUMBEREXIST = 1000000028;//手机号码已注册
    public static final int ERROR_USERINFOPASS = 1000000029;//用户详细信息已注册
    public static final int ERROR_EMAILCODE = 1000000030;//邮箱格式错误
    public static final int ERROR_INSTERMESSAGE = 1000000031;//发表微博失败
    public static final int ERROR_COMMENTMESSAGES = 1000000032;//评论微博失败
    public static final int ERROR_TRANSPONDMESSAGES = 1000000033;//转发微博失败
    public static final int ERROR_ATFRIEND = 1000000034;//添加用户关注失败
    public static final int ERROR_ADDFRIEND = 1000000035;//添加好友失败
    public static final int ERROR_PERMISSION = 1000000036;//此消息不容许转发
    public static final int ERROR_PRAISE = 1000000037;//赞一个失败
    public static final int ERROR_DELETEMESSAGE = 1000000038;//删除微博失败
    public static final int ERROR_DELETECOMMETNMSG = 1000000039;//删除微博评论失败
    public static final int ERROR_NOPERMISSION = 1000000040;//没有删除微博评论的权限
    public static final int ERROR_PREPEATPRAISE = 1000000041;//用户对一条微博只能赞一次
    public static final int ERROR_STRANGER = 1000000042;//关系为陌生人
    public static final int ERROR_OLDPASSWORD = 1000000043;//原密码输入错误
    public static final int ERROR_PASSWORDEQUALS = 1000000044;//两次密码不一致
    public static final int ERROR_ADDRESSBOOKNULL = 1000000045;//通讯录好友为空

    private ErrorMsg() {
    }

    private ErrorMsg(String error, String error_description, String error_uri) {
        this.error = error;
        this.error_description = error_description;
        this.error_uri = error_uri;
    }
    private String error;
    private String error_description;
    private String error_uri;

    public static ErrorMsg error(String error) {
        return new ErrorMsg(error, null, null);
    }

    public static ErrorMsg error(String error, String error_description) {
        return new ErrorMsg(error, error_description, null);
    }

    public static ErrorMsg error(String error, String error_description, String error_uri) {
        return new ErrorMsg(error, error_description, error_uri);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return error_description;
    }

    public void setErrorDescription(String error_description) {
        this.error_description = error_description;
    }

    public String getErrorUri() {
        return error_uri;
    }

    public void setErrorUri(String error_uri) {
        this.error_uri = error_uri;
    }
}
