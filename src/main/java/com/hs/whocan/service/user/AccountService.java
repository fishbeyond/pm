package com.hs.whocan.service.user;

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

    public UserParameter loginByAuthCode(String phoneNo, int authCode) {
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

    public UserParameter loginByToken(String phoneNo, String token) {
        AccessInfo accessInfo = securityService.verifyAndUpdateToken(token);
        UserInfo userInfo = userService.verifyPhoneNo(accessInfo.getAccessId(), phoneNo);
        return transform2UserForm(userInfo, accessInfo.getAccessToken());
    }

    public boolean modifyUser(UserParameter userParameter) {
        UserInfo userInfo = transform2UserInfo(userParameter);
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

    private UserParameter transform2UserForm(UserInfo userInfo, String token) {
        UserParameter userParameter = new UserParameter();
        userParameter.setUserToken(token);
        userParameter.setUserName(userInfo.getUserName());
        userParameter.setPhoneNo(userInfo.getPhoneNo());
        userParameter.setMailAddress(userInfo.getMailAddress());
        return userParameter;
    }

    private UserInfo transform2UserInfo(UserParameter userParameter) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userParameter, userInfo);
        return userInfo;
    }

}
