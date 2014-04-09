package com.hs.whocan.service.user;

import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午12:11
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class UserModify extends WhoCanVerifyLoginService {
    private String userName;
    private String mailAddress;
    private String gender;
    private String remark;
    private String portrait;

    @Resource
    private UserComponent userComponent;

    @Transactional
    public Boolean execute() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        userComponent.modifyUser(user);
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
