package com.hs.whocan.component.session.dao.entity;

import com.hs.whocan.component.account.user.linkman.LinkmanStatus;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.component.session.dao.MessageDao;
import com.hs.whocan.service.social.FriendInfo;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fish on 14-3-17.
 */
@Service
public class MessageRepository implements MessageDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void createMessage(Message message) {
        sessionFactory.getCurrentSession().save(new MessageEntity(message));
    }

    @Override
    public List<Message> findMessageBySessionId(String sessionId) {
        final String hql = "from MessageEntity e where e.sessionId = :sessionId order by e.createTime";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("sessionId", sessionId);
        List<MessageEntity> entities = (List<MessageEntity>) query.list();
        List<Message> list = new ArrayList<Message>();
        for (MessageEntity entity : entities) {
            list.add(entity.getMessage());
        }
        return list;
    }

    @Override
    public void deleteMessage(String messageId) {
        final String hql = "delete from MessageEntity e where e.messageId = :messageId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("messageId", messageId);
        query.executeUpdate();
    }

    @Override
    public Message findMessage(String messageId) {
        final String hql = "from MessageEntity e where e.messageId = :messageId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("messageId", messageId);
        MessageEntity entity = (MessageEntity) query.uniqueResult();
        return entity != null ? entity.getMessage() : null;
    }

    @Override
    public void deleteMessage(String userId, String sessionId, Date createTime) {
        final String hql = "delete from MessageEntity e where e.toUser = :userId and e.sessionId = :sessionId and e.createTime <= :createTime";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("sessionId", sessionId);
        query.setTimestamp("createTime", createTime);
        query.executeUpdate();
    }

    @Override
    public List<Message> findNewMessage(String userId) {
        final String sql = "select * from session_message e inner join message_user_mapper m on e.messageId = m.messageId and m.userId = :userId order by createTime";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(MessageEntity.class);
        sqlQuery.setString("userId", userId);
        List<MessageEntity> entities = (List<MessageEntity>) sqlQuery.list();
        List<Message> list = new ArrayList<Message>();
        for (MessageEntity entity : entities) {
            list.add(entity.getMessage());
        }
        return list;
    }

}
