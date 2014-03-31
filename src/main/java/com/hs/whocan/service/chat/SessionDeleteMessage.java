package com.hs.whocan.service.chat;

import com.hs.whocan.component.session.SessionComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午7:03
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionDeleteMessage {
    private String messageId;
    @Resource
    private SessionComponent sessionComponent;
    public boolean execute(){
        sessionComponent.deleteChat(messageId);
        return true;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
