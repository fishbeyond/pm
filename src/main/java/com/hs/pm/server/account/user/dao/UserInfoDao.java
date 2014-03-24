package com.hs.pm.server.account.user.dao;

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

    public List<UserInfo> findFriendByUserId(String userId);

    public List<UserInfo> findUserByProjectId(String projectId);

    public List<UserInfo> findFriendByPhoneNo(String userId,List<String> phoneNoList);

    public String findPhoneNoByUserId(String userId);
}
