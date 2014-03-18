package com.hs.pm.user;

import com.hs.pm.push.PushService;
import com.hs.pm.security.dao.AccessInfo;
import com.hs.pm.security.dao.AccessInfoDao;
import com.hs.pm.sms.SmsService;
import com.hs.pm.transform.ResultService;
import com.hs.pm.user.dao.User;
import com.hs.pm.user.dao.UserDao;
import com.hs.pm.user.dao.UserMapper;
import com.hs.pm.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14-3-16.
 */
@Service
@Transactional
public class UserService {
    @Resource
    private ResultService resultService;
    @Resource
    private UserDao userDao;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private PushService pushService;

    public List<String> findOperatorById(String userId) {
        String operator = userDao.findUserNameOrPhoneNoById(userId);
        List<String> list = new ArrayList<String>();
        list.add(operator);
        return list;
    }

    public boolean modifyUser(User user) {
        userDao.modifyUser(user);
        return true;
    }

    public List<User> findFriend(String userId) {
        List<User> friends = userDao.findFriendByUserId(userId);
        return resultService.handle(friends);
    }

    public boolean addFriendAlreadyRegister(String userId, String friendId) {
        userDao.createUserMapper(new UserMapper(userId, friendId, false));
        userDao.createUserMapper(new UserMapper(friendId, userId, false));
        pushService.pushToOne("添加好友邀请",friendId);
        return true;
    }

    public boolean addFriendNoRegister(String userId, String phoneNo) {
        userDao.createUser(new User(phoneNo));
        accessInfoDao.createAccessInfo(new AccessInfo(phoneNo));
        String friendId = userDao.findUserIdByPhoneNo(phoneNo);
        userDao.createUserMapper(new UserMapper(userId, friendId, false));
        userDao.createUserMapper(new UserMapper(friendId, userId, false));
        return true;
    }

    public boolean confirmFriend(String userId, String friendId) {
        UserMapper inviteUserMapper = userDao.findUserMapper(friendId, userId);
        UserMapper myUserMapper = userDao.findUserMapper(userId, friendId);
        if (null != inviteUserMapper) {
            inviteUserMapper.setConfirm(true);
            userDao.modifyUserMapper(inviteUserMapper);
        }
        if (null != myUserMapper) {
            myUserMapper.setConfirm(true);
            userDao.modifyUserMapper(myUserMapper);
        }
        return true;
    }

    public List<User> findUserByProjectId(String projectId) {
        return userDao.findUserByProjectId(projectId);
    }


}
