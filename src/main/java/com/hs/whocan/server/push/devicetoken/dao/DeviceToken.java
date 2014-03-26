package com.hs.whocan.server.push.devicetoken.dao;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-12
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="device_token")
public class DeviceToken {
    private int tokenId;
    private String userId;
    private String token;
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
}
