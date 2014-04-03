package com.hs.whocan.service;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.security.access.dao.Access;
import com.hs.whocan.component.account.user.UserComponent;
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
    @Resource
    private SecurityComponent securityComponent;

    @Transactional
    public WhocanFilterExecutor verifyTokenAndSetUserId() {
        Access access = securityComponent.findAccessInfoByToken(token);
        this.setUserId(access.getAccessId());
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
}
