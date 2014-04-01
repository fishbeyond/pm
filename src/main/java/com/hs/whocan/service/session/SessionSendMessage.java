package com.hs.whocan.service.session;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionSendMessage implements WhoCanExecutor {
    @Resource
    private SessionComponent sessionComponent;
    private String content;
    private String sessionId;
    private String userId;
    private String messageId;

    public Boolean execute() {
        Message message = new Message();
        message.setMessageId(messageId);
        message.setCreateTime(new Date());
        message.setContent(content);
        message.setUserId(userId);
        message.setSessionId(sessionId);
        sessionComponent.sendMessage(message);
        return true;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
