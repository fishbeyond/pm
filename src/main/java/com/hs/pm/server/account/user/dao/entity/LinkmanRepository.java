package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.Linkman;
import com.hs.pm.server.account.user.dao.LinkmanDao;
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
    public void createLinkman(List<Linkman> linkmanList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for(Linkman linkman : linkmanList){
            session.save(new LinkmanEntity(linkman));
        }
        transaction.commit();
        session.close();
    }
}
