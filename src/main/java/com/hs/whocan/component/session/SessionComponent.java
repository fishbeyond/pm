package com.hs.whocan.component.session;

import com.hs.whocan.component.account.user.info.dao.UserDao;
import com.hs.whocan.component.session.dao.*;
import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.framework.utils.UUIDGenerator;
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
    public Session getPrivateSession(String userId, String friendId) {
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
        return session;
    }

    @Transactional
    public void sendMessage(Message message) {
        messageDao.createMessage(message);
    }

    public List<Session> findSession(String userId) {
        return sessionDao.findSessionByUserId(userId);
    }

    @Transactional
    public List<Message> findMessageBySession(String roomId) {
        return messageDao.findMessageBySessionId(roomId);
    }

    @Transactional
    public Session addPeopleToSession(String sessionId, String userId, List<String> userIds) {
        Session session = null;
        if (null == sessionId) {
            sessionId = uuidGenerator.shortUuid();
            session = new Session(sessionId, userId);
            session.setType(SessionType.PUBLIC_SESSION);
            sessionDao.createSession(session);
            List<String> oneUserId = new ArrayList<String>();
            oneUserId.add(userId);
            relateSessionUser(sessionId, oneUserId);
        } else {
            session = sessionDao.findSessionById(sessionId);
        }
        relateSessionUser(sessionId, userIds);
        return session;
    }

    private void relateSessionUser(String sessionId, List<String> userIds) {
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
        message.setMessageId(uuidGenerator.shortUuid());
        message.setCreateTime(new Date());
        message.setSessionId(sessionId);
        message.setContent(user.getUserName() + content);
        message.setUserId("SYSTEM");
        messageDao.createMessage(message);
    }


    @Transactional
    public void deleteUser(String sessionId, String deleteUserId) {
        sessionDao.deleteSessionMapperByUserId(sessionId, deleteUserId);
        createSessionSystemMessage(sessionId, deleteUserId, "被请出群");
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
