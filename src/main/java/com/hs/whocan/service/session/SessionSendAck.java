package com.hs.whocan.service.session;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.NeedSignInService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * User: fish
 */
@Service
@Scope("prototype")
public class SessionSendAck extends NeedSignInService {
    private String readTag;
    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private SessionComponent sessionComponent;

    @Override
    @Transactional
    public Boolean execute(User user) {
        sessionComponent.deleteReadMessage(userId,readTag);
        return true;
    }

    public String getReadTag() {
        return readTag;
    }

    public void setReadTag(String readTag) {
        this.readTag = readTag;
    }
}
