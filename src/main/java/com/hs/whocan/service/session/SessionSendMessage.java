package com.hs.whocan.service.session;

import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.account.security.PushMessageComponent;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.service.WhocanNeedLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionSendMessage extends WhocanNeedLoginService {
    private String content;
    private String sessionId;
    private String messageId;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private PushMessageComponent pushMessageComponent;

    public Boolean execute() {
        Message message = new Message();
        message.setMessageId(messageId);
        message.setCreateTime(new Date());
        message.setContent(content);
        message.setUserId(userId);
        message.setSessionId(sessionId);
        sessionComponent.sendMessage(message);
        List<User> users = sessionComponent.findUserIdInSession(sessionId);
        List<String> userIds = new ArrayList<String>();
        for (User user : users) {
            if (userId.equals(user.getUserId())) {
            } else {
                userIds.add(user.getUserId());
            }
        }
        pushMessageComponent.push(userIds, "您有新的消息");
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

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
