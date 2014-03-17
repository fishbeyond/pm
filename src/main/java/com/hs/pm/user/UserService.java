package com.hs.pm.user;

import com.hs.pm.project.dao.ProjectUserMapper;
import com.hs.pm.sms.SmsService;
import com.hs.pm.transform.ResultService;
import com.hs.pm.user.dao.User;
import com.hs.pm.user.dao.UserDao;
import com.hs.pm.utils.RandomGenerator;
import com.hs.pm.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 14-3-16.
 */
@Service
@Transactional
public class UserService {
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private ResultService resultService;
    @Resource
    private SmsService smsService;
    @Resource
    private UserDao userDao;

    public boolean sendAuthCode(String phoneNo) {
        int authCode = RandomGenerator.getRandom(111111, 888888);
        User user = userDao.findUserByPhoneNo(phoneNo);
        if (null != user) {
            user.setAuthCode(authCode);
        } else {
            User newUser = new User(phoneNo, authCode, false);
            userDao.createUser(newUser);
        }
        return smsService.send(phoneNo, "您的验证码是：" + authCode + "[互看]");
    }

    public String loginByAuthCode(User user) {
        String phoneNo = user.getPhoneNo();
        int authCode = user.getAuthCode();
        user = userDao.findUserIdByAuthCode(phoneNo, authCode);
        return resultService.handle(user.getUserId());
    }

    public boolean modifyUser(User user) {
        userDao.modifyUser(user);
        return true;
    }

    public List<User> findFriend(String userId) {
        List<User> friends = userDao.findFriendByUserId(userId);
        return resultService.handle(friends);
    }

    public boolean addFriend(String userId, String friendId) {
        userDao.createUserMapper(userId, friendId);
        return true;
    }

    public List<User> findUserByProjectId(String projectId) {
        return userDao.findUserByProjectId(projectId);
    }
}
