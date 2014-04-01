package com.hs.whocan.component.account.user;

import com.hs.whocan.component.account.user.linkman.dao.LinkmanDao;
import com.hs.whocan.service.social.FriendInfo;
import com.hs.whocan.component.account.user.dao.*;
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
public class UserMapperComponent {
    @Resource
    private UserDao userInfoDao;
    @Resource
    private UserInvitationDao userInvitationDao;
    @Resource
    private UserMapperDao userMapperDao;
    @Resource
    private LinkmanDao linkmanDao;

    @Transactional
    public void createLinkman(String userId,String[] phones) {
        linkmanDao.createLinkman(userId,phones);
    }

    public void addFriendAlreadyRegister(String userId, String friendId) {
        if(0!=userInvitationDao.findUserInvitation(friendId,userId).size()){
            confirmFriend(userId,friendId);
        } else {
            UserInvitation userInvitation = new UserInvitation();
            userInvitation.setUserId(userId);
            userInvitation.setFriendId(friendId);
            userInvitationDao.createUserInvitation(userInvitation);
        }
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

    private FriendInfo transform2FriendInfo(User user) {
        FriendInfo friendInfo = new FriendInfo();
        BeanUtils.copyProperties(user, friendInfo);
        return friendInfo;
    }

    public List<User> findUserByProjectId(String projectId) {
        return userInfoDao.findUserByProjectId(projectId);
    }

    public void deleteFriend(String userId, String friendId) {
        userMapperDao.deleteUserMapper(userId,friendId);
        userMapperDao.deleteUserMapper(friendId,userId);
    }
}
