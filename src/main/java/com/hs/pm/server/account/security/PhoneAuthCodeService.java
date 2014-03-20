package com.hs.pm.server.account.security;

import com.hs.pm.server.account.security.dao.PhoneAuthCode;
import com.hs.pm.server.account.security.dao.PhoneAuthCodeDao;
import com.hs.pm.server.utils.RandomGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午2:02
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PhoneAuthCodeService {

    @Resource
    private PhoneAuthCodeDao phoneAuthCodeDao;

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

    public boolean authCodeIsCorrect(String phoneNo, int authCode){
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phoneNo,authCode);
        return phoneAuthCode!=null?true:false;
    }
}
