package com.hs.whocan.service.chat.old;

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
        return sessionComponent.findChatRoomInfo(userId);
    }

    public List<String> findUserIdInRoom(String roomId){
        return sessionComponent.findUserIdInRoom(roomId);
    }

    public String addPeopleToChatRoom(String roomId,String userId,String userIds) {
        String[] userArray = userIds.split(",");
        return sessionComponent.addPeopleToChatRoom(roomId,userId,userArray);
    }

    public boolean deletePeopleFromChatRoom(String roomId,String userId,String deleteUserId){
        sessionComponent.deletePeopleFromChatRoom(roomId,deleteUserId);
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
        sessionComponent.deleteChat(chatId);
        return true;
    }

}
