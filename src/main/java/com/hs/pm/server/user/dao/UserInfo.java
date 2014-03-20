package com.hs.pm.server.user.dao;

/**
 * Created by root on 14-3-16.
 */
public class UserInfo {
    private String userId;
    private String userName;
    private String phoneNo;
    private String mailAddress;
    private boolean isActive;

    public UserInfo() {
    }

    public UserInfo(String userId, String phoneNo) {
        this.userId = userId;
        this.phoneNo = phoneNo;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}