package com.hs.whocan.service;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.security.access.dao.Access;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * User: fish
 */
public abstract class WhocanNeedLoginService implements WhoCanService {

    public String userId;
    public String token;
    public String operator;
    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private UserComponent userComponent;

    @Transactional
    public WhocanNeedLoginService verifyTokenAndSetUserId() {
        Access access = securityComponent.findAccessInfoByToken(token);
        User user = userComponent.findUserNameInfoById(access.getAccessId());
        this.setUserId(access.getAccessId());
        this.setOperator(user.getUserName());
        return this;
    }

    public static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    public void validate(Object obj){
        Set<ConstraintViolation<Object>> validate = getValidator().validate(obj);
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
