package com.hs.whocan.domain.publicchat.dao;

import java.util.List;

/**
 * Created by fish on 14-3-17.
 */

public interface PublicChatDao {
    public void createPublicChat(PublicChat publicChat);

    public List<PublicChat> findPublicChatByProjectId(String projectId);
}
