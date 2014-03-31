package com.hs.whocan.service.session;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.SessionQuery;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionFindPrivate implements WhoCanExecutor {

    private String friendId;

    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private SessionQuery sessionQuery;
    private String userId;

    public SessionInfo execute() {
        Session session = sessionComponent.getPrivateSession(userId, friendId);
        return sessionQuery.querySessionInfo(userId, session);
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}