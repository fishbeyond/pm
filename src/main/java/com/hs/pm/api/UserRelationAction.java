package com.hs.pm.api;

import com.hs.pm.server.account.UserRelationService;
import com.hs.pm.dto.FriendInfo;
import com.hs.pm.server.account.user.dao.Linkman;
import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.account.user.dao.UserMapper;
import com.hs.pm.server.devicetoken.DeviceService;
import com.hs.pm.server.push.PushService;
import com.hs.pm.server.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

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
    @Resource
    private UUIDGenerator uuidGenerator;

    public void uploadLinkMan(Object obj) {
        LinkedMultiValueMap map = (LinkedMultiValueMap)obj;
        String userId = map.get("userId").get(0).toString();
        int count = Integer.valueOf(map.get("count").get(0).toString());
        List<Linkman> linkmanList = new ArrayList<Linkman>();
        for (int i = 0; i < count; i++) {
            Linkman linkman = new Linkman();
            String linkmanName = map.get("linkmanList[" + i + "][linkmanName]").get(0).toString();
            String phoneNo = map.get("linkmanList[" + i + "][phoneNo]").get(0).toString();
            linkman.setUserId(userId);
            linkman.setLinkmanName(linkmanName);
            linkman.setPhoneNo(phoneNo);
            linkmanList.add(linkman);
        }
        userRelationService.createLinkman(linkmanList);
    }

    public void addFriendAlreadyRegister(String userId, String friendId) {
        userRelationService.addFriendAlreadyRegister(userId, friendId);
//        List<String> deviceTokens = deviceService.findDeviceTokenByUser(friendId);
//        pushService.push(deviceTokens, "添加好友邀请");
    }

    public void addFriendNoRegister(String userId, String phoneNo) {
        userRelationService.addFriendNoRegister(userId, phoneNo);
    }

    public void confirmFriend(String userId,String friendId) {
        userRelationService.confirmFriend(userId,friendId);
    }

    public void modifyFriendAlias(UserMapper userMapper) {
        userRelationService.modifyUserMapperAlias(userMapper);
    }

    public void deleteFriend(String userId,String friendId){
        userRelationService.deleteFriend(userId,friendId);
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
