package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.UserMapper;
import com.hs.pm.server.account.user.dao.UserMapperDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:52
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserMapperRepository implements UserMapperDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void createUserMapper(UserMapper userMapper) {
        sessionFactory.getCurrentSession().save(new UserMapperEntity(userMapper));
    }

    @Override
    public void modifyUserMapper(UserMapper userMapper) {
        sessionFactory.getCurrentSession().update(userMapper);
    }

    @Override
    public UserMapper findUserMapper(String userId, String friendId) {
        final String hql = "from UserMapperEntity e where e.userId = :userId and e.friendId = :friendId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("friendId", friendId);
        UserMapperEntity entity = (UserMapperEntity) query.uniqueResult();
        return entity != null ? entity.getUserMapper() : null;
    }

    @Override
    public void deleteUserMapper(String userId, String friendId) {
        final String hql = "delete from UserMapperEntity e where e.userId = :userId and e.friendId = :friendId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("friendId", friendId);
        query.executeUpdate();
    }
}
