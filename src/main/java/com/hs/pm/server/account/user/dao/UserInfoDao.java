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

    public void createUserMapper(UserMapper userMapper);

    public void modifyUserMapper(UserMapper userMapper);

    public UserMapper findUserMapper(String userId,String friendId);

    public void deleteUserMapper(String userId,String friendId);

    public List<UserInfo> findFriendByUserId(String userId);

    public List<UserInfo> findUserByProjectId(String projectId);

    public void modifyUserActive(String userId);

    public List<UserInfo> findFriendByPhoneNo(String userId,List<String> phoneNoList);
}
