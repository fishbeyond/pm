package com.hs.whocan.service.session;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.SessionQuery;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.service.VerifySignInService;
import com.hs.whocan.service.session.dto.SessionInfo;
import com.hs.whocan.service.session.dto.SessionUserInfo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: fish
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SessionFindNewMessage extends VerifySignInService {

    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private SessionQuery sessionQuery;

    @Override
    @Transactional
    public SessionInfo execute(User user) {
        SessionInfo sessionInfo = new SessionInfo();
        List<Message> messages = sessionComponent.findNewMessage(userId);
        if (noNewMessage(sessionInfo, messages)) return sessionInfo;
        List<String> sessionIds = distinctSessionId(messages);
        List<Session> sessions = sessionComponent.findSessionByIds(sessionIds);
        List<SessionUserInfo> sessionUserInfos = new ArrayList<SessionUserInfo>();
        for (Session session : sessions) {
            SessionUserInfo sessionUserInfo = sessionQuery.querySessionUserInfo(userId, session);
            sessionUserInfos.add(sessionUserInfo);
        }

        sessionInfo.setMessages(messages);
        sessionInfo.setSessionUserInfos(sessionUserInfos);
        return sessionInfo;
    }

    private boolean noNewMessage(SessionInfo sessionInfo, List<Message> messages) {
        if (messages.size() == 0) {
            sessionInfo.setMessages(new ArrayList<Message>());
            sessionInfo.setSessionUserInfos(new ArrayList<SessionUserInfo>());
            return true;
        }
        return false;
    }

    private List<String> distinctSessionId(List<Message> messages) {
        Map<String, String> map = new HashMap<String, String>();
        for (Message message : messages) {
            map.put(message.getSessionId(), null);
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        List<String> sessionIds = new ArrayList<String>();
        for (Map.Entry<String, String> entry : entries) {
            sessionIds.add(entry.getKey());
        }
        return sessionIds;
    }
}
