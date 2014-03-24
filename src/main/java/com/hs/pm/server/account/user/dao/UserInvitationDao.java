package com.hs.pm.server.account.user.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:30
 * To change this template use File | Settings | File Templates.
 */
public interface UserInvitationDao {
    public void createUserInvitation(UserInvitation userInvitation);

    public void deleteUserInvitation(String userId,String friendId);
}
