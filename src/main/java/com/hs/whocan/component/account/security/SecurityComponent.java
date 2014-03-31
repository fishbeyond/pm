package com.hs.whocan.component.account.security;

import com.hs.whocan.component.account.security.dao.Access;
import com.hs.whocan.component.account.security.dao.AccessDao;
import com.hs.whocan.component.account.security.dao.PhoneAuthCode;
import com.hs.whocan.component.account.security.dao.PhoneAuthCodeDao;
import com.hs.whocan.component.utils.RandomGenerator;
import com.hs.whocan.component.utils.UUIDGenerator;
import com.hs.whocan.component.account.security.exception.AuthCodeErrorException;
import com.hs.whocan.component.account.security.exception.TokenDisableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by yinwenbo on 14-3-26.
 */
@Service
public class SecurityComponent {
    private final long VALID_TIME = 300000;
    @Resource
    private PhoneAuthCodeDao phoneAuthCodeDao;
    @Resource
    private AccessDao accessDao;
    @Resource
    private UUIDGenerator uuidGenerator;

    @Transactional
    public int getAuthCode(String phoneNo) {
        int authCode = getAuthCode();
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phoneNo);
        if (null != phoneAuthCode) {
            phoneAuthCode.setAuthCode(authCode);
            phoneAuthCode.setCreateTime(new Date());
        } else {
            phoneAuthCode = new PhoneAuthCode(phoneNo, authCode);
            phoneAuthCodeDao.createPhoneAuthCode(phoneAuthCode);
        }
        return authCode;
    }

    private int getAuthCode() {
        return RandomGenerator.getRandom(111111, 999999);
    }
    public void verifyAuthCode(String phoneNo, int authCode) {
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phoneNo, authCode);
        long applyTime = System.currentTimeMillis()-phoneAuthCode.getCreateTime().getTime();
        if(null == phoneAuthCode || applyTime>VALID_TIME){
            throw new AuthCodeErrorException();
        }
    }

    public String createAccessInfo(String accessId){
        String token = uuidGenerator.shortUuid();
        Access access = new Access(accessId, token);
        accessDao.createAccessInfo(access);
        return token;
    }

    public String modifyAccessToken(String userId) {
        String token = uuidGenerator.shortUuid();
        accessDao.modifyAccessToken(userId,token);
        return token;
    }

    public Access verifyAndUpdateToken(String token) {
        Access access = tokenIsValid(token);
        String newToken = uuidGenerator.shortUuid();
        access.setAccessToken(newToken);
        accessDao.modifyAccessToken(access.getAccessId(), access.getAccessToken());
        return access;
    }

    public Access findAccessInfoByToken(String token) {
        return tokenIsValid(token);
    }

    private Access tokenIsValid(String token){
        Access access = accessDao.findAccessInfoByToken(token);
        if (null == access || (System.currentTimeMillis()- access.getAccessTime().getTime() > access.getAliveTime())) {
            throw new TokenDisableException();
        }
        return access;
    }
}
