package com.hs.whocan.service.security;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.sms.SmsService;
import com.hs.whocan.service.ServiceInterface;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SecuritySendAuthCode implements ServiceInterface {
    private String phoneNo;
    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private SmsService smsService;

    public Boolean execute() {
        int authCode = securityComponent.getAuthCode(phoneNo);
        smsService.sendAuthCode(phoneNo, authCode);
        return true;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
