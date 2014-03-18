package com.hs.pm.privateChat.dao.entity;

import com.hs.pm.privateChat.dao.PrivateChat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="private_chat")
public class PrivateChatEntity {
    private PrivateChat privateChat;
    public PrivateChatEntity(){
        this.privateChat = new PrivateChat();
    }
    public PrivateChatEntity(PrivateChat privateChat){
        this.privateChat=privateChat;
    }
    @Transient
    public PrivateChat getPrivateChat(){
        return privateChat;
    }
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getChatId() {
        return privateChat.getChatId();
    }
    @Column
    public String getToUserId() {
        return privateChat.getToUserId();
    }
    @Column
    public Date getCreateTime() {
        return privateChat.getCreateTime();
    }

    public void setChatId(String chatId) {
        privateChat.setChatId(chatId);
    }

    public void setFromUserId(String fromUserId) {
        privateChat.setFromUserId(fromUserId);
    }
    @Column
    public String getMessage() {
        return privateChat.getMessage();
    }

    public void setCreateTime(Date createTime) {
        privateChat.setCreateTime(createTime);
    }
    @Column
    public String getFromUserId() {
        return privateChat.getFromUserId();
    }

    public void setMessage(String message) {
        privateChat.setMessage(message);
    }

    public void setToUserId(String toUserId) {
        privateChat.setToUserId(toUserId);
    }
}
