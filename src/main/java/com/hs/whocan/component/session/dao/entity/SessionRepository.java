package com.hs.whocan.component.session.dao.entity;

import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.component.session.dao.SessionDao;
import com.hs.whocan.component.session.dao.SessionMapper;
import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.account.user.info.dao.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-27
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SessionRepository implements SessionDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void createSession(Session session) {
        sessionFactory.getCurrentSession().save(new SessionEntity(session));
    }

    @Override
    public void createSessionMapper(SessionMapper sessionMapper) {
        sessionFactory.getCurrentSession().save(sessionMapper);
    }

    @Override
    public void modifySessionName(String sessionId, String sessionName) {
        final String hql = "update SessionEntity e set e.sessionName = :sessionName where e.sessionId = :sessionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionName", sessionName);
        query.setString("sessionId", sessionId);
        query.executeUpdate();
    }

    @Override
    public Session findSessionByUnionId(String sessionId1, String sessionId2) {
        final String hql = "from SessionEntity e where e.sessionId = :sessionId1 or e.sessionId = :sessionId2";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId1", sessionId1);
        query.setString("sessionId2", sessionId2);
        SessionEntity entity = (SessionEntity) query.uniqueResult();
        return entity != null ? entity.getSession() : null;
    }

    @Override
    public void deleteSession(String sessionId) {
        final String hql = "delete from SessionEntity e where e.sessionId = :sessionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        query.executeUpdate();
    }

    @Override
    public void deleteSessionMapper(String sessionId) {
        final String hql = "delete from SessionMapper m where m.sessionId = :sessionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        query.executeUpdate();
    }

    @Override
    public List<Session> findSessionByUserId(String userId) {
        final String hql = "from SessionEntity e where e.sessionId in (select m.sessionId from SessionMapper m where m.userId = :userId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        List<SessionEntity> entities = (List<SessionEntity>) query.list();
        List<Session> sessionList = new ArrayList<Session>();
        for (SessionEntity sessionEntity : entities) {
            sessionList.add(sessionEntity.getSession());
        }
        return sessionList;
    }

    @Override
    public void deleteSessionMapperByUserId(String sessionId, String deleteUserId) {
        final String hql = "delete from SessionMapper e where e.sessionId = :sessionId and e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", deleteUserId);
        query.setString("sessionId", sessionId);
        query.executeUpdate();
    }

    @Override
    public List<String> findUserIdInSession(String sessionId) {
        final String hql = "select e.userId from SessionMapper e where e.sessionId = :sessionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        return (List<String>) query.list();
    }

    @Override
    public List<User> findUserInSession(String sessionId) {
        final String hql = "from UserEntity e where e.userId in (select m.userId from SessionMapper m where m.sessionId = :sessionId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        List<UserEntity> entities = (List<UserEntity>) query.list();
        List<User> users = new ArrayList<User>();
        for (UserEntity entity : entities) {
            users.add(entity.getUser());
        }
        return users;
    }

    @Override
    public List<String> findUserIdInSessionExcludeOwn(String sessionId, String userId) {
        final String hql = "select m.userId from SessionMapper m where m.sessionId = :sessionId and m.userId != :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        query.setString("userId", userId);
        return (List<String>) query.list();
    }

    @Override
    public int findUserNumInSession(String sessionId) {
        final String hql = "select count(e.sessionId) from SessionMapper e where e.sessionId = :sessionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        String result = query.uniqueResult().toString();
        return Integer.valueOf(result);
    }

    @Override
    public SessionMapper findSessionMapper(String sessionId, String userId) {
        final String hql = "from SessionMapper e where e.sessionId = :sessionId and e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        query.setString("userId", userId);
        return (SessionMapper) query.uniqueResult();
    }

    @Override
    public Session findSessionById(String sessionId) {
        final String hql = "from SessionEntity e where e.sessionId = :sessionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        SessionEntity entity = (SessionEntity) query.uniqueResult();
        return entity != null ? entity.getSession() : null;
    }

    @Override
    public List<Session> findSessionByIds(List<String> sessionIds) {
        final String hql = "from SessionEntity e where e.sessionId in :sessionIds";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameterList("sessionIds", sessionIds);
        List<SessionEntity> entities = (List<SessionEntity>) query.list();
        List<Session> sessions = new ArrayList<Session>();
        for (SessionEntity entity : entities) {
            sessions.add(entity.getSession());
        }
        return sessions;
    }

}
