package com.hs.whocan.service;

import com.hs.whocan.domain.account.AccountService;
import com.hs.whocan.domain.account.user.dao.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-21
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AccountAction {
    @Resource
    private AccountService accountService;
    public void modifyUser(UserForm userForm) {
        UserInfo userInfo = transform(userForm);
        accountService.modifyUser(userInfo);
    }

    private UserInfo transform(UserForm userForm) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userForm, userInfo);
        return userInfo;
    }
}
