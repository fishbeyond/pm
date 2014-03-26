package com.hs.whocan.domain.user.dao.entity;

import com.hs.whocan.service.FriendInfo;
import com.hs.whocan.service.LinkmanStatus;
import com.hs.whocan.domain.user.dao.UserInfo;
import com.hs.whocan.domain.user.dao.UserInfoDao;
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

    @Override
    public UserInfo findUserByIdAndPhoneNo(String userId, String phoneNo) {
        final String hql = "from UserInfoEntity e where e.userId = :userId and e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        query.setString("phoneNo",phoneNo);
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
    public String findPhoneNoByUserId(String userId) {
        final String hql = "select e.phoneNo from  UserInfoEntity e where e.userId = :userId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        return (String)query.uniqueResult();
    }

    @Override
    public List<FriendInfo> findFriendByUserId(String userId) {
        final String sql = "select :status as status, alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from (select m.alias,m.friendId from user_mapper m where m.userId = :userId) temp left join user_info i on temp.friendId = i.userId;";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId", userId);
        sqlQuery.setString("status", LinkmanStatus.FRIEND);
        return (List<FriendInfo>)sqlQuery.list();
    }

    @Override
    public List<FriendInfo> findFriendInvite(String userId) {
        final String sql = "select :status as status, null as alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from user_info u where u.userId in (select friendId from user_invitation m where m.userId = :userId)";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId",userId);
        sqlQuery.setString("status", LinkmanStatus.MY_INVITE);
        return  (List<FriendInfo>)sqlQuery.list();
    }

    @Override
    public List<FriendInfo> findFriendInvited(String userId) {
        final String sql = "select :status as status,null as alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from user_info u where u.userId in (select userId from user_invitation m where m.friendId = :userId)";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId",userId);
        sqlQuery.setString("status", LinkmanStatus.INVITE_ME);
        return  (List<FriendInfo>)sqlQuery.list();
    }

    @Override
    public List<FriendInfo> findFriendNotAdd(String userId) {
        final String sql = "select :status as status, null as alias,userId,gender,mailAddress,phoneNo,remark,userName " +
                "from (select phoneNo findPhone from linkman l where l.userId = :userId) temp inner join user_info u on temp.findPhone = u.phoneNo";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(FriendInfo.class);
        sqlQuery.setString("userId",userId);
        sqlQuery.setString("status", LinkmanStatus.NOT_FRIEND);
        return  (List<FriendInfo>)sqlQuery.list();
    }

}
