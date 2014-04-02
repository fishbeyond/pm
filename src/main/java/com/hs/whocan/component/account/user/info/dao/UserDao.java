package com.hs.whocan.component.account.user.info.dao;

import com.hs.whocan.service.social.FriendInfo;

import java.util.List;

/**
 * Created by root on 14-3-16.
 */
public interface UserDao {
    public String findUserIdByPhoneNo(String phoneNo);

    public User findUserById(String userId);

    public User findUserByIdAndPhoneNo(String userId,String phoneNo);

    public User findUserByPhoneNo(String phoneNo);

    public void createUser(User user);

    public void modifyUser(User user);

    public List<User> findUserByProjectId(String projectId);

    public String findPhoneNoByUserId(String userId);

    public List<FriendInfo> findFriendByUserId(String userId);

    public List<FriendInfo> findFriendInvite(String userId);

    public List<FriendInfo> findFriendInvited(String userId);

    public List<FriendInfo> findFriendNotAdd(String userId);
}
