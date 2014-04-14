package com.hs.whocan.component.session.dao.entity;

import com.hs.whocan.component.session.dao.MessageUserMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * User: fish
 */
@Entity
@Table(name = "message_user_mapper")
public class MessageUserMapperEntity {
    private MessageUserMapper messageUserMapper;

    public MessageUserMapperEntity() {
        this.messageUserMapper = new MessageUserMapper();
    }

    public MessageUserMapperEntity(MessageUserMapper messageUserMapper) {
        this.messageUserMapper = messageUserMapper;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getMapperId() {
        return messageUserMapper.getMapperId();
    }

    public void setUserId(String userId) {
        messageUserMapper.setUserId(userId);
    }

    public void setMapperId(String mapperId) {
        messageUserMapper.setMapperId(mapperId);
    }

    public void setMessageId(String messageId) {
        messageUserMapper.setMessageId(messageId);
    }

    @Column
    public String getUserId() {
        return messageUserMapper.getUserId();
    }

    @Column
    public String getMessageId() {
        return messageUserMapper.getMessageId();
    }

    @Column
    public Date getReceiveTime() {
        return messageUserMapper.getReceiveTime();
    }

    public void setReceiveTime(Date receiveTime) {
        messageUserMapper.setReceiveTime(receiveTime);
    }

    public void setSessionId(String sessionId) {
        messageUserMapper.setSessionId(sessionId);
    }

    @Column
    public String getSessionId() {
        return messageUserMapper.getSessionId();
    }
}
