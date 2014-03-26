package com.hs.whocan.service;

import com.hs.whocan.domain.user.UserRelationService;
import com.hs.whocan.domain.user.dao.UserInfo;
import com.hs.whocan.domain.user.dao.UserMapper;
import com.hs.whocan.domain.push.PushService;
import com.hs.whocan.domain.push.devicetoken.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14-3-16.
 */
@Service
public class UserRelationAction {
    @Resource
    private UserRelationService userRelationService;
    @Resource
    private DeviceService deviceService;
    @Resource
    private PushService pushService;

    public void uploadLinkMan(String userId, String phones) {
        String[] phoneArray = phones.split(",");
        userRelationService.createLinkman(userId, phoneArray);
    }

    public void addFriendAlreadyRegister(String userId, String friendId) {
        userRelationService.addFriendAlreadyRegister(userId, friendId);
//        List<String> deviceTokens = deviceService.findDeviceTokenByUser(friendId);
//        pushService.push(deviceTokens, "添加好友邀请");
    }

    public void addFriendNoRegister(String userId, String phoneNo) {
        userRelationService.addFriendNoRegister(userId, phoneNo);
    }

    public void modifyFriendAlias(UserMapper userMapper) {
        userRelationService.modifyUserMapperAlias(userMapper);
    }

    public void deleteFriend(String userId, String friendId) {
        userRelationService.deleteFriend(userId, friendId);
    }

    public List<FriendInfo> findAllRelationByUserId(String userId) {
        List<FriendInfo> alreadyFriends = userRelationService.findFriendUserId(userId);
        List<FriendInfo> inviteFriends = userRelationService.findFriendInvite(userId);
        List<FriendInfo> invitedFriends = userRelationService.findFriendInvited(userId);
        List<FriendInfo> notAddFriends = userRelationService.findFriendNotAdd(userId);
        ArrayList<FriendInfo> list = new ArrayList<FriendInfo>();
        list.addAll(alreadyFriends);
        list.addAll(inviteFriends);
        list.addAll(invitedFriends);
        list.addAll(notAddFriends);
        return list;
    }

    public List<UserInfo> findUserByProjectId(String projectId) {
        return userRelationService.findUserByProjectId(projectId);
    }
}
