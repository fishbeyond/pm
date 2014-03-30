package com.hs.whocan.component.session;

import com.hs.whocan.component.session.dao.*;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.utils.UUIDGenerator;
import com.hs.whocan.service.chat.old.SessionInfo;
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
@Transactional
public class SessionComponent {
    @Resource
    private SessionDao sessionDao;
    @Resource
    private MessageDao messageDao;
    @Resource
    private UUIDGenerator uuidGenerator;

    public SessionInfo findPrivateSession(String userId, String friendId) {
        String roomId1 = userId + "_" + friendId;
        String roomId2 = friendId + "_" + userId;
        Session session = sessionDao.findSessionByUnionId(roomId1, roomId2);
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setChatRoom(session);
        if (null == session) {
            session = new Session(roomId1, userId);
            sessionDao.createSession(session);
            sessionDao.createSessionMapper(new SessionMapper(roomId1, userId));
            sessionDao.createSessionMapper(new SessionMapper(roomId1, friendId));
            return sessionInfo;
        }
        return sessionInfo;
    }

    public void sendMessage(Message message) {
        message.setCreateTime(new Date());
        messageDao.createMessage(message);
    }

    public List<SessionInfo> findChatRoomInfo(String userId) {
        List<Session> sessions = sessionDao.findSessionByUserId(userId);
        List<SessionInfo> sessionInfos = new ArrayList<SessionInfo>();
        for (Session session : sessions) {
            SessionInfo sessionInfo = new SessionInfo();
            List<User> users = sessionDao.findSessionUserBySessionId(session.getSessionId());
            sessionInfo.setUserList(users);
            sessionInfo.setChatRoom(session);
            sessionInfos.add(sessionInfo);
        }
        return sessionInfos;
    }

    public List<Message> findChatByRoomId(String roomId) {
        return messageDao.findMessageBySessionId(roomId);
    }

    public String addPeopleToChatRoom(String roomId, String userId, String[] userIds) {
        if (null == roomId) {
            roomId = uuidGenerator.shortUuid();
            Session session = new Session(roomId, userId);
            sessionDao.createSession(session);
        }
        for (String addUserId : userIds) {
            SessionMapper sessionMapper = new SessionMapper(roomId, addUserId);
            sessionDao.createSessionMapper(sessionMapper);
        }
        return roomId;
    }

    public void deletePeopleFromChatRoom(String roomId, String deleteUserId) {
        sessionDao.deleteSessionMapperByUserId(roomId, deleteUserId);
    }

    public List<String> findUserIdInRoom(String roomId) {
        return sessionDao.findUserIdBySessionId(roomId);
    }

    public void deleteChat(String chatId) {
        messageDao.deleteMessage(chatId);
    }
}
