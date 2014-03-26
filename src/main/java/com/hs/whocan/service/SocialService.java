package com.hs.whocan.service;

import com.hs.whocan.domain.user.UserMapperService;
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
public class SocialService {
    @Resource
    private UserMapperService userMapperService;
    @Resource
    private DeviceService deviceService;
    @Resource
    private PushService pushService;

    public void uploadLinkMan(String userId, String phones) {
        String[] phoneArray = phones.split(",");
        userMapperService.createLinkman(userId, phoneArray);
    }

    public void addFriendAlreadyRegister(String userId, String friendId) {
        userMapperService.addFriendAlreadyRegister(userId, friendId);
//        List<String> deviceTokens = deviceService.findDeviceTokenByUser(friendId);
//        pushService.push(deviceTokens, "添加好友邀请");
    }

    public void addFriendNoRegister(String userId, String phoneNo) {
        userMapperService.addFriendNoRegister(userId, phoneNo);
    }

    public void modifyFriendAlias(UserMapper userMapper) {
        userMapperService.modifyUserMapperAlias(userMapper);
    }

    public void deleteFriend(String userId, String friendId) {
        userMapperService.deleteFriend(userId, friendId);
    }

    public List<FriendInfo> findAllRelationByUserId(String userId) {
        List<FriendInfo> alreadyFriends = userMapperService.findFriendUserId(userId);
        List<FriendInfo> inviteFriends = userMapperService.findFriendInvite(userId);
        List<FriendInfo> invitedFriends = userMapperService.findFriendInvited(userId);
        List<FriendInfo> notAddFriends = userMapperService.findFriendNotAdd(userId);
        ArrayList<FriendInfo> list = new ArrayList<FriendInfo>();
        list.addAll(alreadyFriends);
        list.addAll(inviteFriends);
        list.addAll(invitedFriends);
        list.addAll(notAddFriends);
        return list;
    }

    public List<UserInfo> findUserByProjectId(String projectId) {
        return userMapperService.findUserByProjectId(projectId);
    }
}
