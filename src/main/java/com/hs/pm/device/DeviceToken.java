package com.hs.pm.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-12
 * Time: 下午3:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="device_token")
public class DeviceToken {
    private int tokenId;
    private int userId;
    private String token;
    @Id
    public int getTokenId() {
        return tokenId;
    }
    @Column
    public int getUserId() {
        return userId;
    }
    @Column
    public String getToken() {
        return token;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
