package com.hs.pm.user.dao.entity;

import com.hs.pm.user.dao.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user")
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
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
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

    @Column
    public String getPhoneNo() {
        return user.getPhoneNo();
    }

    public void setUserId(String userId) {
        user.setUserId(userId);
    }

    public void setIsActive(boolean isActive) {
        user.setActive(isActive);
    }
    @Column
    public boolean getIsActive() {
        return user.isActive();
    }
}
