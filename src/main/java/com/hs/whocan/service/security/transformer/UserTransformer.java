package com.hs.whocan.service.security.transformer;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.service.user.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserTransformer {

    public UserInfo transform2UserInfo(User user, String token) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserToken(token);
        userInfo.setUserName(user.getUserName());
        userInfo.setPhoneNo(user.getPhoneNo());
        userInfo.setMailAddress(user.getMailAddress());
        return userInfo;
    }
}
