package com.hs.pm.user.dao;

import java.util.List;

/**
 * Created by root on 14-3-16.
 */
public interface UserDao {
    public User findUserById(String userId);

    public User findUserByPhoneNo(String phoneNo);

    public void createUser(User user);

    public void modifyUser(User user);

    public String findUserIdByAuthCode(String phoneNo, int authCode);

    public List<User> findFriendByUserId(String userId);

    public void createUserMapper(String userId, String friendId);
}
