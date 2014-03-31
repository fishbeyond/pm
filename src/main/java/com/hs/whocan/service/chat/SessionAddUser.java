package com.hs.whocan.service.chat;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionAddUser extends WhoCanExecutor {
    protected String sessionId;
    private String userIds;
    @Resource
    protected SessionComponent sessionComponent;

    public String execute() {
        String[] userArray = userIds.split(",");
        return sessionComponent.addPeopleToChatRoom(sessionId,userId,userArray);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
