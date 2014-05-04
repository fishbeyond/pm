package com.hs.whocan.service.session;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.NeedSignInService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope("prototype")
public class SessionModifyName extends NeedSignInService {
    private String name;
    private String sessionId;

    @Resource
    private SessionComponent sessionComponent;

    @Override
    @Transactional
    public Boolean execute(User user) {
        sessionComponent.modifySessionName(sessionId,name);
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
