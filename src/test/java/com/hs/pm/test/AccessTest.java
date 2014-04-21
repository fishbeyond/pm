package com.hs.pm.test;

import com.hs.whocan.component.account.security.dao.PhoneAuthCode;
import com.hs.whocan.component.account.security.dao.PhoneAuthCodeDao;
import com.hs.whocan.service.security.SecurityLoginAuthCode;
import com.hs.whocan.service.security.SecuritySendAuthCode;
import com.hs.whocan.service.security.UserInfoResult;
import org.junit.Test;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by root on 14-3-2.
 */
public class AccessTest extends WhocanTest{
    @Resource
    private SecuritySendAuthCode securitySendAuthCode;
    @Resource
    private PhoneAuthCodeDao phoneAuthCodeDao;
    @Resource
    private SecurityLoginAuthCode securityLoginAuthCode;

    @Test
    public void send_authCode() {
        String phone = "123";
        String deviceToken ="123";
        securitySendAuthCode.setPhoneNo(phone);
        Boolean execute = securitySendAuthCode.execute();
        assertThat(execute,is(true));
    }
    @Test
    public void login_authCode() {
        String phone = "123";
        String deviceToken ="123";
        securitySendAuthCode.setPhoneNo(phone);
        Boolean execute = securitySendAuthCode.execute();
        PhoneAuthCode phoneAuthCode = phoneAuthCodeDao.findPhoneAuthCode(phone);
        securityLoginAuthCode.setPhoneNo(phone);
        securityLoginAuthCode.setAuthCode(phoneAuthCode.getAuthCode());
        securityLoginAuthCode.setDeviceToken(deviceToken);
        UserInfoResult result = securityLoginAuthCode.execute();
        assertThat(phone,is(result.getPhoneNo()));
        assertThat(phone, is(result.getUserName()));
    }


}

