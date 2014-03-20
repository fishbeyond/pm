package com.hs.pm.server.publicchat.dao.entity;

import com.hs.pm.server.publicchat.dao.PublicChat;
import com.hs.pm.server.publicchat.dao.PublicChatDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fish on 14-3-17.
 */
@Service
public class PublicChatRepository implements PublicChatDao {
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public void createPublicChat(PublicChat publicChat) {
        sessionFactory.getCurrentSession().save(new PublicChatEntity(publicChat));
    }

    @Override
    public List<PublicChat> findPublicChatByProjectId(String projectId) {
        final String hql = "from PublicChatEntity e where e.projectId = :projectId order by e.createTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("projectId",projectId);
        List<PublicChatEntity> entities = (List<PublicChatEntity>) query.list();
        List<PublicChat> publicChats = new ArrayList<PublicChat>();
        for(PublicChatEntity entity:entities){
            publicChats.add(entity.getPublicChat());
        }
        return publicChats;
    }
}
