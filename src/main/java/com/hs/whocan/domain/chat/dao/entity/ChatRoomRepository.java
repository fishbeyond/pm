package com.hs.whocan.domain.chat.dao.entity;

import com.hs.whocan.domain.chat.dao.Chat;
import com.hs.whocan.domain.chat.dao.ChatRoom;
import com.hs.whocan.domain.chat.dao.ChatRoomDao;
import com.hs.whocan.domain.chat.dao.ChatRoomMapper;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-27
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ChatRoomRepository implements ChatRoomDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void createChatRoom(ChatRoom chatRoom) {
        sessionFactory.getCurrentSession().save(new ChatRoomEntity(chatRoom));
    }

    @Override
    public void createChatRoomMapper(ChatRoomMapper chatRoomMapper) {
        sessionFactory.getCurrentSession().save(chatRoomMapper);
    }

    @Override
    public void modifyChatRoomName(ChatRoom chatRoom) {
        final String hql = "update ChatRoomEntity e set e.roomName = :roomName where e.roomId = :roomId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("roomName", chatRoom.getRoomName());
        query.setString("roomId", chatRoom.getRoomId());
        query.executeUpdate();
    }

    @Override
    public ChatRoom findChatRoomById(String roomId1, String roomId2) {
        final String hql = "from ChatRoomEntity e where e.roomId = :roomId1 or e.roomId = :roomId2";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("roomId1", roomId1);
        query.setString("roomId2", roomId2);
        ChatRoomEntity entity = (ChatRoomEntity) query.uniqueResult();
        return entity != null ? entity.getChatRoom() : null;
    }

    @Override
    public void deleteChatRoom(String roomId) {
        final String hql = "delete from ChatRoomEntity e where e.roomId = :roomId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("roomId",roomId);
        query.executeUpdate();
    }

    @Override
    public void deleteChatRoomMapper(String roomId) {
        final String hql = "delete from ChatRoomMapper m where m.roomId = :roomId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("roomId",roomId);
        query.executeUpdate();
    }

    @Override
    public List<ChatRoom> findChatRoomByUserId(String userId) {
        final String hql = "from ChatRoomEntity e where e.roomId in (select m.roomId from ChatRoomMapper m where m.userId = :userId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        List<ChatRoomEntity> entities = (List<ChatRoomEntity>) query.list();
        List<ChatRoom> chatRoomList = new ArrayList<ChatRoom>();
        for(ChatRoomEntity chatRoomEntity : entities){
            chatRoomList.add(chatRoomEntity.getChatRoom());
        }
        return chatRoomList;
    }

}
