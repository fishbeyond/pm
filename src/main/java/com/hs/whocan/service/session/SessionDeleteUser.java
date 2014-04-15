package com.hs.whocan.service.session;

import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.framework.utils.UUIDGenerator;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:19
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionDeleteUser extends WhoCanVerifyLoginService {

    private String sessionId;
    private String deleteUserId;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private UserComponent userComponent;
    @Resource
    private UUIDGenerator uuidGenerator;

    public Boolean execute() {
        sessionComponent.deleteUser(sessionId, deleteUserId);
        Message message = getDeleteUserMessage(deleteUserId);
        List<String> userIds = sessionComponent.findUserIdInSession(sessionId, null);
        sessionComponent.sendMessage(message, userIds);
        return true;
    }

    private Message getDeleteUserMessage(String deleteUserId) {
        User user = userComponent.findUserById(deleteUserId);
        Message message = new Message();
        message.setContent(user.getUserName() + "被请出群");
        message.setCreateTime(new Date());
        message.setSessionId(sessionId);
        message.setFromUser(userId);
        message.setMsgType("SYSTEM");
        message.setMessageId(uuidGenerator.shortUuid());
        return message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
