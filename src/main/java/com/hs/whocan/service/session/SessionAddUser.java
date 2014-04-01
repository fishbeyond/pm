package com.hs.whocan.service.session;

import com.hs.whocan.component.push.PushMessageComponent;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.SessionQuery;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionAddUser implements WhoCanExecutor {
    private String sessionId;
    private String userIds;
    private String userId;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private SessionQuery sessionQuery;
    @Resource
    private PushMessageComponent pushMessageComponent;

    public SessionInfo execute() {
        String[] userArray = userIds.split(",");
        Session session = sessionComponent.addPeopleToSession(sessionId, userId, userArray);
        SessionInfo sessionInfo = sessionQuery.querySessionInfo(userId, session);
        pushMessageComponent.push(Arrays.asList(userArray),"您被加入群"+sessionInfo.getSessionName());
        return sessionInfo;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
