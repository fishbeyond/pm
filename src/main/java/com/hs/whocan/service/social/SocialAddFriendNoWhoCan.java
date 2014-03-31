package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SocialAddFriendNoWhoCan implements WhoCanExecutor {
    private String phoneNo;
    @Resource
    private UserMapperComponent userMapperComponent;
    private String userId;

    public Boolean execute() {
        userMapperComponent.addFriendNoRegister(userId, phoneNo);
        return true;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
