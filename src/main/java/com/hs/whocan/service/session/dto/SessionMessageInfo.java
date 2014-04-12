package com.hs.whocan.service.session.dto;

import com.hs.whocan.component.session.dao.Message;

import java.util.Date;
import java.util.List;

/**
 * User: fish
 */
public class SessionMessageInfo {
    private Date updateTimestamp;
    private List<Message> messages;

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
