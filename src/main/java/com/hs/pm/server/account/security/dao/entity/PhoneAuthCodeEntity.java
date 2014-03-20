package com.hs.pm.server.account.security.dao.entity;

import com.hs.pm.server.account.security.dao.PhoneAuthCode;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "phone_auth_code")
public class PhoneAuthCodeEntity {
    private PhoneAuthCode phoneAuthCode;

    public PhoneAuthCodeEntity() {
        this.phoneAuthCode = new PhoneAuthCode();
    }

    public PhoneAuthCodeEntity(PhoneAuthCode phoneAuthCode) {
        this.phoneAuthCode = phoneAuthCode;
    }
    @Transient
    public PhoneAuthCode getPhoneAuthCode() {
        return phoneAuthCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPhoneId() {
        return phoneAuthCode.getPhoneId();
    }

    public void setPhoneNo(String phoneNo) {
        phoneAuthCode.setPhoneNo(phoneNo);
    }

    public void setPhoneId(int phoneId) {
        phoneAuthCode.setPhoneId(phoneId);
    }

    public void setAuthCode(int authCode) {
        phoneAuthCode.setAuthCode(authCode);
    }

    @Column
    public int getAuthCode() {
        return phoneAuthCode.getAuthCode();
    }

    @Column
    public String getPhoneNo() {
        return phoneAuthCode.getPhoneNo();
    }
}
