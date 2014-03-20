package com.hs.pm.api;

import com.hs.pm.server.account.security.AccessInfoService;
import com.hs.pm.server.account.security.PhoneAuthCodeService;
import com.hs.pm.api.exception.AuthCodeErrorException;
import com.hs.pm.api.exception.TokenDisableException;
import com.hs.pm.server.sms.SmsService;
import com.hs.pm.server.account.user.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private PhoneAuthCodeService phoneAuthCodeService;
    @Resource
    private SmsService smsService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private AccessInfoService accessInfoService;

    public void sendAuthCode(String phoneNo) {
        int authCode = phoneAuthCodeService.getAuthCode(phoneNo);
        smsService.sendAuthCode(phoneNo, authCode);
    }

    public String loginByAuthCode(String phoneNo, int authCode) {
        String token = null;
        if (phoneAuthCodeService.authCodeIsCorrect(phoneNo, authCode)) {
            token = accessInfoService.createAccessUser(phoneNo);
        } else {
            throw new AuthCodeErrorException();
        }
        return token;
    }

    public List<String> findOperatorById(String userId) {
        String operator = userInfoService.findOperatorByToken(userId);
        if (null == operator) {
            throw new TokenDisableException();
        }
        List<String> list = new ArrayList<String>();
        list.add(operator);
        return list;
    }

}
