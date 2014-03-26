package com.hs.whocan.domain.chat.dao;

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

    public void modifyChatRoomName(ChatRoom chatRoom);

    public List<ChatRoom> findChatRoomByUserId(String userId);

    public void deleteChatRoom(String roomId);
}
