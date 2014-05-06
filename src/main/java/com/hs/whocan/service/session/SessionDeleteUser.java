package com.hs.whocan.service.session;

import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.utils.UUIDGenerator;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SessionDeleteUser extends VerifySignInService {

    private String sessionId;
    private String deleteUserIds;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private UserComponent userComponent;
    @Resource
    private UUIDGenerator uuidGenerator;

    public Boolean execute(User user) {
        String[] deleteIds = deleteUserIds.split(",");
        StringBuilder content = new StringBuilder();
        for(String deleteId :deleteIds){
            sessionComponent.deleteUser(sessionId, deleteId);
            content.append(userComponent.findUserById(deleteId).getUserName()+",");
        }
        content.append("被请出群");
        Message message = getDeleteUserMessage(content.toString());
        List<String> userIds = sessionComponent.findUserIdInSession(sessionId, null);
        sessionComponent.sendMessage(message, userIds);
        return true;
    }

    private Message getDeleteUserMessage(String content) {
        Message message = new Message();
        message.setContent(content);
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

    public String getDeleteUserIds() {
        return deleteUserIds;
    }

    public void setDeleteUserIds(String deleteUserIds) {
        this.deleteUserIds = deleteUserIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
