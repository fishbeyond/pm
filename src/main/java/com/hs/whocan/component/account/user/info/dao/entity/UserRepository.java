package com.hs.whocan.component.account.user.info.dao.entity;

import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.account.user.info.dao.UserDao;
import com.hs.whocan.component.account.user.linkman.LinkmanStatus;
import com.hs.whocan.service.social.FriendInfo;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
    public User findUserById(String userId) {
        final String hql = "from UserEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        UserEntity entity = (UserEntity) query.uniqueResult();
        return entity == null ? null : entity.getUser();
    }

    @Override
    public User findUserByIdAndPhoneNo(String userId, String phoneNo) {
        final String hql = "from UserEntity e where e.userId = :userId and e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("phoneNo", phoneNo);
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
        final String hql = "update UserEntity e set e.gender = :gender,e.mailAddress = :mailAddress,e.portrait = :portrait, e.remark = :remark,e.userName = :userName where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("gender", user.getGender());
        query.setString("mailAddress", user.getMailAddress());
        query.setString("portrait", user.getPortrait());
        query.setString("remark", user.getRemark());
        query.setString("userName", user.getUserName());
        query.setString("userId", user.getUserId());
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
    public String findPhoneNoByUserId(String userId) {
        final String hql = "select e.phoneNo from  UserEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        return (String) query.uniqueResult();
    }

    @Override
    public List<FriendInfo> findFriendByUserId(String userId) {
        final String sql = "select :status as status, alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from (select m.alias,m.friendId from user_mapper m where m.userId = :userId) temp left join user_info i on temp.friendId = i.userId;";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId", userId);
        sqlQuery.setString("status", LinkmanStatus.FRIEND);
        return (List<FriendInfo>) sqlQuery.list();
    }

    @Override
    public List<FriendInfo> findFriendInvite(String userId) {
        final String sql = "select :status as status, null as alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from user_info u where u.userId in (select friendId from user_invitation m where m.userId = :userId)";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId", userId);
        sqlQuery.setString("status", LinkmanStatus.MY_INVITE);
        return (List<FriendInfo>) sqlQuery.list();
    }

    @Override
    public List<FriendInfo> findFriendInvited(String userId) {
        final String sql = "select :status as status,null as alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from user_info u where u.userId in (select userId from user_invitation m where m.friendId = :userId)";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId", userId);
        sqlQuery.setString("status", LinkmanStatus.INVITE_ME);
        return (List<FriendInfo>) sqlQuery.list();
    }

    @Override
    public List<FriendInfo> findFriendNotAdd(String userId) {
        final String sql = "select :status as status, null as alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from (select phoneNo findPhone from user_linkman l where l.userId = :userId) temp inner join user_info u on temp.findPhone = u.phoneNo and" +
                " u.userId not in (select friendId from user_mapper m where m.userId= :userId)";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId", userId);
        sqlQuery.setString("status", LinkmanStatus.NOT_FRIEND);
        return (List<FriendInfo>) sqlQuery.list();
    }

    @Override
    public void modifyPortrait(String userId, String portrait) {
        final String hql = "update user_info e set e.portrait = :portrait where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("portrait", portrait);
        query.executeUpdate();
    }

}
