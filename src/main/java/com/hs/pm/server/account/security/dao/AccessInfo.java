package com.hs.pm.server.account.security.dao;

import java.util.Date;

/**
 * Created by fish on 14-3-17.
 */
public class AccessInfo {
    private String accessId;
    private String password;
    private Date accessTime;
    private long aliveTime;
    private String accessToken;

    private static long ALIVE_TIME = 1000 * 60 * 30;

    public AccessInfo() {
    }

    public AccessInfo(String accessId, String accessToken) {
        this.accessId = accessId;
        this.accessToken = accessToken;
        this.accessTime = new Date();
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public long getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(long aliveTime) {
        this.aliveTime = aliveTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
