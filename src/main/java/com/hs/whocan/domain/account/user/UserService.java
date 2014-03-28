package com.hs.whocan.domain.account.user;

import com.hs.whocan.domain.account.security.dao.AccessDao;
import com.hs.whocan.domain.account.security.dao.PhoneAuthCode;
import com.hs.whocan.domain.account.security.dao.PhoneAuthCodeDao;
import com.hs.whocan.domain.account.user.dao.User;
import com.hs.whocan.domain.account.user.dao.UserDao;
import com.hs.whocan.domain.account.user.dao.UserInvitationDao;
import com.hs.whocan.domain.utils.RandomGenerator;
import com.hs.whocan.domain.utils.UUIDGenerator;
import com.hs.whocan.domain.account.user.exception.PhoneNoDisableException;
import com.hs.whocan.domain.account.security.exception.TokenDisableException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {
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

    public List<String> findUserIdByToken(String token) {
        User operator = findOperatorInfoByToken(token);
        if (null == operator) {
            throw new TokenDisableException();
        }
        List<String> list = new ArrayList<String>();
        list.add(operator.getUserId());
        return list;
    }

    public List<String> findOperatorByToken(String token) {
        User operator = findOperatorInfoByToken(token);
        List<String> list = new ArrayList<String>();
        String operatorName = operator.getUserName() != null ? operator.getUserName() : operator.getPhoneNo();
        list.add(operatorName);
        return list;
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

    public User verifyPhoneNo(String userId,String phoneNo){
        User user = userInfoDao.findUserByIdAndPhoneNo(userId, phoneNo);
        if(null== user){
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

    private User findOperatorInfoByToken(String token) {
        String userId = accessDao.findAccessIdByToken(token);
        User user = userInfoDao.findUserById(userId);
        return user;
    }

    public User findUserNameInfoById(String userId) {
        return userInfoDao.findUserById(userId);
    }
}
