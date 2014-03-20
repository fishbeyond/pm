package com.hs.pm.server.account.user;

import com.hs.pm.server.devicetoken.dao.DeviceDao;
import com.hs.pm.server.push.PushService;
import com.hs.pm.server.account.security.dao.AccessInfoDao;
import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.account.user.dao.UserInfoDao;
import com.hs.pm.server.account.user.dao.UserMapper;
import com.hs.pm.server.utils.UUIDGenerator;
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
public class FriendService {
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private PushService pushService;
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private DeviceDao deviceDao;

    public List<UserInfo> findFriend(String userId) {
        return userInfoDao.findFriendByUserId(userId);
    }

    public boolean addFriendAlreadyRegister(String userId, String friendId) {
        userInfoDao.createUserMapper(new UserMapper(userId, friendId, false));
        userInfoDao.createUserMapper(new UserMapper(friendId, userId, false));
        List<String> deviceTokens = deviceDao.findDeviceTokenByUser(friendId);
        pushService.push(deviceTokens, "添加好友邀请");
        return true;
    }

    public boolean confirmFriend(String userId, String friendId) {
        UserMapper inviteUserMapper = userInfoDao.findUserMapper(friendId, userId);
        UserMapper myUserMapper = userInfoDao.findUserMapper(userId, friendId);
        if (null != inviteUserMapper) {
            inviteUserMapper.setConfirm(true);
            userInfoDao.modifyUserMapper(inviteUserMapper);
        }
        if (null != myUserMapper) {
            myUserMapper.setConfirm(true);
            userInfoDao.modifyUserMapper(myUserMapper);
        }
        return true;
    }

    public List<UserInfo> findUserByProjectId(String projectId) {
        return userInfoDao.findUserByProjectId(projectId);
    }
}
