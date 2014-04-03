package com.hs.whocan.component.account.user.info.dao.entity;

import com.hs.whocan.component.account.user.info.dao.User;

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
public class UserEntity {
    private User user;

    public UserEntity() {
        this.user = new User();
    }

    public UserEntity(User user) {
        this.user = user;
    }

    @Transient
    public User getUser() {
        return user;
    }

    @Id
    public String getUserId() {
        return user.getUserId();
    }

    public void setUserName(String userName) {
        user.setUserName(userName);
    }

    @Column
    public String getMailAddress() {
        return user.getMailAddress();
    }

    @Column
    public String getUserName() {
        return user.getUserName();
    }

    public void setPhoneNo(String phoneNo) {
        user.setPhoneNo(phoneNo);
    }

    public void setMailAddress(String mailAddress) {
        user.setMailAddress(mailAddress);
    }

    @Column(length = 15,nullable = false,unique = true)
    public String getPhoneNo() {
        return user.getPhoneNo();
    }

    public void setUserId(String userId) {
        user.setUserId(userId);
    }

    public void setRemark(String remark) {
        user.setRemark(remark);
    }
    @Column
    public String getRemark() {
        return user.getRemark();
    }
    @Column
    public String getGender() {
        return user.getGender();
    }

    public void setGender(String gender) {
        user.setGender(gender);
    }

    public void setPortrait(String portrait) {
        user.setPortrait(portrait);
    }

    public String getPortrait() {
        return user.getPortrait();
    }
}
