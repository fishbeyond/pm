package com.hs.pm.user.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by root on 14-3-16.
 */
public class User {
    private String userId;
    private String userName;
    private String phoneNo;
    private String mailAddress;
    private String password;
    private int authCode;
    private boolean isActive;

    public User() {
    }

    public User(String phoneNo,int authCode,boolean isActive) {
        this.phoneNo = phoneNo;
        this.authCode = authCode;
        this.isActive = isActive;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
