package com.hs.whocan.component.account.security;

import com.hs.whocan.component.account.security.access.dao.Access;
import com.hs.whocan.component.account.security.access.dao.AccessDao;
import com.hs.whocan.component.account.security.authcode.dao.PhoneAuthCode;
import com.hs.whocan.component.account.security.authcode.dao.PhoneAuthCodeDao;
import com.hs.whocan.component.account.security.exception.TokenErrorException;
import com.hs.whocan.framework.utils.RandomGenerator;
import com.hs.whocan.framework.utils.UUIDGenerator;
import com.hs.whocan.component.account.security.exception.AuthCodeErrorException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by fish on 14-3-26.
 */
@Service
public class SecurityComponent {
    private static final long VALID_TIME = 30000000000l;
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
        if(null == phoneAuthCode){
            throw new AuthCodeErrorException();
        }
        //验证码超时
//        long applyTime = System.currentTimeMillis()-phoneAuthCode.getCreateTime().getTime();
//        if(applyTime>VALID_TIME){
//            throw new AuthCodeDisableException();
//        }

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
        if (null == access) {
            throw new TokenErrorException();
        }
        //token超时
//        if(System.currentTimeMillis()- access.getAccessTime().getTime() > access.getAliveTime()){
//            throw new TokenDisableException();
//        }
        return access;
    }

    public String findReadTag(String userId){
        return accessDao.findReadTag(userId);
    }

    public void modifyReadTag(String userId, String readTag) {
        accessDao.modifyReadTag(userId,readTag);
    }
}
