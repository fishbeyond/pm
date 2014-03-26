package com.hs.whocan.domain.user;

import com.hs.whocan.domain.security.dao.AccessInfoDao;
import com.hs.whocan.domain.security.dao.PhoneAuthCode;
import com.hs.whocan.domain.security.dao.PhoneAuthCodeDao;
import com.hs.whocan.domain.user.dao.UserInfo;
import com.hs.whocan.domain.user.dao.UserInfoDao;
import com.hs.whocan.domain.user.dao.UserInvitationDao;
import com.hs.whocan.domain.utils.RandomGenerator;
import com.hs.whocan.domain.utils.UUIDGenerator;
import com.hs.whocan.framework.exception.TokenDisableException;
import com.hs.whocan.service.UserForm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private UserInfoDao userInfoDao;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private UserInvitationDao userInvitationDao;

    public UserInfo findUserByPhoneNo(String phoneNo) {
        return userInfoDao.findUserByPhoneNo(phoneNo);
    }

    public void relateUserInvitationByPhoneNo(String phoneNo, String userId) {
        userInvitationDao.relateUserInvitationByPhoneNo(phoneNo, userId);
    }

    public List<String> findUserIdByToken(String token) {
        UserInfo operator = findOperatorInfoByToken(token);
        if (null == operator) {
            throw new TokenDisableException();
        }
        List<String> list = new ArrayList<String>();
        list.add(operator.getUserId());
        return list;
    }

    public List<String> findOperatorByToken(String token) {
        UserInfo operator = findOperatorInfoByToken(token);
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
        modifyAuthCodeAfterSecond(phoneAuthCode, 300);
        return authCode;
    }

    private void modifyAuthCodeAfterSecond(final PhoneAuthCode phoneAuthCode, long second) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int authCode = 0;
                phoneAuthCode.setAuthCode(authCode);
                phoneAuthCodeDao.modifyPhoneAuthCodeByPhoneNo(phoneAuthCode);
            }
        }, second * 1000);
    }

    private int getAuthCode() {
        return RandomGenerator.getRandom(111111, 999999);
    }

    public boolean authCodeIsCorrect(String phoneNo, int authCode) {
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phoneNo, authCode);
        return phoneAuthCode != null ? true : false;
    }

    public UserInfo findUserInfoById(String userId){
       return userInfoDao.findUserById(userId);
    }

    public UserInfo createUserInfo(String phoneNo) {
        String userId = uuidGenerator.shortUuid();
        UserInfo userInfo = new UserInfo(userId, phoneNo, phoneNo);
        userInfoDao.createUser(userInfo);
        return userInfo;
    }

    private UserForm transform2UserForm(UserInfo userInfo, String token) {
        UserForm userForm = new UserForm();
        userForm.setUserToken(token);
        userForm.setUserName(userInfo.getUserName());
        userForm.setPhoneNo(userInfo.getPhoneNo());
        userForm.setMailAddress(userInfo.getMailAddress());
        return userForm;
    }

    public void modifyUser(UserInfo userInfo) {
        userInfoDao.modifyUser(userInfo);
    }

    private UserInfo findOperatorInfoByToken(String token) {
        String userId = accessInfoDao.findAccessIdByToken(token);
        UserInfo userInfo = userInfoDao.findUserById(userId);
        return userInfo;
    }

}
