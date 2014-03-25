package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.Linkman;
import com.hs.pm.server.account.user.dao.LinkmanDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-25
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LinkmanRepository implements LinkmanDao {
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public void createLinkman(String userId,String[] phones) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for(String phoneNo : phones){
            String sql = "select linkmanId from LinkmanEntity e where e.userId = :userId and e.phoneNo = :phoneNo";
            Query query = session.createQuery(sql);
            query.setString("userId",userId);
            query.setString("phoneNo",phoneNo);
            if(0==query.list().size()){
                session.save(new LinkmanEntity(userId,phoneNo));
            }
        }
        transaction.commit();
        session.close();
    }

    @Override
    public List<Linkman> findLinkmanByUserId(String userId) {
        final String hql = "from LinkmanEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId",userId);
        return (List<Linkman>)query.list();
    }
}
