package com.hs.whocan.component.account.security.device.dao;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * info: fish
 * Date: 14-3-12
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="security_deviceToken")
public class DeviceToken {
    private int tokenId;
    private String userId;
    private String token;
    private int unreadNum;
    public DeviceToken(){}

    public DeviceToken(String userId, String token,int unreadNum) {
        this.userId = userId;
        this.token = token;
        this.unreadNum = unreadNum;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTokenId() {
        return tokenId;
    }
    @Column(nullable = false)
    public String getUserId() {
        return userId;
    }
    @Column(nullable = false)
    public String getToken() {
        return token;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Transient
    public int getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }
}
