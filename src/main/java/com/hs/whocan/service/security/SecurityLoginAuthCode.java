package com.hs.whocan.service.security;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.service.security.transformer.UserTransformer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 上午10:40
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SecurityLoginAuthCode {
    private String phoneNo;
    private int authCode;

    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private UserComponent userComponent;
    @Resource
    private UserTransformer userTransformer;

    @Transactional
    public UserInfo execute() {
        securityComponent.verifyAuthCode(phoneNo, authCode);
        User user = userComponent.findUserByPhoneNo(phoneNo);
        String token = null;
        if (null == user) {
            user = userComponent.createUserInfo(phoneNo);
            token = securityComponent.createAccessInfo(user.getUserId());
            userComponent.relateUserInvitationByPhoneNo(phoneNo, user.getUserId());
        } else {
            token = securityComponent.modifyAccessToken(user.getUserId());
        }
        return userTransformer.transform2UserInfo(user, token);
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }
}
