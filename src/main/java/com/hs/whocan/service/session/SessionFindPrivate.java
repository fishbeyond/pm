package com.hs.whocan.service.session;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.WhoCanExecutor;
import com.hs.whocan.service.session.old.SessionInfo;
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
public class SessionFindPrivate extends WhoCanExecutor {

    private String friendId;

    @Resource
    protected SessionComponent sessionComponent;

    public SessionInfo execute() {
        return sessionComponent.findPrivateSession(userId, friendId);
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
