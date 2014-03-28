package com.hs.whocan.domain.account.security.dao.entity;

import com.hs.whocan.domain.account.security.dao.Access;
import com.hs.whocan.domain.account.security.dao.AccessDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:20
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AccessRepository implements AccessDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Access findAccessInfoByPhoneNo(String phoneNo) {
        final String hql = "from AccessEntity e where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        AccessEntity accessEntity = (AccessEntity) query.uniqueResult();
        return accessEntity != null ? accessEntity.getAccess() : null;
    }

    @Override
    public String findAccessIdByToken(String accessToken) {
        final String hql = "from AccessEntity e where e.accessToken = :accessToken";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("accessToken", accessToken);
        AccessEntity entity = (AccessEntity) query.uniqueResult();
        if (null == entity || !isTokenAlive(entity))
            return null;
        return entity.getAccessId();
    }

    private boolean isTokenAlive(AccessEntity entity) {
        return (System.currentTimeMillis() - entity.getAccessTime().getTime()) <= entity.getAliveTime() ? true : false;
    }

    @Override
    public Access findAccessInfoByToken(String accessToken) {
        final String hql = "from AccessEntity e where e.accessToken = :accessToken";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("accessToken", accessToken);
        AccessEntity entity = (AccessEntity) query.uniqueResult();
        return entity != null ? entity.getAccess() : null;
    }

    @Override
    public void createAccessInfo(Access access) {
        sessionFactory.getCurrentSession().save(new AccessEntity(access));
    }

    @Override
    public Access findAccessIdByAuthCode(String phoneNo, int authCode) {
        final String hql = "from AccessEntity e where e.phoneNo = :phoneNo and e.authCode = :authCode";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        query.setInteger("authCode", authCode);
        AccessEntity entity = (AccessEntity) query.uniqueResult();
        if (null != entity) {
            entity.setAccessTime(new Date());
            sessionFactory.getCurrentSession().save(entity);
            return entity.getAccess();
        }
        return null;
    }

    @Override
    public Access findAccessInfoByUserId(String userId) {
        final String hql = "from AccessEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        AccessEntity entity = (AccessEntity) query.uniqueResult();
        return entity != null ? entity.getAccess() : null;
    }

    @Override
    public void modifyAccessInfo(Access access) {
        sessionFactory.getCurrentSession().update(new AccessEntity(access));
    }

    @Override
    public void modifyAccessToken(String accessId, String accessToken) {
        final String hql = "update AccessEntity e set e.accessToken = :accessToken,e.accessTime = :accessTime where e.accessId = :accessId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("accessToken", accessToken);
        query.setTimestamp("accessTime", new Date());
        query.setString("accessId", accessId);
        query.executeUpdate();
    }
}
