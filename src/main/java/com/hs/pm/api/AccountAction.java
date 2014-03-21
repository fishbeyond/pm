package com.hs.pm.api;

import com.hs.pm.dto.UserToken;
import com.hs.pm.server.account.AccountService;
import com.hs.pm.api.exception.AuthCodeErrorException;
import com.hs.pm.api.exception.TokenDisableException;
import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.sms.SmsService;
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
public class AccountAction {
    @Resource
    private AccountService accountService;
    @Resource
    private SmsService smsService;

    public void sendAuthCode(String phoneNo) {
        int authCode = accountService.getAuthCode(phoneNo);
        smsService.sendAuthCode(phoneNo, authCode);
    }

    public UserToken loginByAuthCode(String phoneNo, int authCode) {
        UserToken token = null;
        if (accountService.authCodeIsCorrect(phoneNo, authCode)) {
            token = accountService.createAccessUser(phoneNo);
        } else {
            throw new AuthCodeErrorException();
        }
        return token;
    }

    public List<String> findUserIdByToken(String token){
        UserInfo operator = accountService.findOperatorInfoByToken(token);
        if (null == operator) {
            throw new TokenDisableException();
        }
        List<String> list = new ArrayList<String>();
        list.add(operator.getUserId());
        return list;

    }
    public List<String> findOperatorByToken(String token) {
        UserInfo operator = accountService.findOperatorInfoByToken(token);
        List<String> list = new ArrayList<String>();
        String operatorName = operator.getUserName()!=null?operator.getUserName():operator.getPhoneNo();
        list.add(operatorName);
        return list;
    }

    public void modifyUser(UserInfo userInfo) {
        accountService.modifyUser(userInfo);
    }

}
