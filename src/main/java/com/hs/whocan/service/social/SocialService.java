package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.account.user.dao.UserMapper;
import com.hs.whocan.component.push.PushService;
import com.hs.whocan.component.push.devicetoken.DeviceService;
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
    private UserMapperComponent userMapperComponent;
    @Resource
    private DeviceService deviceService;
    @Resource
    private PushService pushService;

    public boolean uploadLinkMan(String userId, String phones) {
        String[] phoneArray = phones.split(",");
        userMapperComponent.createLinkman(userId, phoneArray);
        return true;
    }

    public boolean addFriendAlreadyRegister(String userId, String friendId) {
        userMapperComponent.addFriendAlreadyRegister(userId, friendId);
//        List<String> deviceTokens = deviceService.findDeviceTokenByUser(friendId);
//        pushService.push(deviceTokens, "添加好友邀请");
        return true;
    }

    public boolean addFriendNoRegister(String userId, String phoneNo) {
        userMapperComponent.addFriendNoRegister(userId, phoneNo);
        return true;
    }

    public boolean modifyFriendAlias(UserMapper userMapper) {
        userMapperComponent.modifyUserMapperAlias(userMapper);
        return true;
    }

    public boolean deleteFriend(String userId, String friendId) {
        userMapperComponent.deleteFriend(userId, friendId);
        return true;
    }

    public List<FriendInfo> findAllRelationByUserId(String userId) {
        List<FriendInfo> alreadyFriends = userMapperComponent.findFriendUserId(userId);
        List<FriendInfo> inviteFriends = userMapperComponent.findFriendInvite(userId);
        List<FriendInfo> invitedFriends = userMapperComponent.findFriendInvited(userId);
        List<FriendInfo> notAddFriends = userMapperComponent.findFriendNotAdd(userId);
        ArrayList<FriendInfo> list = new ArrayList<FriendInfo>();
        list.addAll(alreadyFriends);
        list.addAll(inviteFriends);
        list.addAll(invitedFriends);
        list.addAll(notAddFriends);
        return list;
    }

    public List<User> findUserByProjectId(String projectId) {
        return userMapperComponent.findUserByProjectId(projectId);
    }
}
