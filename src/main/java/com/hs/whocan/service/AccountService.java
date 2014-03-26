package com.hs.whocan.service;

import com.hs.whocan.domain.security.SecurityService;
import com.hs.whocan.domain.security.dao.AccessInfo;
import com.hs.whocan.domain.sms.SmsService;
import com.hs.whocan.domain.user.UserService;
import com.hs.whocan.domain.user.dao.UserInfo;
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
    private UserService userService;
    @Resource
    private SmsService smsService;
    @Resource
    private SecurityService securityService;

    public boolean sendAuthCode(String phoneNo) {
        int authCode = securityService.getAuthCode(phoneNo);
        smsService.sendAuthCode(phoneNo, authCode);
        return true;
    }

    public UserForm loginByAuthCode(String phoneNo, int authCode) {
        securityService.verifyAuthCode(phoneNo, authCode);
        UserInfo userInfo = userService.findUserByPhoneNo(phoneNo);
        String token = null;
        if (null == userInfo) {
            userInfo = userService.createUserInfo(phoneNo);
            token = securityService.createAccessInfo(userInfo.getUserId());
            userService.relateUserInvitationByPhoneNo(phoneNo, userInfo.getUserId());
        } else {
            token = securityService.modifyAccessToken(userInfo.getUserId());
        }
        return transform2UserForm(userInfo, token);
    }

    public UserForm loginByToken(String phoneNo, String token) {
        AccessInfo accessInfo = securityService.verifyAndUpdateToken(token);
        UserInfo userInfo = userService.verifyPhoneNo(accessInfo.getAccessId(), phoneNo);
        return transform2UserForm(userInfo, accessInfo.getAccessToken());
    }

    public boolean modifyUser(UserForm userForm) {
        UserInfo userInfo = transform2UserInfo(userForm);
        userService.modifyUser(userInfo);
        return true;
    }

    public List<String> findUserIdByToken(String token) {
        AccessInfo accessInfo = securityService.findAccessInfoByToken(token);
        List<String> list = new ArrayList<String>();
        list.add(accessInfo.getAccessId());
        return list;
    }

    public List<String> findOperatorByToken(String userId) {
        UserInfo userInfo = userService.findUserNameInfoById(userId);
        List<String> list = new ArrayList<String>();
        list.add(userInfo.getUserName());
        return list;
    }

    private UserForm transform2UserForm(UserInfo userInfo, String token) {
        UserForm userForm = new UserForm();
        userForm.setUserToken(token);
        userForm.setUserName(userInfo.getUserName());
        userForm.setPhoneNo(userInfo.getPhoneNo());
        userForm.setMailAddress(userInfo.getMailAddress());
        return userForm;
    }

    private UserInfo transform2UserInfo(UserForm userForm) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userForm, userInfo);
        return userInfo;
    }

}
