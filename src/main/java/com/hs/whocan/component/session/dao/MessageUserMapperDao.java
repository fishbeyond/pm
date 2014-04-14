package com.hs.whocan.component.session.dao;

import java.util.Date;

/**
 * User: fish
 */
public interface MessageUserMapperDao {
    public void createMessageUserMapper(MessageUserMapper messageUserMapper);

    public void deleteReadMessage(String userId, String sessionId, Date receiveTime);

    public int findUnreadNum(String userId);
}
