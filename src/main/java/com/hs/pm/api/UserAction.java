package com.hs.pm.api;

import com.hs.pm.server.account.user.FriendService;
import com.hs.pm.server.account.user.UserInfoService;
import com.hs.pm.server.account.user.dao.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 14-3-16.
 */
@Service
public class UserAction {
    @Resource
    private FriendService friendService;
    @Resource
    private UserInfoService userInfoService;

    public void modifyUser(UserInfo userInfo) {
        userInfoService.modifyUser(userInfo);
    }

    public List<UserInfo> findFriend(String userId) {
        return friendService.findFriend(userId);
    }

    public void addFriendAlreadyRegister(String userId, String friendId) {
        friendService.addFriendAlreadyRegister(userId,friendId);
    }

    public void confirmFriend(String userId, String friendId) {
        friendService.confirmFriend(userId,friendId);
    }

    public List<UserInfo> findUserByProjectId(String projectId) {
        return friendService.findUserByProjectId(projectId);
    }


}
