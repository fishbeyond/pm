package com.hs.pm.user.dao;

import java.util.List;

/**
 * Created by root on 14-3-16.
 */
public interface UserDao {
    public String findUserIdByPhoneNo(String phoneNo);

    public String findUserNameOrPhoneNoById(String userId);

    public User findUserById(String userId);

    public User findUserByPhoneNo(String phoneNo);

    public void createUser(User user);

    public void modifyUser(User user);

    public void createUserMapper(UserMapper userMapper);

    public void modifyUserMapper(UserMapper userMapper);

    public UserMapper findUserMapper(String userId,String friendId);

    public void deleteUserMapper(String userId,String friendId);

    public List<User> findFriendByUserId(String userId);

    public List<User> findUserByProjectId(String projectId);

    public void modifyUserActive(String userId);
}
