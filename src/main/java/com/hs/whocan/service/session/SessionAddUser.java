package com.hs.whocan.service.session;

import com.hs.whocan.component.account.security.PushMessageComponent;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.SessionQuery;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import com.hs.whocan.service.session.dto.SessionUserInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionAddUser extends WhoCanVerifyLoginService {
    private String sessionId;
    private String userIds;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private SessionQuery sessionQuery;
    @Resource
    private PushMessageComponent pushMessageComponent;

    public SessionUserInfo execute() {
        String[] userArray = userIds.split(",");
        List<String> userList = Arrays.asList(userArray);
        Session session = sessionComponent.addPeopleToSession(sessionId, userId, userList);
        SessionUserInfo sessionUserInfo = sessionQuery.querySessionInfo(userId, session);
        pushMessageComponent.push(userList, "您被加入群:" + sessionUserInfo.getSessionName());
        return sessionUserInfo;
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
