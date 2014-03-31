package com.hs.whocan.component.session;

import com.hs.whocan.component.account.user.dao.UserDao;
import com.hs.whocan.component.session.dao.*;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.utils.UUIDGenerator;
import com.hs.whocan.service.chat.old.SessionInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class SessionComponent {
    @Resource
    private SessionDao sessionDao;
    @Resource
    private MessageDao messageDao;
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private UserDao userDao;

    public SessionInfo findPrivateSession(String userId, String friendId) {
        String roomId1 = userId + "_" + friendId;
        String roomId2 = friendId + "_" + userId;
        Session session = sessionDao.findSessionByUnionId(roomId1, roomId2);
        SessionInfo sessionInfo = new SessionInfo();
        if (null == session) {
            session = new Session(roomId1, userId);
            sessionDao.createSession(session);
            sessionDao.createSessionMapper(new SessionMapper(roomId1, userId));
            sessionDao.createSessionMapper(new SessionMapper(roomId1, friendId));
        }
        sessionInfo.setSession(session);
        return sessionInfo;
    }

    public void sendMessage(Message message) {
        messageDao.createMessage(message);
    }

    public List<SessionInfo> findSessionInfo(String userId) {
        List<Session> sessions = sessionDao.findSessionByUserId(userId);
        List<SessionInfo> sessionInfos = new ArrayList<SessionInfo>();
        for (Session session : sessions) {
            SessionInfo sessionInfo = createSessionInfo(userId, session);
            sessionInfos.add(sessionInfo);
        }
        return sessionInfos;
    }

    private SessionInfo createSessionInfo(String userId, Session session) {
        SessionInfo sessionInfo = new SessionInfo();
        List<User> users = sessionDao.findSessionUserBySessionId(session.getSessionId());
        sessionInfo.setUserList(users);
        sessionInfo.setSession(session);
        setPrivateSessionName(userId, sessionInfo, users);
        return sessionInfo;
    }

    private void setPrivateSessionName(String userId, SessionInfo sessionInfo, List<User> users) {
        if (users.size() == 2) {
            for (User user : users) {
                if (userId.equals(user.getUserId())) {
                } else {
                    sessionInfo.setSessionName(user.getUserName());
                }
            }
        } else if (null == sessionInfo.getSessionName()||"".equals(sessionInfo.getSessionName())) {
            int userNum = sessionDao.findUserNumInSession(sessionInfo.getSessionId());
            sessionInfo.setSessionName("群聊(" + userNum + "人)");
        }
    }

    public List<Message> findChatByRoomId(String roomId) {
        return messageDao.findMessageBySessionId(roomId);
    }

    public SessionInfo addPeopleToSession(String sessionId, String userId, String[] userIds) {
        int userNum = userIds.length;
        Session session = null;
        if (null == sessionId) {
            sessionId = uuidGenerator.shortUuid();
            session = new Session(sessionId, userId);
            sessionDao.createSession(session);
            relateSessionUser(sessionId, userId);
        } else {
            session = sessionDao.findSessionById(sessionId);
        }
        for (String addUserId : userIds) {
            relateSessionUser(sessionId, addUserId);
        }
        return createSessionInfo(userId, session);
    }

    private void relateSessionUser(String sessionId, String userId) {
        SessionMapper sessionMapper = sessionDao.findSessionMapper(sessionId, userId);
        if (null == sessionMapper) {
            sessionMapper = new SessionMapper(sessionId, userId);
            sessionDao.createSessionMapper(sessionMapper);
        }
    }

    public void deletePeopleFromSession(String sessionId, String deleteUserId) {
        sessionDao.deleteSessionMapperByUserId(sessionId, deleteUserId);
    }

    public List<User> findUserIdInSession(String sessionId) {
        return sessionDao.findSessionUserBySessionId(sessionId);
    }

    public void deleteChat(String chatId) {
        messageDao.deleteMessage(chatId);
    }
}
