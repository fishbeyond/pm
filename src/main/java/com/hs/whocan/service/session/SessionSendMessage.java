package com.hs.whocan.service.session;

import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.account.security.PushMessageComponent;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.component.session.dao.MessageDao;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SessionSendMessage extends WhoCanVerifyLoginService {
    private String content;
    private String sessionId;
    private String messageId;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private PushMessageComponent pushMessageComponent;
    @Resource
    private UserComponent userComponent;
    @Resource
    private MessageDao messageDao;

    @Transactional
    public Boolean execute() {
        Message message = new Message();
        message.setMessageId(messageId);
        message.setCreateTime(new Date());
        message.setContent(content);
        message.setFromUser(userId);
        message.setSessionId(userId);
        List<String> userIds = new ArrayList<String>();
        User user = userComponent.findUser(sessionId);
        if (null != user) {
            messageDao.createMessage(message);
            userIds.add(sessionId);
            sessionComponent.distributeMessage(message, userIds);
        } else {
            sessionComponent.sendMessage(message, userId);
            List<String> userIdList = sessionComponent.findUserIdInSession(sessionId);
            for (String userId : userIdList) {
                if (userId.equals(userId)) {
                } else {
                    userIds.add(userId);
                }
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
