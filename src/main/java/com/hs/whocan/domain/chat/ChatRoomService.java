package com.hs.whocan.domain.chat;

import com.hs.whocan.domain.chat.dao.*;
import com.hs.whocan.domain.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class ChatRoomService {
    @Resource
    private ChatRoomDao chatRoomDao;
    @Resource
    private ChatDao chatDao;
    @Resource
    private UUIDGenerator uuidGenerator;

    public String findPrivateRoom(String userId, String friendId) {
        String roomId1 = userId +"_"+ friendId;
        String roomId2 = friendId  +"_"+  userId;
        ChatRoom chatRoom = chatRoomDao.findChatRoomByUnionId(roomId1, roomId2);
        if (null == chatRoom) {
            chatRoom = new ChatRoom(roomId1, userId);
            chatRoomDao.createChatRoom(chatRoom);
            chatRoomDao.createChatRoomMapper(new ChatRoomMapper(roomId1, userId));
            chatRoomDao.createChatRoomMapper(new ChatRoomMapper(roomId1, friendId));
            return roomId1;
        }
        return chatRoom.getRoomId();
    }

    public void sendChat(Chat chat) {
        chat.setCreateTime(new Date());
        chatDao.createChat(chat);
    }

    public List<ChatRoom> findChatRoom(String userId) {
        return chatRoomDao.findChatRoomByUserId(userId);
    }

    public List<Chat> findChatByRoomId(String roomId) {
        return chatDao.findChatByRoomId(roomId);
    }

    public String addPeopleToChatRoom(String roomId, String userId, String[] userIds) {
        if(null==roomId){
            roomId = uuidGenerator.shortUuid();
            ChatRoom chatRoom = new ChatRoom(roomId, userId);
            chatRoomDao.createChatRoom(chatRoom);
        }
        for(String addUserId : userIds){
            ChatRoomMapper chatRoomMapper = new ChatRoomMapper(roomId, addUserId);
            chatRoomDao.createChatRoomMapper(chatRoomMapper);
        }
        return roomId;
    }

    public void deletePeopleFromChatRoom(String roomId, String deleteUserId) {
        chatRoomDao.deleteChatRoomMapperByUserId(roomId, deleteUserId);
    }

    public List<String> findUserIdInRoom(String roomId) {
        return chatRoomDao.findUserIdByRoomId(roomId);
    }

    public void deleteChat(String chatId) {
        chatDao.deleteChat(chatId);
    }
}
