package com.hs.whocan.domain.user;

import com.hs.whocan.service.FriendInfo;
import com.hs.whocan.domain.user.dao.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class UserMapperService {
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserInvitationDao userInvitationDao;
    @Resource
    private UserMapperDao userMapperDao;
    @Resource
    private LinkmanDao linkmanDao;

    public void createLinkman(String userId,String[] phones) {
        linkmanDao.createLinkman(userId,phones);
    }

    public void addFriendAlreadyRegister(String userId, String friendId) {
        if(0!=userInvitationDao.findUserInvitation(friendId,userId).size()){
            confirmFriend(userId,friendId);
        }
        UserInvitation userInvitation = new UserInvitation();
        userInvitation.setUserId(userId);
        userInvitation.setFriendId(friendId);
        userInvitationDao.createUserInvitation(userInvitation);
    }

    public void addFriendNoRegister(String userId, String phoneNo) {
        UserInvitation userInvitation = new UserInvitation();
        userInvitation.setUserId(userId);
        userInvitation.setInvitePhoneNo(phoneNo);
        userInvitationDao.createUserInvitation(userInvitation);
    }

    private void confirmFriend(String userId, String friendId) {
        userInvitationDao.deleteUserInvitation(friendId, userId);
        userMapperDao.createUserMapper(new UserMapper(userId, friendId));
        userMapperDao.createUserMapper(new UserMapper(friendId, userId));
    }

    public void modifyUserMapperAlias(UserMapper userMapper) {
        userMapperDao.modifyUserMapperAlias(userMapper);
    }

    public List<FriendInfo> findFriendUserId(String userId) {
        return userInfoDao.findFriendByUserId(userId);
    }

    public List<FriendInfo> findFriendInvite(String userId){
        return userInfoDao.findFriendInvite(userId);
    }

    public List<FriendInfo> findFriendInvited(String userId) {
        return userInfoDao.findFriendInvited(userId);
    }

    public List<FriendInfo> findFriendNotAdd(String userId) {
        return userInfoDao.findFriendNotAdd(userId);
    }

    private FriendInfo transform2FriendInfo(UserInfo userInfo) {
        FriendInfo friendInfo = new FriendInfo();
        BeanUtils.copyProperties(userInfo, friendInfo);
        return friendInfo;
    }

    public List<UserInfo> findUserByProjectId(String projectId) {
        return userInfoDao.findUserByProjectId(projectId);
    }

    public void deleteFriend(String userId, String friendId) {
        userMapperDao.deleteUserMapper(userId,friendId);
        userMapperDao.deleteUserMapper(friendId,userId);
    }
}
