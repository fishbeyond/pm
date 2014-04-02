package com.hs.whocan.component.account.security.authcode.dao;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
public class PhoneAuthCode {
    private int phoneId;
    private String phoneNo;
    private int authCode;
    private Date createTime;

    public PhoneAuthCode() {
    }

    public PhoneAuthCode(String phoneNo, int authCode) {
        this.phoneNo = phoneNo;
        this.authCode = authCode;
        this.createTime = new Date();
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
