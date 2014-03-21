package com.hs.pm.api;

import com.hs.pm.api.exception.TokenDisableException;
import com.hs.pm.dto.UserForm;
import com.hs.pm.server.account.AccountService;
import com.hs.pm.api.exception.AuthCodeErrorException;
import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.sms.SmsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AccessAction {
    @Resource
    private AccountService accountService;
    @Resource
    private SmsService smsService;

    public void sendAuthCode(String phoneNo) {
        int authCode = accountService.getAuthCode(phoneNo);
        smsService.sendAuthCode(phoneNo, authCode);
    }

    public UserForm loginByAuthCode(String phoneNo, int authCode) {
        UserForm token = null;
        if (accountService.authCodeIsCorrect(phoneNo, authCode)) {
            token = accountService.createAccessUser(phoneNo);
        } else {
            throw new AuthCodeErrorException();
        }
        return token;
    }

    public UserForm loginByToken(String phoneNo, String token) {
        return accountService.updateToken(phoneNo, token);
    }
}
