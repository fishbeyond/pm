package com.hs.whocan.service;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.security.dao.Access;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.dao.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * User: fish
 */
public abstract class VerifySignInService implements ServiceInterface {

    public String token;
    public String userId;
    public String operator;

    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private UserComponent userComponent;

    @Transactional
    public Object doService() {
        Access access = securityComponent.findAccessInfoByToken(token);
        User user = userComponent.findUserById(access.getAccessId());
        this.setUserId(access.getAccessId());
        this.setOperator(user.getUserName());
        return execute(user);
    }

    public abstract Object execute(User user);

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
