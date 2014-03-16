package com.hs.pm.user;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by root on 14-3-16.
 */
@Service
public class UserDao {
    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private Session session;

    public UserDao() {
        this.session = sessionFactory.getCurrentSession();
    }

    public User findUserByPhoneNo(int phoneNo) {
        final String hql = "from User where phoneNo=:phoneNo";
        Query query = session.createQuery(hql);
        return (User) query.uniqueResult();
    }


    public void createUser(User phoneNo) {
        session.save(phoneNo);
    }
}
