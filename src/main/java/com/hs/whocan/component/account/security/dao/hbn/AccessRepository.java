package com.hs.whocan.component.account.security.dao.hbn;

import com.hs.whocan.component.account.security.dao.Access;
import com.hs.whocan.component.account.security.dao.AccessDao;
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
    public Access findAccessByToken(String accessToken) {
        final String hql = "from AccessEntity e where e.accessToken = :accessToken";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("accessToken", accessToken);
        AccessEntity entity = (AccessEntity) query.uniqueResult();
        return entity != null ? entity.getAccess() : null;
    }

    @Override
    public void createAccess(Access access) {
        sessionFactory.getCurrentSession().save(new AccessEntity(access));
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
