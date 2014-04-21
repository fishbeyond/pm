package com.hs.whocan.component.account.user.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * info: fish
 * Date: 14-3-12
 * Time: 下午1:22
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DeviceTokenDao {
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

    public List<String> findToken(String userId) {
        final String hql = "select t.token from DeviceToken t where t.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        return (List<String>) query.list();
    }

    public List<DeviceToken> findDeviceToken(List<String> userIds) {
        for (String userId : userIds) {
        }
        if (userIds.size() == 0) {
            return new ArrayList<DeviceToken>();
        }
        final String hql = "from DeviceToken t where t.userId in ( :userIds )";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameterList("userIds", userIds);
        return (List<DeviceToken>) query.list();
    }

    public DeviceToken findDeviceToken(String userId, String token) {
        final String hql = "from DeviceToken t where t.userId = :userId and t.token = :token";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("token", token);
        Object result = query.uniqueResult();
        return result != null ? (DeviceToken) result : null;
    }

    public void modifyDeviceToken(DeviceToken deviceToken) {
        final String hql = "update DeviceToken t set t.token = :token where t.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("token", deviceToken.getToken());
        query.setString("userId", deviceToken.getUserId());
        query.executeUpdate();
    }

    public List<DeviceToken> findDeviceToken(String userId) {
        final String hql = "from DeviceToken t where t.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        return (List<DeviceToken>) query.list();
    }
}
