package com.hs.whocan.domain.chat.dao.entity;

import com.hs.whocan.domain.chat.dao.ChatRoom;
import com.hs.whocan.domain.chat.dao.ChatRoomMapper;
import com.hs.whocan.domain.user.dao.UserMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 下午7:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "chat_room")
public class ChatRoomEntity {
    private ChatRoom chatRoom;

    public ChatRoomEntity() {
        chatRoom = new ChatRoom();
    }

    public ChatRoomEntity(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Transient
    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    @Id
    public String getRoomId() {
        return chatRoom.getRoomId();
    }

    public void setRoomName(String roomName) {
        chatRoom.setRoomName(roomName);
    }

    @Column
    public String getRoomName() {
        return chatRoom.getRoomName();
    }

    public void setCreateTime(Date createTime) {
        chatRoom.setCreateTime(createTime);
    }

    public void setUserId(String userId) {
        chatRoom.setUserId(userId);
    }

    public void setRoomId(String roomId) {
        chatRoom.setRoomId(roomId);
    }

    @Column
    public Date getCreateTime() {
        return chatRoom.getCreateTime();
    }

    @Column
    public String getUserId() {
        return chatRoom.getUserId();
    }
    @OneToMany(targetEntity =ChatRoomMapper.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "roomId")
    public Set<ChatRoomMapper> getChatRoomMappers() {
        return chatRoom.getChatRoomMappers();
    }

    public void setChatRoomMappers(Set<ChatRoomMapper> chatRoomMappers) {
        chatRoom.setChatRoomMappers(chatRoomMappers);
    }
}
