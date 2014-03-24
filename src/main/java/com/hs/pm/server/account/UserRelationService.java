package com.hs.pm.server.account;

import com.hs.pm.server.account.friend.FriendInfo;
import com.hs.pm.server.account.user.dao.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class UserRelationService {
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserInvitationDao userInvitationDao;
    @Resource
    private UserMapperDao userMapperDao;


    public void addFriendAlreadyRegister(String userId, String friendId) {
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

    public void confirmFriend(UserMapper userMapper) {
        userInvitationDao.deleteUserInvitation(userMapper.getUserId(), userMapper.getFriendId());
        userMapperDao.createUserMapper(userMapper);
    }

    public void modifyUserMapperAlias(UserMapper userMapper) {
        userMapperDao.modifyUserMapperAlias(userMapper);
    }

    public List<UserInfo> findFriendUserId(String userId) {
        List<UserInfo> friends = userInfoDao.findFriendByUserId(userId);
        List<FriendInfo> friendInfoList = new ArrayList<FriendInfo>();
        for (UserInfo userInfo : friends) {

        }
        return null;
    }

    private FriendInfo transform2FriendInfo(UserInfo userInfo) {
        FriendInfo friendInfo = new FriendInfo();
        BeanUtils.copyProperties(userInfo, friendInfo);
        return friendInfo;
    }


    public List<UserInfo> findUserByProjectId(String projectId) {
        return userInfoDao.findUserByProjectId(projectId);
    }

    public List<UserInfo> findFriendByPhoneNo(String userId, List<String> phoneNoList) {
        return userInfoDao.findFriendByPhoneNo(userId, phoneNoList);
    }

}
