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
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SocialUploadLinkman implements WhoCanExecutor {

    private String phones;
    @Resource
    private UserMapperComponent userMapperComponent;
    private String userId;

    public Boolean execute() {
        String[] phoneArray = phones.split(",");
        userMapperComponent.createLinkman(userId, phoneArray);
        return true;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
