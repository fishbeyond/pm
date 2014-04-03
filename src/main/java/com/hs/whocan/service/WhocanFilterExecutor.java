package com.hs.whocan.service;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.security.access.dao.Access;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * User: fish
 */
public abstract class WhocanFilterExecutor implements WhoCanExecutor {

    public String userId;
    public String token;
    public String operator;
    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private UserComponent userComponent;

    @Transactional
    public WhocanFilterExecutor verifyTokenAndSetUserId() {
        Access access = securityComponent.findAccessInfoByToken(token);
        User user = userComponent.findUserNameInfoById(access.getAccessId());
        this.setUserId(access.getAccessId());
        this.setOperator(user.getUserName());
        return this;
    }

    public abstract Object execute();

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
