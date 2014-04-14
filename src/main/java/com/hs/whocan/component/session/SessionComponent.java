package com.hs.whocan.component.session;

import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.account.user.info.dao.UserDao;
import com.hs.whocan.component.session.dao.*;
import com.hs.whocan.component.session.exception.SessionNotExistException;
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
    public void sendMessage(Message message, String userId) {
        messageDao.createMessage(message);
        distributeMessage(message, userId);
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
        List<String> userList = new ArrayList<String>();
        userList.addAll(userIds);
        if (null == sessionId) {
            sessionId = uuidGenerator.shortUuid();
            session = new Session(sessionId, userId);
            session.setType(SessionType.PUBLIC_SESSION);
            sessionDao.createSession(session);
            userList.add(userId);
        } else {
            session = sessionDao.findSessionById(sessionId);
        }
        if (null == session) {
            throw new SessionNotExistException();
        }
        relateSessionUser(sessionId, userList, userId);
        return session;
    }

    private void relateSessionUser(String sessionId, List<String> userIds, String operateUserId) {
        StringBuilder content = new StringBuilder();
        for (String addUserId : userIds) {
            SessionMapper sessionMapper = sessionDao.findSessionMapper(sessionId, addUserId);
            if (null == sessionMapper) {
                sessionMapper = new SessionMapper(sessionId, addUserId);
                sessionDao.createSessionMapper(sessionMapper);
                User user = userDao.findUserById(addUserId);
                content.append(user.getUserName() + ", ");
            }
        }
        content.append("被加入群");
        createSystemMessage(sessionId, operateUserId, content.toString(), "SYSTEM");
    }

    private void createSystemMessage(String sessionId, String fromUserId, String content, String msgType) {
        Message message = new Message();
        message.setContent(content);
        message.setCreateTime(new Date());
        message.setSessionId(sessionId);
        message.setFromUser(fromUserId);
        message.setMsgType(msgType);
        message.setMessageId(uuidGenerator.shortUuid());
        messageDao.createMessage(message);
        distributeMessage(message, null);
    }

    private void distributeMessage(Message message, String excludeUserId) {
        List<String> userIds = null;
        if (null == excludeUserId) {
            userIds = sessionDao.findUserIdInSession(message.getSessionId());
        } else {
            userIds = sessionDao.findUserIdInSessionExcludeOwn(message.getSessionId(), excludeUserId);
        }
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
    public void deleteUser(String sessionId, String userId, String deleteUserId) {
        sessionDao.deleteSessionMapperByUserId(sessionId, deleteUserId);
        User user = userDao.findUserById(deleteUserId);
        createSystemMessage(sessionId, userId, user.getUserName() + "被请出群", "SYSTEM");
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
        messageUserMapperDao.deleteReadMessage(userId, message.getSessionId(), message.getCreateTime());
    }

    public List<Session> findSessionByIds(List<String> sessionIds) {
        return sessionDao.findSessionByIds(sessionIds);
    }

    public void createSessionMapper(String sessionId, List<String> userIds) {
        for (String addUserId : userIds) {
            SessionMapper sessionMapper = sessionDao.findSessionMapper(sessionId, addUserId);
            if (null == sessionMapper) {
                sessionMapper = new SessionMapper(sessionId, addUserId);
                sessionDao.createSessionMapper(sessionMapper);
            }
        }
    }
}
