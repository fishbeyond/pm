package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.account.user.dao.UserInfoDao;
import com.hs.pm.server.account.user.dao.UserMapper;
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
public class UserInfoRepository implements UserInfoDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public String findUserIdByPhoneNo(String phoneNo) {
        final String hql = "select e.userId from UserInfoEntity e where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        return (String) query.uniqueResult();
    }


    @Override
    public UserInfo findUserById(String userId) {
        final String hql = "from UserInfoEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        UserInfoEntity entity = (UserInfoEntity) query.uniqueResult();
        return entity == null ? null : entity.getUserInfo();
    }

    public UserInfo findUserByPhoneNo(String phoneNo) {
        final String hql = "from UserInfoEntity e where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        UserInfoEntity entity = (UserInfoEntity) query.uniqueResult();
        return entity == null ? null : entity.getUserInfo();
    }


    public void createUser(UserInfo userInfo) {
        sessionFactory.getCurrentSession().save(new UserInfoEntity(userInfo));
    }

    @Override
    public void modifyUser(UserInfo userInfo) {
        sessionFactory.getCurrentSession().update(new UserInfoEntity(userInfo));
    }

    @Override
    public List<UserInfo> findFriendByUserId(String userId) {
        final String hql = "from UserInfoEntity e where e.userId in (select m.friendId from UserMapperEntity m where m.userId = :userId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        List<UserInfoEntity> entities = (List<UserInfoEntity>) query.list();
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for (UserInfoEntity userInfoEntity : entities) {
            userInfos.add(userInfoEntity.getUserInfo());
        }
        return userInfos;
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
    public List<UserInfo> findUserByProjectId(String projectId) {
        final String hql = "from UserInfoEntity u  where u.userId in (select e.userId from ProjectUserMapperEntity e where e.projectId = :projectId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("projectId", projectId);
        List<UserInfoEntity> entities = (List<UserInfoEntity>) query.list();
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for (UserInfoEntity entity : entities) {
            userInfos.add(entity.getUserInfo());
        }
        return userInfos;
    }

    @Override
    public void modifyUserActive(String userId) {
        final String hql = "update UserInfoEntity e set e.isActive=true where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.executeUpdate();
    }

    @Override
    public List<UserInfo> findFriendByPhoneNo(String userId, List<String> phoneNoList) {
        final String hql = "from UserInfoEntity e left join UserMapperEntity m on m.friendId =e.userId where m.userId = :userId and e.phoneNo in (:phoneNoList)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId",userId);
        query.setParameterList("phoneNoList",phoneNoList);
        List<UserInfoEntity> entities = (List<UserInfoEntity>) query.list();
        List result = new ArrayList<UserInfo>();
        for(UserInfoEntity entity:entities){
            result.add(entity.getUserInfo());
        }
        return result;
    }

}
