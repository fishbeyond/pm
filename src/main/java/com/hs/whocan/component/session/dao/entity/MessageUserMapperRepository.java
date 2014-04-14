package com.hs.whocan.component.session.dao.entity;

import com.hs.whocan.component.session.dao.MessageUserMapper;
import com.hs.whocan.component.session.dao.MessageUserMapperDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: fish
 */
@Service
public class MessageUserMapperRepository implements MessageUserMapperDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void createMessageUserMapper(MessageUserMapper messageUserMapper) {
        sessionFactory.getCurrentSession().save(new MessageUserMapperEntity(messageUserMapper));
    }

    @Override
    public void deleteReadMessage(String userId, String sessionId, Date receiveTime) {
        final String hql = "delete from MessageUserMapperEntity e where e.userId = :userId " +
                "and e.sessionId = :sessionId " +
                "and e.receiveTime <= :receiveTime";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("sessionId", sessionId);
        query.setTimestamp("receiveTime", receiveTime);
        query.executeUpdate();
    }

    @Override
    public int findUnreadNum(String userId) {
        final String hql = "select count(e.mapperId) from MessageUserMapperEntity e where e.userId = :userId ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        Long result = (Long) query.uniqueResult();
        return result.intValue();
    }
}
