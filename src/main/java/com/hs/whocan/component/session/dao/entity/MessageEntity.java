package com.hs.whocan.component.session.dao.entity;


import com.hs.whocan.component.session.dao.Message;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fish on 14-3-17.
 */
@Entity
@Table(name = "message")
public class MessageEntity {
    private Message message;

    public MessageEntity() {
        this.message = new Message();
    }

    public MessageEntity(Message message) {
        this.message = message;
    }

    @Transient
    public Message getMessage() {
        return this.message;
    }
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getMessageId() {
        return message.getMessageId();
    }

    public void setMessageId(String messageId) {
        message.setMessageId(messageId);
    }

    @Column
    public String getContent() {
        return message.getContent();
    }

    public void setContent(String content) {
        message.setContent(content);
    }

    public void setMessage(String message) {
        this.message.setContent(message);
    }

    public void setUserId(String userId) {
        message.setUserId(userId);
    }
    @Column
    public String getUserId() {
        return message.getUserId();
    }
    @Column
    public Date getCreateTime() {
        return message.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        message.setCreateTime(createTime);
    }
    @Column
    public String getRoomId() {
        return message.getRoomId();
    }

    public void setRoomId(String roomId) {
        message.setRoomId(roomId);
    }
}
