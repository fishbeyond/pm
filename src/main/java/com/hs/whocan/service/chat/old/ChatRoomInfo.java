package com.hs.whocan.service.chat.old;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.chat.dao.ChatRoom;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class ChatRoomInfo {
    private String roomId;
    private String userId;
    private String roomName;
    private Date createTime;
    private List<User> userList;

    public void setChatRoom(ChatRoom chatRoom) {
        this.roomId = chatRoom.getRoomId();
        this.userId = chatRoom.getUserId();
        this.roomName = chatRoom.getRoomName();
        this.createTime = chatRoom.getCreateTime();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
}
