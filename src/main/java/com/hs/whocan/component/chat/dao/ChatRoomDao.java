package com.hs.whocan.component.chat.dao;

import com.hs.whocan.component.account.user.dao.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public interface ChatRoomDao {
    public void createChatRoom(ChatRoom chatRoom);

    public void createChatRoomMapper(ChatRoomMapper chatRoomMapper);

    public void modifyChatRoomName(ChatRoom chatRoom);

    public ChatRoom findChatRoomByUnionId(String roomId1, String roomId2);

    public void deleteChatRoom(String roomId);

    public void deleteChatRoomMapper(String roomId);

    public List<ChatRoom> findChatRoomByUserId(String userId);

    public void deleteChatRoomMapperByUserId(String roomId, String deleteUserId);

    public List<String> findUserIdByRoomId(String roomId);

    public List<User> findChatRoomUserByRoomId(String roomId);
}
