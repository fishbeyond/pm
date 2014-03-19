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
    public String findUserIdByPhoneNo(String phoneNo) {
        final String hql = "select e.userId from UserEntity e where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        return (String) query.uniqueResult();
    }

    @Override
    public String findUserNameOrPhoneNoById(String userId) {
        final String hql = "select userName,phoneNo from UserEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        Object[] result = (Object[]) query.uniqueResult();
        if (null != result) {
            return result[0] != null ? result[0].toString() : result[1].toString();
        }
        return null;
    }

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

    @Override
    public void modifyUserActive(String userId) {
        final String hql = "update UserEntity e set e.isActive=true where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId",userId);
        query.executeUpdate();
    }

}
