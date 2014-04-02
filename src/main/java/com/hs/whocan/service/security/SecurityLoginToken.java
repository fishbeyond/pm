package com.hs.whocan.service.security;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.security.access.dao.Access;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.service.WhoCanExecutor;
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
public class SecurityLoginToken implements WhoCanExecutor {
    private String token;
    private String phoneNo;
    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private UserComponent userComponent;
    @Resource
    private UserTransformer userTransformer;

    @Transactional
    public UserInfoResult execute() {
        Access access = securityComponent.verifyAndUpdateToken(token);
        User user = userComponent.verifyPhoneNo(access.getAccessId(), phoneNo);
        return userTransformer.transform2UserInfo(user, access.getAccessToken());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
