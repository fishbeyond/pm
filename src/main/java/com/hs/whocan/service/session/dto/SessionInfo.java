package com.hs.whocan.service.session.dto;

import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.session.dao.Message;

import java.util.List;

/**
 * User: fish
 */
public class SessionInfo {
    private List<SessionUserInfo> sessionUserInfos;
    private List<Message> messages;

    public List<SessionUserInfo> getSessionUserInfos() {
        return sessionUserInfos;
    }

    public void setSessionUserInfos(List<SessionUserInfo> sessionUserInfos) {
        this.sessionUserInfos = sessionUserInfos;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
