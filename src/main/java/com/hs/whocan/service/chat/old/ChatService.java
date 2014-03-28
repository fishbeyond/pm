package com.hs.whocan.service.chat.old;

import com.hs.whocan.component.chat.ChatRoomComponent;
import com.hs.whocan.component.chat.dao.Chat;
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
    private ChatRoomComponent chatRoomComponent;

    public List<ChatRoomInfo> findAllChatRoomInfo(String userId) {
        return chatRoomComponent.findChatRoomInfo(userId);
    }

    public List<String> findUserIdInRoom(String roomId){
        return chatRoomComponent.findUserIdInRoom(roomId);
    }

    public String addPeopleToChatRoom(String roomId,String userId,String userIds) {
        String[] userArray = userIds.split(",");
        return chatRoomComponent.addPeopleToChatRoom(roomId,userId,userArray);
    }

    public boolean deletePeopleFromChatRoom(String roomId,String userId,String deleteUserId){
        chatRoomComponent.deletePeopleFromChatRoom(roomId,deleteUserId);
        return true;
    }

    public ChatRoomInfo findPrivateRoom(String userId, String friendId) {
        return chatRoomComponent.findPrivateRoom(userId, friendId);
    }

    public boolean sendChat(Chat chat) {
        chatRoomComponent.sendChat(chat);
        return true;
    }

    public List<Chat> findChatByRoomId(String roomId) {
        return chatRoomComponent.findChatByRoomId(roomId);
    }

    public boolean deleteChat(String chatId){
        chatRoomComponent.deleteChat(chatId);
        return true;
    }

}
