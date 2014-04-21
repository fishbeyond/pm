package com.hs.whocan.component.session;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.account.user.dao.UserDao;
import com.hs.whocan.component.session.dao.*;
import com.hs.whocan.component.session.exception.SessionNotExistException;
import com.hs.whocan.framework.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Resource
    private MessageUserMapperDao messageUserMapperDao;

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
    public void sendMessage(Message message, List<String> userIds) {
        messageDao.createMessage(message);
        distributeMessage(message, userIds);
    }


    public List<Session> findSession(String userId) {
        return sessionDao.findSessionByUserId(userId);
    }

    @Transactional
    public List<Message> findMessageBySession(String roomId) {
        return messageDao.findMessageBySessionId(roomId);
    }

    @Transactional
    public Session createSession(String sessionId, String userId) {
        Session session = null;
        if (null == sessionId) {
            sessionId = uuidGenerator.shortUuid();
            session = new Session(sessionId, userId);
            sessionDao.createSession(session);
        } else {
            session = sessionDao.findSessionById(sessionId);
        }
        if (null == session) {
            throw new SessionNotExistException();
        }
        return session;
    }

    @Transactional
    public void relateSessionUser(String sessionId, List<String> userIds) {
        for (String addUserId : userIds) {
            SessionMapper sessionMapper = sessionDao.findSessionMapper(sessionId, addUserId);
            if (null == sessionMapper) {
                sessionMapper = new SessionMapper(sessionId, addUserId);
                sessionDao.createSessionMapper(sessionMapper);
            }
        }
    }

    public List<String> findUserIdInSession(String sessionId, String excludeUserId) {
        List<String> userIds = null;
        if (null == excludeUserId) {
            userIds = sessionDao.findUserIdInSession(sessionId);
        } else {
            userIds = sessionDao.findUserIdInSessionExcludeOwn(sessionId, excludeUserId);
        }
        return userIds;
    }

    public void distributeMessage(Message message, List<String> userIds) {
        for (String userId : userIds) {
            MessageUserMapper messageUserMapper = new MessageUserMapper();
            messageUserMapper.setMapperId(uuidGenerator.shortUuid());
            messageUserMapper.setUserId(userId);
            messageUserMapper.setSessionId(message.getSessionId());
            messageUserMapper.setMessageId(message.getMessageId());
            messageUserMapper.setReceiveTime(message.getCreateTime());
            messageUserMapperDao.createMessageUserMapper(messageUserMapper);
        }
    }


    @Transactional
    public void deleteUser(String sessionId, String deleteUserId) {
        sessionDao.deleteSessionMapperByUserId(sessionId, deleteUserId);
    }

    @Transactional
    public List<User> findUserInSession(String sessionId) {
        return sessionDao.findUserInSession(sessionId);
    }

    @Transactional
    public void deleteMessage(String chatId) {
        messageDao.deleteMessage(chatId);
    }

    public List<Message> findNewMessage(String userId) {
        return messageDao.findNewMessage(userId);
    }

    public void deleteReadMessage(String userId, String readTag) {
        Message message = messageDao.findMessage(readTag);
        if (null != message) {
            messageUserMapperDao.deleteReadMessage(userId, message.getSessionId(), message.getCreateTime());
        }
    }

    public List<Session> findSessionByIds(List<String> sessionIds) {
        return sessionDao.findSessionByIds(sessionIds);
    }
}
