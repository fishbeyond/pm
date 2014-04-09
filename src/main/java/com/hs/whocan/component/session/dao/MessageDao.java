package com.hs.whocan.component.session.dao;

import java.util.Date;
import java.util.List;

/**
 * Created by fish on 14-3-17.
 */

public interface MessageDao {
    public void createMessage(Message message);

    public List<Message> findMessageBySessionId(String sessionId);

    public void deleteMessage(String messageId);

    public List<Message> findNewMessageBySessionId(String sessionId, Date updateTimestamp);
}
