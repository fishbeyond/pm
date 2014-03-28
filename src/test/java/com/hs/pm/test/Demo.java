package com.hs.pm.test;

import com.hs.whocan.domain.chat.dao.ChatRoom;
import com.hs.whocan.domain.chat.dao.ChatRoomMapper;
import com.hs.whocan.domain.chat.dao.entity.ChatRoomEntity;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by root on 14-3-2.
 */
@SpringApplicationContext({"classpath:META-INF/pm-context.xml"})
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class Demo {
//    @SpringBeanByType
//    private SessionFactory sessionFactory;
//    @Test
//    public void demo1() {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setRoomId("123");
//        HashSet<ChatRoomMapper> chatRoomMappers = new HashSet<ChatRoomMapper>();
//        ChatRoomMapper chatRoomMapper1 = new ChatRoomMapper();
//        ChatRoomMapper chatRoomMapper2 = new ChatRoomMapper();
//        chatRoomMappers.add(chatRoomMapper1);
//        chatRoomMappers.add(chatRoomMapper2);
//        chatRoom.setChatRoomMappers(chatRoomMappers);
//        sessionFactory.getCurrentSession().save(new ChatRoomEntity(chatRoom));
//    }
//
//    @Test
//    public void find() {
//        Object o = sessionFactory.getCurrentSession().get(ChatRoomEntity.class, "123");
//        System.out.println(o);
//    }
//
//    @Test
//    public void delete(){
//        String hql = "delete from ChatRoomMapper m where m.roomId = :roomId";
//        Query query = getSession().createQuery(hql);
//        query.setString("roomId", "1");
//        query.executeUpdate();
//    }
//
//    private Session getSession(){
//       return sessionFactory.getCurrentSession();
//    }
//    @Test
//    public void findChatRoomByUserId() {
//        final String hql = "from ChatRoomEntity e where e.roomId in (select m.roomId from ChatRoomMapper m where m.userId = :userId)";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setString("userId", "1");
//        List<ChatRoomEntity> entities = (List<ChatRoomEntity>) query.list();
//        List<ChatRoom> chatRoomList = new ArrayList<ChatRoom>();
//        for (ChatRoomEntity chatRoomEntity : entities) {
//            chatRoomList.add(chatRoomEntity.getChatRoom());
//        }
//    }
}
