package com.hs.pm.server.account;

import com.hs.pm.dto.UserToken;
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

    public int getAuthCode(String phoneNo) {
        int authCode = RandomGenerator.getRandom(111111, 999999);
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phoneNo);
        if (null != phoneAuthCode) {
            phoneAuthCode.setAuthCode(authCode);
        } else {
            phoneAuthCodeDao.createPhoneAuthCode(new PhoneAuthCode(phoneNo, authCode));
        }
        return authCode;
    }

    public boolean authCodeIsCorrect(String phoneNo, int authCode) {
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phoneNo, authCode);
        return phoneAuthCode != null ? true : false;
    }


    public UserToken createAccessUser(String phoneNo) {
        UserInfo userInfo = userInfoDao.findUserByPhoneNo(phoneNo);
        String token = null;
        if (null == userInfo) {
            String userId = uuidGenerator.shortUuid();
            token = uuidGenerator.shortUuid();
            userInfo = new UserInfo(userId, phoneNo);
            AccessInfo accessInfo = new AccessInfo(userId, token);
            userInfoDao.createUser(userInfo);
            accessInfoDao.createAccessInfo(accessInfo);
        }
        UserToken userToken = new UserToken();
        userToken.setUserToken(token);
        userToken.setUserName(userInfo.getUserName());
        userToken.setPhoneNo(userInfo.getPhoneNo());
        userToken.setMailAddress(userInfo.getMailAddress());
        return userToken;
    }

    public UserInfo findOperatorInfoByToken(String token) {
        String userId = accessInfoDao.findAccessIdByToken(token);
        UserInfo userInfo = userInfoDao.findUserById(userId);
        return userInfo;
    }

    public void modifyUser(UserInfo userInfo) {
        userInfoDao.modifyUser(userInfo);
    }
}
