package com.hs.pm.server.account;

import com.hs.pm.api.exception.PhoneNoDisableException;
import com.hs.pm.api.exception.TokenDisableException;
import com.hs.pm.dto.UserForm;
import com.hs.pm.server.account.security.dao.AccessInfo;
import com.hs.pm.server.account.security.dao.AccessInfoDao;
import com.hs.pm.server.account.security.dao.PhoneAuthCode;
import com.hs.pm.server.account.security.dao.PhoneAuthCodeDao;
import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.account.user.dao.UserInfoDao;
import com.hs.pm.server.utils.RandomGenerator;
import com.hs.pm.server.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-21
 * Time: 上午9:00
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AccountService {
    @Resource
    private PhoneAuthCodeDao phoneAuthCodeDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private UUIDGenerator uuidGenerator;


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

    public UserForm updateToken(String phoneNo, String token) {
        AccessInfo accessInfo = accessInfoDao.findAccessInfoByToken(token);
        if (null == accessInfo) {
            throw new TokenDisableException();
        }
        UserInfo userInfo = userInfoDao.findUserById(accessInfo.getAccessId());
        if (!phoneNo.equals(userInfo.getPhoneNo())) {
            throw new PhoneNoDisableException();
        }
        String newToken = uuidGenerator.shortUuid();
        accessInfo.setAccessToken(newToken);
        return transform2UserForm(userInfo, newToken);
    }

    public UserForm createAccessUser(String phoneNo) {
        UserInfo userInfo = userInfoDao.findUserByPhoneNo(phoneNo);
        String token = uuidGenerator.shortUuid();
        if (null == userInfo) {
            String userId = uuidGenerator.shortUuid();
            userInfo = new UserInfo(userId, phoneNo, phoneNo);
            AccessInfo accessInfo = new AccessInfo(userId, token);
            userInfoDao.createUser(userInfo);
            accessInfoDao.createAccessInfo(accessInfo);
        } else {
            accessInfoDao.modifyAccessToken(userInfo.getUserId(), token);
        }
        return transform2UserForm(userInfo, token);
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
