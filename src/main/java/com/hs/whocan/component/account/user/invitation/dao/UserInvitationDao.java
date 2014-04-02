package com.hs.whocan.component.account.user.invitation.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:30
 * To change this template use File | Settings | File Templates.
 */
public interface UserInvitationDao {
    public void createUserInvitation(UserInvitation userInvitation);

    public List<UserInvitation> findUserInvitation(String userId, String friendId);

    public void deleteUserInvitation(String userId, String friendId);

    public void relateUserInvitationByPhoneNo(String phoneNo, String friendId);
}
