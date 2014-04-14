package com.hs.whocan.component.session.dao;

import java.util.Date;

/**
 * User: fish
 */
public class MessageUserMapper {
    private String mapperId;
    private String userId;
    private String messageId;
    private String sessionId;
    private Date receiveTime;

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
