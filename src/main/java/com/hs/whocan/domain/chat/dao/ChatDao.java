package com.hs.whocan.domain.chat.dao;

import java.util.List;

/**
 * Created by fish on 14-3-17.
 */

public interface ChatDao {
    public void createChat(Chat chat);

    public List<Chat> findChatByRoomId(String roomId);

    public void deleteChat(String chatId);
}
