package com.hs.pm.server.security.dao.entity;

import com.hs.pm.server.security.dao.AccessInfo;
import com.hs.pm.server.security.dao.AccessInfoDao;
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
public class AccessInfoRepository implements AccessInfoDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public AccessInfo findAccessInfoByPhoneNo(String phoneNo) {
        final String hql = "from AccessInfoEntity where phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        AccessInfoEntity accessInfoEntity = (AccessInfoEntity) query.uniqueResult();
        return accessInfoEntity != null ? accessInfoEntity.getAccessInfo() : null;
    }

    @Override
    public void createAccessInfo(AccessInfo accessInfo) {
        sessionFactory.getCurrentSession().save(new AccessInfoEntity(accessInfo));
    }

    @Override
    public AccessInfo findAccessIdByAuthCode(String phoneNo, int authCode) {
        final String hql = "from AccessInfoEntity where phoneNo = :phoneNo and authCode = :authCode";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        query.setInteger("authCode", authCode);
        AccessInfoEntity entity = (AccessInfoEntity) query.uniqueResult();
        if(null!=entity){
            entity.setAccessTime(new Date());
            sessionFactory.getCurrentSession().save(entity);
            return entity.getAccessInfo();
        }
        return null;
    }

    @Override
    public AccessInfo findAccessInfoByUserId(String userId) {
        final String hql = "from AccessInfoEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId",userId);
        AccessInfoEntity entity = (AccessInfoEntity) query.uniqueResult();
        return entity!=null?entity.getAccessInfo():null;
    }

    @Override
    public void modifyAccessInfo(AccessInfo accessInfo) {
        sessionFactory.getCurrentSession().update(new AccessInfoEntity(accessInfo));
    }
}
