package com.hs.pm.publicChat.dao;


import com.hs.pm.publicChat.PublicChat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by fish on 14-3-17.
 */
@Entity
@Table(name = "public_chat")
public class PublicChatEntity {
    private PublicChat publicChat;

    public PublicChatEntity() {
        this.publicChat = new PublicChat();
    }

    public PublicChatEntity(PublicChat publicChat) {
        this.publicChat = publicChat;
    }

    public String getMessage() {
        return publicChat.getMessage();
    }

    public void setProjectId(String projectId) {
        publicChat.setProjectId(projectId);
    }

    public String getFromUserId() {
        return publicChat.getFromUserId();
    }

    public void setMessage(String message) {
        publicChat.setMessage(message);
    }

    public String getFromUserName() {
        return publicChat.getFromUserName();
    }

    public void setFromUserId(String fromUserId) {
        publicChat.setFromUserId(fromUserId);
    }

    public String getProjectId() {
        return publicChat.getProjectId();
    }

    public void setFromUserName(String fromUserName) {
        publicChat.setFromUserName(fromUserName);
    }

    public Date getCreateTime() {
        return publicChat.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        publicChat.setCreateTime(createTime);
    }
}
