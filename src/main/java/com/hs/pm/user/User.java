package com.hs.pm.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by root on 14-3-16.
 */
@Entity
@Table(name = "user")
public class User {
    private String userId;
    private String userName;
    private int phoneNo;
    private String mailAddress;
    private String password;
    private int authCode;

    public User() {
    }

    public User(String userId,int phoneNo) {
        this.userId = userId;
        this.phoneNo = phoneNo;
    }

    @Id
    public String getUserId() {
        return userId;
    }

    @Column
    public String getUserName() {
        return userName;
    }

    @Column
    public int getPhoneNo() {
        return phoneNo;
    }

    @Column
    public String getMailAddress() {
        return mailAddress;
    }

    @Column
    public String getPassword() {
        return password;
    }

    @Column
    public int getAuthCode() {
        return authCode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNo(int phoneNo) {
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
}
