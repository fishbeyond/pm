package com.hs.whocan.component.account.security.authcode.dao.entity;

import com.hs.whocan.component.account.security.authcode.dao.PhoneAuthCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "security_authCode")
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
    @Column
    public Date getCreateTime() {
        return phoneAuthCode.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        phoneAuthCode.setCreateTime(createTime);
    }
}
