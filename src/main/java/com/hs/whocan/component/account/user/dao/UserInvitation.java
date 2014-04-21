package com.hs.whocan.component.account.user.dao;

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
    private String invitePhoneNo;

    public UserInvitation() {
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

    public String getInvitePhoneNo() {
        return invitePhoneNo;
    }

    public void setInvitePhoneNo(String invitePhoneNo) {
        this.invitePhoneNo = invitePhoneNo;
    }
}
