package com.hs.whocan.component.account.user.info.dao;

/**
 * Created by root on 14-3-16.
 */
public class User {
    private String userId;
    private String userName;
    private String phoneNo;
    private String mailAddress;
    private String gender;
    private String remark;
    private String portrait;

    public User() {
    }

    public User(String userId, String phoneNo, String userName) {
        this.userId = userId;
        this.phoneNo = phoneNo;
        this.userName = userName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
