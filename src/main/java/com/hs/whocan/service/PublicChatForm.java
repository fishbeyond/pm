package com.hs.whocan.service;

import com.hs.whocan.domain.publicchat.dao.PublicChat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class PublicChatForm {
    private PublicChat publicChat;

    public PublicChatForm() {
    }

    public PublicChatForm(PublicChat publicChat) {
        this.publicChat = publicChat;
    }

    public PublicChat getPublicChat() {
        return publicChat;
    }

    public String getChatId() {
        return publicChat.getChatId();
    }

    public void setCreateTime(Date createTime) {
        publicChat.setCreateTime(createTime);
    }

    public void setMessage(String message) {
        publicChat.setMessage(message);
    }

    public void setChatId(String chatId) {
        publicChat.setChatId(chatId);
    }

    public String getGroupId() {
        return publicChat.getGroupId();
    }

    public String getUserId() {
        return publicChat.getFromUserId();
    }

    public void setUserId(String fromUserId) {
        publicChat.setFromUserId(fromUserId);
    }

    public String getMessage() {
        return publicChat.getMessage();
    }

    public void setGroupId(String groupId) {
        publicChat.setGroupId(groupId);
    }

    public Date getCreateTime() {
        return publicChat.getCreateTime();
    }
}
