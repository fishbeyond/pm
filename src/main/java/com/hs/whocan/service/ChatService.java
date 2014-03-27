package com.hs.whocan.service;

import com.hs.whocan.domain.chat.ChatRoomService;
import com.hs.whocan.domain.chat.dao.Chat;
import com.hs.whocan.domain.chat.dao.ChatRoom;
import org.springframework.stereotype.Service;

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
public class ChatService {

    @Resource
    private ChatRoomService chatRoomService;

    public List<ChatRoom> findAllChatRoom(String userId) {
        return chatRoomService.findChatRoom(userId);
    }

    public String addPeopleToChatRoom(String roomId,String userId,String[] userIds) {
        return chatRoomService.addPeopleToChatRoom(roomId,userId,userIds);
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

}
