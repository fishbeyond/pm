package com.hs.whocan.service.security.transformer;

import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.service.security.UserInfoResult;
import org.springframework.beans.BeanUtils;
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

    public UserInfoResult transform2UserInfo(User user, String token) {
        UserInfoResult userInfoResult = new UserInfoResult();
        userInfoResult.setUserToken(token);
        BeanUtils.copyProperties(user, userInfoResult);
        return userInfoResult;
    }
}
