package com.hs.pm.server.account.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public class UserMapper {
    private String mapperId;
    private String userId;
    private String friendId;
    private boolean isConfirm;

    public UserMapper() {
    }

    public UserMapper(String userId, String friendId, boolean confirm) {
        this.userId = userId;
        this.friendId = friendId;
        isConfirm = confirm;
    }

    public String getMapperId() {
        return mapperId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }
}
