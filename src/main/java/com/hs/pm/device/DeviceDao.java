package com.hs.pm.device;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-12
 * Time: 下午1:22
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DeviceDao {
    @Resource
    private SessionFactory sessionFactory;
        public List<String> findAllDeviceToken(){
            final String hql = "select token from DeviceToken";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            return (List<String>)query.list();
        };
        public List<String> findDeviceTokenByUser(int userId){
            final String hql = "select token from DeviceToken where userId=:userId";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            return (List<String>)query.list();
        };
}
