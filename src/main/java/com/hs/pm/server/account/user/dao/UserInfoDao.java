package com.hs.pm.server.account.user.dao;

import com.hs.pm.dto.FriendInfo;

import java.util.List;

/**
 * Created by root on 14-3-16.
 */
public interface UserInfoDao {
    public String findUserIdByPhoneNo(String phoneNo);

    public UserInfo findUserById(String userId);

    public UserInfo findUserByPhoneNo(String phoneNo);

    public void createUser(UserInfo userInfo);

    public void modifyUser(UserInfo userInfo);

    public List<UserInfo> findUserByProjectId(String projectId);

    public String findPhoneNoByUserId(String userId);

    public List<FriendInfo> findFriendByUserId(String userId);

    public List<FriendInfo> findFriendInvite(String userId);

    public List<FriendInfo> findFriendInvited(String userId);

    public List<FriendInfo> findFriendNotAdd(String userId);
}
