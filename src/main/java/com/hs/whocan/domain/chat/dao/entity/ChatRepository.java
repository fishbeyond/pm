package com.hs.whocan.domain.chat.dao.entity;

import com.hs.whocan.domain.chat.dao.Chat;
import com.hs.whocan.domain.chat.dao.ChatDao;
import com.hs.whocan.domain.chat.dao.ChatRoom;
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
    public void createChat(Chat chat) {
        sessionFactory.getCurrentSession().save(new ChatEntity(chat));
    }

    @Override
    public List<Chat> findChatByRoomId(String roomId) {
        final String hql = "from ChatEntity e where e.roomId = :roomId order by e.createTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("roomId",roomId);
        List<ChatEntity> entities = (List<ChatEntity>) query.list();
        List<Chat> list = new ArrayList<Chat>();
        for(ChatEntity entity : entities){
            list.add(entity.getChat());
        }
        return list;
    }

}
