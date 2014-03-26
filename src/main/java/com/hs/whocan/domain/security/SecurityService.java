package com.hs.whocan.domain.security;

import com.hs.whocan.domain.security.dao.AccessInfo;
import com.hs.whocan.domain.security.dao.AccessInfoDao;
import com.hs.whocan.domain.security.dao.PhoneAuthCode;
import com.hs.whocan.domain.security.dao.PhoneAuthCodeDao;
import com.hs.whocan.domain.utils.RandomGenerator;
import com.hs.whocan.domain.utils.UUIDGenerator;
import com.hs.whocan.framework.exception.AuthCodeErrorException;
import com.hs.whocan.framework.exception.TokenDisableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by yinwenbo on 14-3-26.
 */
@Service
public class SecurityService {
    private final long VALID_TIME = 300000;
    @Resource
    private PhoneAuthCodeDao phoneAuthCodeDao;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private UUIDGenerator uuidGenerator;

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
        AccessInfo accessInfo = new AccessInfo(accessId, token);
        accessInfoDao.createAccessInfo(accessInfo);
        return token;
    }

    public String modifyAccessToken(String userId) {
        String token = uuidGenerator.shortUuid();
        accessInfoDao.modifyAccessToken(userId,token);
        return token;
    }

    public AccessInfo verifyAndUpdateToken(String token) {
        AccessInfo accessInfo = tokenIsValid(token);
        String newToken = uuidGenerator.shortUuid();
        accessInfo.setAccessToken(newToken);
        accessInfoDao.modifyAccessToken(accessInfo.getAccessId(), accessInfo.getAccessToken());
        return accessInfo;
    }

    public AccessInfo findAccessInfoByToken(String token) {
        return tokenIsValid(token);
    }

    private AccessInfo tokenIsValid(String token){
        AccessInfo accessInfo = accessInfoDao.findAccessInfoByToken(token);
        if (null == accessInfo || (System.currentTimeMillis()-accessInfo.getAccessTime().getTime() > accessInfo.getAliveTime())) {
            throw new TokenDisableException();
        }
        return accessInfo;
    }
}
