package com.hs.whocan.component.session.dao;

import com.hs.whocan.component.account.user.dao.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public interface SessionDao {
    public void createSession(Session session);

    public void createSessionMapper(SessionMapper sessionMapper);

    public void modifySessionName(Session session);

    public Session findSessionByUnionId(String sessionId1, String sessionId2);

    public void deleteSession(String sessionId);

    public void deleteSessionMapper(String sessionId);

    public List<Session> findSessionByUserId(String userId);

    public void deleteSessionMapperByUserId(String sessionId, String deleteUserId);

    public List<String> findUserIdBySessionId(String sessionId);

    public List<User> findSessionUserBySessionId(String sessionId);
}
