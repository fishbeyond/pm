package com.hs.pm.server.account.user;

import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.account.user.dao.UserInfoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;


    public String findOperatorByToken(String token) {
        UserInfo userInfo = userInfoDao.findUserByToken(token);
        String userName = userInfo.getUserName();
        String phoneNo = userInfo.getPhoneNo();
        if (null != userInfo) {
            return userName != null ? userName : phoneNo;
        }
        return null;
    }
    public void modifyUser(UserInfo userInfo) {
        userInfoDao.modifyUser(userInfo);
    }

}
