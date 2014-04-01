package com.hs.whocan.component.push.devicetoken.dao;

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

    public void createDeviceToken(DeviceToken deviceToken) {
        sessionFactory.getCurrentSession().save(deviceToken);
    }

    public List<String> findAllDeviceToken() {
        final String hql = "select t.token from DeviceToken t";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (List<String>) query.list();
    }

    public List<String> findDeviceToken(String userId) {
        final String hql = "select t.token from DeviceToken t where t.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        return (List<String>) query.list();
    }

    public List<String> findDeviceToken(List<String> userIds) {
        final String hql = "select t.token from DeviceToken t where t.userId in ( :userIds )";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameterList("userIds", userIds);
        return (List<String>) query.list();
    }

    public DeviceToken findDeviceToken(String userId, String token) {
        final String hql = "select t.token from DeviceToken t where t.userId = :userId and t.token = :token";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("token", token);
        return (DeviceToken) query.uniqueResult();
    }

    public void modifyDeviceToken(DeviceToken oldDeviceToken) {
        sessionFactory.getCurrentSession().update(oldDeviceToken);
    }
}
