package com.hs.whocan.service.session.old;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ChatService {

    @Resource
    private SessionComponent sessionComponent;

    public List<SessionInfo> findAllChatRoomInfo(String userId) {
        return sessionComponent.findSessionInfo(userId);
    }

    public List<User> findUserIdInRoom(String roomId){
        return sessionComponent.findUserIdInSession(roomId);
    }

    public SessionInfo addPeopleToChatRoom(String roomId,String userId,String userIds) {
        String[] userArray = userIds.split(",");
        return sessionComponent.addPeopleToSession(roomId, userId, userArray);
    }

    public boolean deletePeopleFromChatRoom(String roomId,String userId,String deleteUserId){
        sessionComponent.deleteUser(roomId, deleteUserId);
        return true;
    }

    public SessionInfo findPrivateRoom(String userId, String friendId) {
        return sessionComponent.findPrivateSession(userId, friendId);
    }

    public boolean sendChat(Message message) {
        sessionComponent.sendMessage(message);
        return true;
    }

    public List<Message> findChatByRoomId(String roomId) {
        return sessionComponent.findChatByRoomId(roomId);
    }

    public boolean deleteChat(String chatId){
        sessionComponent.deleteMessage(chatId);
        return true;
    }

}
