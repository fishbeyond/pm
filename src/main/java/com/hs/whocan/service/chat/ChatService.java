package com.hs.whocan.service.chat;

import com.hs.whocan.domain.chat.ChatRoomService;
import com.hs.whocan.domain.chat.dao.Chat;
import com.hs.whocan.domain.chat.dao.ChatRoom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private ChatRoomService chatRoomService;

    public List<ChatRoom> findAllChatRoom(String userId) {
        return chatRoomService.findChatRoom(userId);
    }

    public List<String> findUserIdInRoom(String roomId){
        return chatRoomService.findUserIdInRoom(roomId);
    }

    public String addPeopleToChatRoom(String roomId,String userId,String userIds) {
        String[] userArray = userIds.split(",");
        return chatRoomService.addPeopleToChatRoom(roomId,userId,userArray);
    }

    public boolean deletePeopleFromChatRoom(String roomId,String userId,String deleteUserId){
        chatRoomService.deletePeopleFromChatRoom(roomId,deleteUserId);
        return true;
    }

    public String findPrivateRoom(String userId, String friendId) {
        return chatRoomService.findPrivateRoom(userId, friendId);
    }

    public boolean sendChat(Chat chat) {
        chatRoomService.sendChat(chat);
        return true;
    }

    public List<Chat> findChatByRoomId(String roomId) {
        return chatRoomService.findChatByRoomId(roomId);
    }

    public boolean deleteChat(String chatId){
        chatRoomService.deleteChat(chatId);
        return true;
    }

}
