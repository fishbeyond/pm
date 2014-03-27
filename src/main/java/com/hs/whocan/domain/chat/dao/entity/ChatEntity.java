package com.hs.whocan.domain.chat.dao.entity;


import com.hs.whocan.domain.chat.dao.Chat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fish on 14-3-17.
 */
@Entity
@Table(name = "chat")
public class ChatEntity {
    private Chat chat;

    public ChatEntity() {
        this.chat = new Chat();
    }

    public ChatEntity(Chat chat) {
        this.chat = chat;
    }

    @Transient
    public Chat getChat() {
        return this.chat;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getChatId() {
        return chat.getChatId();
    }

    public void setChatId(String chatId) {
        chat.setChatId(chatId);
    }

    @Column
    public String getMessage() {
        return chat.getMessage();
    }

    public void setMessage(String message) {
        chat.setMessage(message);
    }

    public void setUserId(String userId) {
        chat.setUserId(userId);
    }
    @Column
    public String getUserId() {
        return chat.getUserId();
    }
    @Column
    public Date getCreateTime() {
        return chat.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        chat.setCreateTime(createTime);
    }
    @Column
    public String getRoomId() {
        return chat.getRoomId();
    }

    public void setRoomId(String roomId) {
        chat.setRoomId(roomId);
    }
}
