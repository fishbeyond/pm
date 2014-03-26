package com.hs.whocan.domain.user.dao.entity;

import com.hs.whocan.domain.user.dao.UserMapper;
import com.hs.whocan.domain.user.dao.UserMapperDao;
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
    public void modifyUserMapperAlias(UserMapper userMapper) {
        final String hql = "update UserMapperEntity e set e.alias = :alias where e.userId = :userId and e.friendId = :friendId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("alias",userMapper.getAlias());
        query.setString("userId", userMapper.getUserId());
        query.setString("friendId", userMapper.getFriendId());
        query.executeUpdate();
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
