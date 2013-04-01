/*
 * Name   User.java
 * Author ZhangZhenli
 * Created on 2012-5-7, 15:48:40
 *
 * Copyright (c) 2012 NanJing YiWuXian Technology Co., Ltd. All rights reserved
 *
 */
package cn.mimail.txthandler;

import java.io.Serializable;

/**
 * 用户表PO
 *
 * @author ZhangZhenli
 */
public class User implements Serializable {

    private int userId = 123456789;
    private String userName = "zhangzhenli";
    private String userPassword = "password123456";
    private String phoneNumber = "13731863750";
    private int phoneNumberState = 0;

    public int getPhoneNumberState() {
        return phoneNumberState;
    }

    public void setPhoneNumberState(int phoneNumberState) {
        this.phoneNumberState = phoneNumberState;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
//        忽略用户表ID，用户唯一标示符为UserName
//        if (this.userId != other.userId) {
//            return false;
//        }
        if ((this.userName == null) ? (other.userName != null) : !this.userName.equals(other.userName)) {
            return false;
        }
        if ((this.userPassword == null) ? (other.userPassword != null) : !this.userPassword.equals(other.userPassword)) {
            return false;
        }
        if ((this.phoneNumber == null) ? (other.phoneNumber != null) : !this.phoneNumber.equals(other.phoneNumber)) {
            return false;
        }
        if(this.phoneNumberState != other.phoneNumberState){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
//        hash = 11 * hash + this.userId;
        hash = 11 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        hash = 11 * hash + (this.userPassword != null ? this.userPassword.hashCode() : 0);
        hash = 11 * hash + (this.phoneNumber != null ? this.phoneNumber.hashCode() : 0);
        hash = 11 * hash + this.phoneNumberState;
        return hash;
    }

    @Override
    public String toString() {
        return "User{" + "userId=。。。。" + ", userName=" + userName + ", userPassword=" + userPassword + ",phoneNumber" + phoneNumber + ",phoneNumberState" + phoneNumberState +'}';
    }
}
