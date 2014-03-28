package com.hs.whocan.service.user;

import com.hs.whocan.domain.account.security.SecurityService;
import com.hs.whocan.domain.account.security.dao.Access;
import com.hs.whocan.domain.sms.SmsService;
import com.hs.whocan.domain.account.user.UserService;
import com.hs.whocan.domain.account.user.dao.User;
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

    public UserInfo loginByAuthCode(String phoneNo, int authCode) {
        securityService.verifyAuthCode(phoneNo, authCode);
        User user = userService.findUserByPhoneNo(phoneNo);
        String token = null;
        if (null == user) {
            user = userService.createUserInfo(phoneNo);
            token = securityService.createAccessInfo(user.getUserId());
            userService.relateUserInvitationByPhoneNo(phoneNo, user.getUserId());
        } else {
            token = securityService.modifyAccessToken(user.getUserId());
        }
        return transform2UserForm(user, token);
    }

    public UserInfo loginByToken(String phoneNo, String token) {
        Access access = securityService.verifyAndUpdateToken(token);
        User user = userService.verifyPhoneNo(access.getAccessId(), phoneNo);
        return transform2UserForm(user, access.getAccessToken());
    }

    public boolean modifyUser(UserInfo userInfo) {
        User user = transform2UserInfo(userInfo);
        userService.modifyUser(user);
        return true;
    }

    public List<String> findUserIdByToken(String token) {
        Access access = securityService.findAccessInfoByToken(token);
        List<String> list = new ArrayList<String>();
        list.add(access.getAccessId());
        return list;
    }

    public List<String> findOperatorByToken(String userId) {
        User user = userService.findUserNameInfoById(userId);
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
