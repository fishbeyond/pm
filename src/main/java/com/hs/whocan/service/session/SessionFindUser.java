package com.hs.whocan.service.session;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionFindUser implements WhoCanExecutor {

    private String sessionId;
    @Resource
    private SessionComponent sessionComponent;
    private String userId;

    @Transactional
    public List<User> execute() {
        return sessionComponent.findUserIdInSession(sessionId);
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
}
