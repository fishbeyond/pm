package com.hs.whocan.domain.chat.dao.entity;

import com.hs.whocan.domain.chat.dao.Chat;
import com.hs.whocan.domain.chat.dao.ChatDao;
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
public class ChatRepository implements ChatDao {
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public void createPublicChat(Chat chat) {
        sessionFactory.getCurrentSession().save(new ChatEntity(chat));
    }

    @Override
    public List<Chat> findPublicChatByProjectId(String projectId) {
        final String hql = "from PublicChatEntity e where e.projectId = :projectId order by e.createTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("projectId",projectId);
        List<ChatEntity> entities = (List<ChatEntity>) query.list();
        List<Chat> chats = new ArrayList<Chat>();
        for(ChatEntity entity:entities){
            chats.add(entity.getChat());
        }
        return chats;
    }
}
