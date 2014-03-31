package com.hs.whocan.component.session;

import com.hs.whocan.component.account.user.dao.UserDao;
import com.hs.whocan.component.session.dao.*;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.utils.UUIDGenerator;
import com.hs.whocan.service.session.old.SessionInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SessionComponent {
    @Resource
    private SessionDao sessionDao;
    @Resource
    private MessageDao messageDao;
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private UserDao userDao;

    @Transactional
    public SessionInfo findPrivateSession(String userId, String friendId) {
        String roomId1 = userId + "_" + friendId;
        String roomId2 = friendId + "_" + userId;
        Session session = sessionDao.findSessionByUnionId(roomId1, roomId2);
        if (null == session) {
            session = new Session(roomId1, userId);
            session.setType(SessionType.PRIVATE_SESSION);
            sessionDao.createSession(session);
            sessionDao.createSessionMapper(new SessionMapper(roomId1, userId));
            sessionDao.createSessionMapper(new SessionMapper(roomId1, friendId));
        }
        SessionInfo sessionInfo = createSessionInfo(userId, session);
        return sessionInfo;
    }

    @Transactional
    public void sendMessage(Message message) {
        messageDao.createMessage(message);
    }

    @Transactional
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
        setSessionInfoName(sessionInfo, userId, users);
        return sessionInfo;
    }

    private void setSessionInfoName(SessionInfo sessionInfo, String userId, List<User> users) {
        if (sessionInfo.getType().equals(SessionType.PRIVATE_SESSION)) {
            for (User user : users) {
                if (userId.equals(user.getUserId())) {
                } else {
                    sessionInfo.setSessionName(user.getUserName());
                }
            }
        } else if (null == sessionInfo.getSessionName() || "".equals(sessionInfo.getSessionName())) {
            int userNum = sessionDao.findUserNumInSession(sessionInfo.getSessionId());
            sessionInfo.setSessionName("群聊(" + userNum + "人)");
        }
    }

    @Transactional
    public List<Message> findChatByRoomId(String roomId) {
        return messageDao.findMessageBySessionId(roomId);
    }

    @Transactional
    public SessionInfo addPeopleToSession(String sessionId, String userId, String[] userIds) {
        Session session = null;
        if (null == sessionId) {
            sessionId = uuidGenerator.shortUuid();
            session = new Session(sessionId, userId);
            session.setType(SessionType.PUBLIC__SESSION);
            sessionDao.createSession(session);
            String[] oneUserId = new String[1];
            oneUserId[0] = userId;
            relateSessionUser(sessionId, oneUserId);
        } else {
            session = sessionDao.findSessionById(sessionId);
        }
        relateSessionUser(sessionId, userIds);
        return createSessionInfo(userId, session);
    }

    private void relateSessionUser(String sessionId, String[] userIds) {
        for (String addUserId : userIds) {

            SessionMapper sessionMapper = sessionDao.findSessionMapper(sessionId, addUserId);
            if (null == sessionMapper) {
                sessionMapper = new SessionMapper(sessionId, addUserId);
                sessionDao.createSessionMapper(sessionMapper);
                createSessionSystemMessage(sessionId, addUserId, "被加入群");
            }
        }
    }

    private void createSessionSystemMessage(String sessionId, String userId, String content) {
        User user = userDao.findUserById(userId);
        Message message = new Message();
        message.setCreateTime(new Date());
        message.setSessionId(sessionId);
        message.setContent(user.getUserName() + content);
        message.setUserId("SYSTEM");
        messageDao.createMessage(message);
    }


    @Transactional
    public void deleteUser(String sessionId, String deleteUserId) {
        sessionDao.deleteSessionMapperByUserId(sessionId, deleteUserId);
        User userById = userDao.findUserById(deleteUserId);
        createSessionSystemMessage(sessionId, deleteUserId,"被请出群");
    }

    @Transactional
    public List<User> findUserIdInSession(String sessionId) {
        return sessionDao.findSessionUserBySessionId(sessionId);
    }

    @Transactional
    public void deleteMessage(String chatId) {
        messageDao.deleteMessage(chatId);
    }
}
