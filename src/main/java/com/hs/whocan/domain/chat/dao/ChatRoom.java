package com.hs.whocan.domain.chat.dao;

import com.hs.whocan.domain.user.dao.UserMapper;

import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 下午7:34
 * To change this template use File | Settings | File Templates.
 */
public class ChatRoom {
    private String roomId;
    private String userId;
    private String roomName;
    private Date createTime;
    private Set<ChatRoomMapper> chatRoomMappers;

    public ChatRoom() {
    }

    public ChatRoom(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
        this.createTime = new Date();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<ChatRoomMapper> getChatRoomMappers() {
        return chatRoomMappers;
    }

    public void setChatRoomMappers(Set<ChatRoomMapper> chatRoomMappers) {
        this.chatRoomMappers = chatRoomMappers;
    }
}
