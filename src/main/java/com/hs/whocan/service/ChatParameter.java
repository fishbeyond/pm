package com.hs.whocan.service;

import com.hs.whocan.domain.chat.dao.Chat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class ChatParameter {
    private Chat chat;
    public ChatParameter() {
    }

    public ChatParameter(Chat chat) {
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }

    public String getChatId() {
        return chat.getChatId();
    }

    public void setCreateTime(Date createTime) {
        chat.setCreateTime(createTime);
    }

    public void setMessage(String message) {
        chat.setMessage(message);
    }

    public void setChatId(String chatId) {
        chat.setChatId(chatId);
    }

    public void setUserId(String userId) {
        chat.setUserId(userId);
    }

    public String getUserId() {
        return chat.getUserId();
    }

    public String getMessage() {
        return chat.getMessage();
    }

    public String getRoomId() {
        return chat.getRoomId();
    }

    public void setRoomId(String roomId) {
        chat.setRoomId(roomId);
    }

    public Date getCreateTime() {
        return chat.getCreateTime();
    }
}
