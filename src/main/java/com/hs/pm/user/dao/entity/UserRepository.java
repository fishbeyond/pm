package com.hs.pm.user.dao.entity;

import com.hs.pm.user.dao.User;
import com.hs.pm.user.dao.UserDao;
import com.hs.pm.user.dao.UserMapper;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserRepository implements UserDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public User findUserById(String userId) {
        final String hql = "from UserEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        UserEntity entity = (UserEntity) query.uniqueResult();
        return entity == null ? null : entity.getUser();
    }

    public User findUserByPhoneNo(String phoneNo) {
        final String hql = "from UserEntity e where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        UserEntity entity = (UserEntity) query.uniqueResult();
        return entity == null ? null : entity.getUser();
    }


    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(new UserEntity(user));
    }

    @Override
    public void modifyUser(User user) {
        sessionFactory.getCurrentSession().update(new UserEntity(user));
    }

    public User findUserIdByAuthCode(String phoneNo, int authCode) {
        final String hql = "from UserEntity e where e.phoneNo = :phoneNo and e.authCode = :authCode";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        query.setInteger("authCode", authCode);
        UserEntity entity = (UserEntity) query.uniqueResult();
        if(null!=entity){
            entity.setIsActive(true);
        }
        sessionFactory.getCurrentSession().save(entity);
        return entity != null ? entity.getUser() : null;
    }

    @Override
    public List<User> findFriendByUserId(String userId) {
        final String hql = "from UserEntity e where e.userId in (select m.friendId from UserMapperEntity m where m.userId = :userId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        List<UserEntity> entities = (List<UserEntity>) query.list();
        List<User> users = new ArrayList<User>();
        for (UserEntity userEntity : entities) {
            users.add(userEntity.getUser());
        }
        return users;
    }

    @Override
    public void createUserMapper(String userId, String friendId) {
        UserMapper userMapper = new UserMapper(userId, friendId, false);
        sessionFactory.getCurrentSession().save(new UserMapperEntity(userMapper));
    }


    @Override
    public List<User> findUserByProjectId(String projectId) {
        final String hql = "from UserEntity u  where u.userId in (select e.userId from ProjectUserMapperEntity e where e.projectId = :projectId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("projectId", projectId);
        List<UserEntity> entities = (List<UserEntity>) query.list();
        List<User> users = new ArrayList<User>();
        for (UserEntity entity : entities) {
            users.add(entity.getUser());
        }
        return users;
    }

}
