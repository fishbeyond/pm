package com.hs.pm.security;

import com.hs.pm.project.dao.ProjectDao;
import com.hs.pm.security.dao.AccessInfo;
import com.hs.pm.security.dao.AccessInfoDao;
import com.hs.pm.sms.SmsService;
import com.hs.pm.transform.ResultService;
import com.hs.pm.user.dao.User;
import com.hs.pm.user.dao.UserDao;
import com.hs.pm.utils.RandomGenerator;
import com.hs.pm.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class AccessInfoService {
    @Resource
    private ResultService resultService;
    @Resource
    private SmsService smsService;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private UserDao userDao;
    @Resource
    private ProjectDao projectDao;
    @Resource
    private UUIDGenerator uuidGenerator;

    public boolean sendAuthCode(String phoneNo) {
        int authCode = RandomGenerator.getRandom(111111, 888888);
        AccessInfo accessInfo = accessInfoDao.findAccessInfoByPhoneNo(phoneNo);
        if (null != accessInfo) {
            accessInfo.setAuthCode(authCode);
        } else {
            String userId = uuidGenerator.shortUuid();
            AccessInfo newAccessInfo = new AccessInfo(userId,phoneNo, authCode);
            User user = new User(userId, phoneNo);
            accessInfoDao.createAccessInfo(newAccessInfo);
            userDao.createUser(user);
        }
        return smsService.send(phoneNo, "您的验证码是：" + authCode+ "[互看]");
    }

    public String loginByAuthCode(AccessInfo accessInfo) {
        String phoneNo = accessInfo.getPhoneNo();
        int authCode = accessInfo.getAuthCode();
        accessInfo = accessInfoDao.findAccessIdByAuthCode(phoneNo, authCode);
        String userId = null;
        if (accessInfo != null) {
            userId = accessInfo.getAccessId();
            userDao.modifyUserActive(userId);
            projectDao.bindProjectAndUser(phoneNo, userId);
        }
        return resultService.handle(userId);
    }

    public boolean modifyPassword(String userId,String oldPassword,String newPassword) {
        AccessInfo accessInfo = accessInfoDao.findAccessInfoByUserId(userId);
        if(oldPassword.equals(accessInfo.getPassword())){
            accessInfo.setPassword(newPassword);
            accessInfoDao.modifyAccessInfo(accessInfo);
            return true;
        }
        return false;
    }
}
