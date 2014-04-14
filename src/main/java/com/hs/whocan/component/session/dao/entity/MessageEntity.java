package com.hs.whocan.component.session.dao.entity;


import com.hs.whocan.component.session.dao.Message;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fish on 14-3-17.
 */
@Entity
@Table(name = "session_message")
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

    @Column
    public Date getCreateTime() {
        return message.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        message.setCreateTime(createTime);
    }

    @Column
    public String getSessionId() {
        return message.getSessionId();
    }

    public void setSessionId(String sessionId) {
        message.setSessionId(sessionId);
    }

    @Column
    public String getMsgType() {
        return message.getMsgType();
    }

    public void setMsgType(String msgType) {
        message.setMsgType(msgType);
    }

    public void setFormUser(String formUser) {
        message.setFormUser(formUser);
    }

    @Column
    public String getFormUser() {
        return message.getFormUser();
    }
}
