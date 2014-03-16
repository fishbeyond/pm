package com.hs.pm.user;

import com.hs.pm.sms.SmsService;
import com.hs.pm.utils.RandomGenerator;
import com.hs.pm.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by root on 14-3-16.
 */
@Service
public class UserService {
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private RandomGenerator randomGenerator;
    @Resource
    private SmsService smsService;
    @Resource
    private UserDao userDao;

    public void sendAuthCode(int phoneNo) {
        int authCode = RandomGenerator.getRandom(111111, 888888);
        User user = userDao.findUserByPhoneNo(phoneNo);
        if (null != user) {
            user.setAuthCode(authCode);
        } else {
            String userId = uuidGenerator.shortUuid();
            User newUser = new User(userId, phoneNo);
            userDao.createUser(newUser);
        }
        smsService.send(Integer.toString(phoneNo), "您的验证码是：" + authCode + "[互看]");
    }
    public boolean loginByAuthCode(){
        return false;
    }
}
