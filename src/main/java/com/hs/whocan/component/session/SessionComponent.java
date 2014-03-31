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
            session = new Session(roomId1, userId, null);
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

    public List<SessionInfo> findChatRoomInfo(String userId) {
        List<Session> sessions = sessionDao.findSessionByUserId(userId);
        List<SessionInfo> sessionInfos = new ArrayList<SessionInfo>();
        for (Session session : sessions) {
            SessionInfo sessionInfo = new SessionInfo();
            List<User> users = sessionDao.findSessionUserBySessionId(session.getSessionId());
            sessionInfo.setUserList(users);
            sessionInfo.setSession(session);
            sessionInfos.add(sessionInfo);
        }
        return sessionInfos;
    }

    public List<Message> findChatByRoomId(String roomId) {
        return messageDao.findMessageBySessionId(roomId);
    }

    public String addPeopleToSession(String sessionId, String userId, String[] userIds) {
        int userNum = userIds.length;
        if (null == sessionId) {
            String sessionName = "群聊(" + userNum + "人)";
            sessionId = uuidGenerator.shortUuid();
            Session session = new Session(sessionId, userId, sessionName);
            sessionDao.createSession(session);
        } else {
            int oldNum = sessionDao.findUserNumInSession(sessionId);
            String sessionName = "群聊(" + (oldNum+userNum) + "人)";
            sessionDao.modifySessionName(sessionId,sessionName);
        }
        for (String addUserId : userIds) {
            SessionMapper sessionMapper = new SessionMapper(sessionId, addUserId);
            sessionDao.createSessionMapper(sessionMapper);
        }
        return sessionId;
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
