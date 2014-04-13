package com.hs.whocan.service.session;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.SessionQuery;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import com.hs.whocan.service.session.dto.SessionInfo;
import com.hs.whocan.service.session.dto.SessionUserInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private SessionQuery sessionQuery;

    @Override
    @Transactional
    public SessionInfo execute() {
        String readTag = securityComponent.findReadTag(userId);
        List<Session> sessions = sessionComponent.findSession(userId);
        List<Session> newSessions = new ArrayList<Session>();
        List<Message> messages = new ArrayList<Message>();
        for (Session session : sessions) {
            List<Message> list = sessionComponent.findNewMessage(session.getSessionId(), readTag);
            if(list.size()!=0){
                newSessions.add(session);
            }
            messages.addAll(list);
        }
        ArrayList<SessionUserInfo> sessionUserInfos = new ArrayList<SessionUserInfo>();
        for (Session session : newSessions) {
            SessionUserInfo sessionUserInfo = sessionQuery.querySessionInfo(userId, session);
            sessionUserInfos.add(sessionUserInfo);
        }
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setMessages(messages);
        sessionInfo.setSessionUserInfos(sessionUserInfos);
        return sessionInfo;
    }
}
