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
import java.util.*;

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
        SessionInfo sessionInfo = new SessionInfo();
        List<Message> messages = sessionComponent.findNewMessage(userId);
        if (messages.size() == 0) {
            return sessionInfo;
        }
        Map<String, String> map = new HashMap<String, String>();
        for (Message message : messages) {
            map.put(message.getSessionId(), null);
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        List<String> sessionIds = new ArrayList<String>();
        for (Map.Entry<String, String> entry : entries) {
            sessionIds.add(entry.getKey());
        }
        List<Session> sessions = sessionComponent.findSessionByIds(sessionIds);
        ArrayList<SessionUserInfo> sessionUserInfos = new ArrayList<SessionUserInfo>();
        for (Session session : sessions) {
            SessionUserInfo sessionUserInfo = sessionQuery.querySessionInfo(userId, session);
            sessionUserInfos.add(sessionUserInfo);
        }

        sessionInfo.setMessages(messages);
        sessionInfo.setSessionUserInfos(sessionUserInfos);
        return sessionInfo;
    }
}
