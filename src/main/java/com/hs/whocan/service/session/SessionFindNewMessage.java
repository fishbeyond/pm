package com.hs.whocan.service.session;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import com.hs.whocan.service.session.dto.SessionMessageInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: fish
 */
@Service
@Scope("prototype")
public class SessionFindNewMessage extends WhoCanVerifyLoginService {

    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private SessionComponent sessionComponent;

    @Override
    @Transactional
    public SessionMessageInfo execute() {
        Date updateTimestamp = securityComponent.findTimestamp(userId);
        if(null==updateTimestamp){
            updateTimestamp = new Date(0);
        }
        List<Session> sessions = sessionComponent.findSession(userId);
        List<Message> messages = new ArrayList<Message>();
        for (Session session : sessions) {
            List<Message> list = sessionComponent.findNewMessage(session.getSessionId(), updateTimestamp);
            messages.addAll(list);
        }
        SessionMessageInfo sessionMessageInfo = new SessionMessageInfo();
        sessionMessageInfo.setUpdateTimestamp(new Date());
        sessionMessageInfo.setMessages(messages);
        return sessionMessageInfo;
    }
}
