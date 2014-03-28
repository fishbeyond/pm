package com.hs.whocan.service.user;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.security.dao.Access;
import com.hs.whocan.component.sms.SmsService;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.dao.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AccountService {
    @Resource
    private UserComponent userComponent;
    @Resource
    private SmsService smsService;
    @Resource
    private SecurityComponent securityComponent;

    public boolean sendAuthCode(String phoneNo) {
        int authCode = securityComponent.getAuthCode(phoneNo);
        smsService.sendAuthCode(phoneNo, authCode);
        return true;
    }

    public UserInfo loginByAuthCode(String phoneNo, int authCode) {
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
        return transform2UserForm(user, token);
    }

    public UserInfo loginByToken(String phoneNo, String token) {
        Access access = securityComponent.verifyAndUpdateToken(token);
        User user = userComponent.verifyPhoneNo(access.getAccessId(), phoneNo);
        return transform2UserForm(user, access.getAccessToken());
    }

    public boolean modifyUser(UserInfo userInfo) {
        User user = transform2UserInfo(userInfo);
        userComponent.modifyUser(user);
        return true;
    }

    public List<String> findUserIdByToken(String token) {
        Access access = securityComponent.findAccessInfoByToken(token);
        List<String> list = new ArrayList<String>();
        list.add(access.getAccessId());
        return list;
    }

    public List<String> findOperatorByToken(String userId) {
        User user = userComponent.findUserNameInfoById(userId);
        List<String> list = new ArrayList<String>();
        list.add(user.getUserName());
        return list;
    }

    private UserInfo transform2UserForm(User user, String token) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserToken(token);
        userInfo.setUserName(user.getUserName());
        userInfo.setPhoneNo(user.getPhoneNo());
        userInfo.setMailAddress(user.getMailAddress());
        return userInfo;
    }

    private User transform2UserInfo(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return user;
    }

}
