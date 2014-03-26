package com.hs.whocan.domain.publicchat.dao.entity;


import com.hs.whocan.domain.publicchat.dao.PublicChat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @Transient
    public PublicChat getPublicChat() {
        return this.publicChat;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getChatId() {
        return publicChat.getChatId();
    }

    public void setChatId(String chatId) {
        publicChat.setChatId(chatId);
    }

    @Column
    public String getMessage() {
        return publicChat.getMessage();
    }

    @Column
    public String getFromUserId() {
        return publicChat.getFromUserId();
    }

    public void setMessage(String message) {
        publicChat.setMessage(message);
    }

    public void setFromUserId(String fromUserId) {
        publicChat.setFromUserId(fromUserId);
    }

    @Column
    public String getGroupId() {
        return publicChat.getGroupId();
    }

    public void setGroupId(String groupId) {
        publicChat.setGroupId(groupId);
    }

    @Column
    public Date getCreateTime() {
        return publicChat.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        publicChat.setCreateTime(createTime);
    }
}
