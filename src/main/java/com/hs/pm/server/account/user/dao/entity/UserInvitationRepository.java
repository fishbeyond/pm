package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.UserInvitation;
import com.hs.pm.server.account.user.dao.UserInvitationDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:35
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserInvitationRepository implements UserInvitationDao{
    @Resource
    private SessionFactory sessionFactory;


    @Override
    public void createUserInvitation(UserInvitation userInvitation) {
        sessionFactory.getCurrentSession().save(new UserInvitationEntity(userInvitation));
    }

    @Override
    public void deleteUserInvitation(String userId, String friendId) {
        final String hql = "delete from UserInvitationEntity e where e.userId = :userId and e.friendId = :friend";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId",userId);
        query.setString("friend",friendId);
        query.executeUpdate();
    }

    @Override
    public void relateUserInvitation(String invitePhoneNo, String friendId) {
        final String hql = "update UserInvitationEntity e set e.friendId = :friendId where e.invitePhoneNo = :invitePhoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("invitePhoneNo",invitePhoneNo);
        query.setString("friendId",friendId);
        query.executeUpdate();
    }
}
