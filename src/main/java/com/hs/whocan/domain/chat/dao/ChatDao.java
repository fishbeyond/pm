package com.hs.whocan.domain.chat.dao;

import java.util.List;

/**
 * Created by fish on 14-3-17.
 */

public interface ChatDao {
    public void createPublicChat(Chat chat);

    public List<Chat> findPublicChatByProjectId(String projectId);
}
