package com.hs.pm.api;

import com.hs.pm.server.account.UserRelationService;
import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.devicetoken.DeviceService;
import com.hs.pm.server.push.PushService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<UserInfo> findFriendByUserId(String userId) {
        return userRelationService.findFriendUserId(userId);
    }

    public List<UserInfo> findFriendByPhoneNo(String userId,List<String> phoneNoList) {
        return userRelationService.findFriendByPhoneNo(userId,phoneNoList);
    }

    public void addFriendAlreadyRegister(String userId, String friendId) {
        userRelationService.addFriendAlreadyRegister(userId, friendId);
        List<String> deviceTokens = deviceService.findDeviceTokenByUser(friendId);
//        pushService.push(deviceTokens, "添加好友邀请");
    }

    public void confirmFriend(String userId, String friendId) {
        userRelationService.confirmFriend(userId, friendId);
    }

    public List<UserInfo> findUserByProjectId(String projectId) {
        return userRelationService.findUserByProjectId(projectId);
    }


}
