package com.hs.whocan.server.privatechat.dao.entity;

import com.hs.whocan.server.privatechat.dao.PrivateChat;
import com.hs.whocan.server.privatechat.dao.PrivateChatDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PrivateChatRepository implements PrivateChatDao {
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public void createPrivateChat(PrivateChat privateChat) {
        sessionFactory.getCurrentSession().save(privateChat);
    }

    @Override
    public List<PrivateChat> findPrivateChat(String fromUserId, String toUserId) {
        final String hql = "from PrivateChatEntity e where e.fromUserId = :fromUserId and e.toUserId = :toUserId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("fromUserId",fromUserId);
        query.setString("toUserId",toUserId);
        List<PrivateChatEntity> entities = (List<PrivateChatEntity>) query.list();
        List<PrivateChat> privateChats = new ArrayList<PrivateChat>();
        for(PrivateChatEntity entity:entities){
            privateChats.add(entity.getPrivateChat());
        }
        return privateChats;
    }
}
