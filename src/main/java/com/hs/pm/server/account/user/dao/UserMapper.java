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
    private String alias;

    public UserMapper() {
    }

    public UserMapper(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
