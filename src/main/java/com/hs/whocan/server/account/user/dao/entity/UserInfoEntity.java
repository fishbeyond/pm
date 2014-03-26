package com.hs.whocan.server.account.user.dao.entity;

import com.hs.whocan.server.account.user.dao.UserInfo;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user_info")
public class UserInfoEntity {
    private UserInfo userInfo;

    public UserInfoEntity() {
        this.userInfo = new UserInfo();
    }

    public UserInfoEntity(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Transient
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Id
    public String getUserId() {
        return userInfo.getUserId();
    }

    public void setUserName(String userName) {
        userInfo.setUserName(userName);
    }

    @Column
    public String getMailAddress() {
        return userInfo.getMailAddress();
    }

    @Column
    public String getUserName() {
        return userInfo.getUserName();
    }

    public void setPhoneNo(String phoneNo) {
        userInfo.setPhoneNo(phoneNo);
    }

    public void setMailAddress(String mailAddress) {
        userInfo.setMailAddress(mailAddress);
    }

    @Column(length = 15,nullable = false,unique = true)
    public String getPhoneNo() {
        return userInfo.getPhoneNo();
    }

    public void setUserId(String userId) {
        userInfo.setUserId(userId);
    }

    public void setRemark(String remark) {
        userInfo.setRemark(remark);
    }
    @Column
    public String getRemark() {
        return userInfo.getRemark();
    }
    @Column
    public String getGender() {
        return userInfo.getGender();
    }

    public void setGender(String gender) {
        userInfo.setGender(gender);
    }
}
