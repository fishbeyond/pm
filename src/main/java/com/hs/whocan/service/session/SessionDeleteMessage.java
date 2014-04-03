package com.hs.whocan.service.session;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.WhoCanExecutor;
import com.hs.whocan.service.WhocanFilterExecutor;
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
public class SessionDeleteMessage extends WhocanFilterExecutor {
    private String messageId;
    @Resource
    private SessionComponent sessionComponent;

    public Boolean execute() {
        sessionComponent.deleteMessage(messageId);
        return true;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
