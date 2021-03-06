package com.hs.whocan.service.session;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SessionFindUser extends VerifySignInService {

    private String sessionId;
    @Resource
    private SessionComponent sessionComponent;

    @Transactional
    public List<User> execute(User user) {
        return sessionComponent.findUserInSession(sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
