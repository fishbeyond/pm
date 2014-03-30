package com.hs.whocan.service.chat;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionSendMessage {
    @Resource
    private SessionComponent sessionComponent;
    private Message message;
    public boolean execute() {
        sessionComponent.sendMessage(message);
        return true;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
