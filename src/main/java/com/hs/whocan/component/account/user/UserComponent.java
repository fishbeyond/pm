package com.hs.whocan.component.account.user;

import com.hs.whocan.component.account.security.dao.AccessDao;
import com.hs.whocan.component.account.security.dao.PhoneAuthCode;
import com.hs.whocan.component.account.security.dao.PhoneAuthCodeDao;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.account.user.dao.UserDao;
import com.hs.whocan.component.account.user.dao.UserInvitationDao;
import com.hs.whocan.framework.utils.RandomGenerator;
import com.hs.whocan.framework.utils.UUIDGenerator;
import com.hs.whocan.component.account.user.exception.PhoneNoDisableException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UserComponent {
    @Resource
    private PhoneAuthCodeDao phoneAuthCodeDao;
    @Resource
    private UserDao userInfoDao;
    @Resource
    private AccessDao accessDao;
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private UserInvitationDao userInvitationDao;

    public User findUserByPhoneNo(String phoneNo) {
        return userInfoDao.findUserByPhoneNo(phoneNo);
    }

    public void relateUserInvitationByPhoneNo(String phoneNo, String userId) {
        userInvitationDao.relateUserInvitationByPhoneNo(phoneNo, userId);
    }

    public int getAuthCode(String phoneNo) {
        int authCode = getAuthCode();
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phoneNo);
        if (null != phoneAuthCode) {
            phoneAuthCode.setAuthCode(authCode);
        } else {
            phoneAuthCode = new PhoneAuthCode(phoneNo, authCode);
            phoneAuthCodeDao.createPhoneAuthCode(phoneAuthCode);
        }
        return authCode;
    }

    private int getAuthCode() {
        return RandomGenerator.getRandom(111111, 999999);
    }

    public User verifyPhoneNo(String userId, String phoneNo) {
        User user = userInfoDao.findUserByIdAndPhoneNo(userId, phoneNo);
        if (null == user) {
            throw new PhoneNoDisableException();
        }
        return user;
    }

    public User createUserInfo(String phoneNo) {
        String userId = uuidGenerator.shortUuid();
        User user = new User(userId, phoneNo, phoneNo);
        userInfoDao.createUser(user);
        return user;
    }

    public void modifyUser(User user) {
        userInfoDao.modifyUser(user);
    }

    @Transactional
    public User findUserById(String userId) {
        return userInfoDao.findUserById(userId);
    }

    public void modifyPortrait(String userId, String portrait) {
        userInfoDao.modifyPortrait(userId, portrait);
    }

    @Transactional
    public User findUser(String sessionId) {
        return userInfoDao.findUserById(sessionId);
    }
}
