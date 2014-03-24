package com.hs.pm.server.account.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class UserInvitation {
    private String invitationId;
    private String userId;
    private String friendId;

    public UserInvitation(){}
    public UserInvitation(String userId,String friendId){
        this.userId = userId;
        this.friendId = friendId;
    }

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
