package com.hs.pm.server.account.security;

import com.hs.pm.server.account.security.dao.AccessInfo;
import com.hs.pm.server.account.security.dao.AccessInfoDao;
import com.hs.pm.server.account.user.dao.UserInfo;
import com.hs.pm.server.account.user.dao.UserInfoDao;
import com.hs.pm.server.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午4:45
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AccessInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private UUIDGenerator uuidGenerator;
    public String createAccessUser(String phoneNo) {
        String userId = uuidGenerator.shortUuid();
        String token = uuidGenerator.shortUuid();
        UserInfo userInfo = new UserInfo(userId, phoneNo);
        AccessInfo accessInfo = new AccessInfo(userId, phoneNo,token);
        userInfoDao.createUser(userInfo);
        accessInfoDao.createAccessInfo(accessInfo);
        return token;
    }
}
