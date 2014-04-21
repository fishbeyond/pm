package com.hs.whocan.component.account.user;

import com.hs.whocan.component.account.user.dao.DeviceTokenDao;
import com.hs.whocan.component.account.user.dao.DeviceToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class DeviceComponent {
    @Resource
    private DeviceTokenDao deviceTokenDao;

    public void createDeviceToken(DeviceToken deviceToken) {
        DeviceToken oldDeviceToken = deviceTokenDao.findDeviceToken(deviceToken.getUserId(), deviceToken.getToken());
        if (null == oldDeviceToken) {
            deviceTokenDao.createDeviceToken(deviceToken);
        } else {
            oldDeviceToken.setToken(deviceToken.getToken());
            deviceTokenDao.modifyDeviceToken(oldDeviceToken);
        }
    }

    public List<String> findTokenByUser(String userId) {
        return deviceTokenDao.findToken(userId);
    }

    public List<DeviceToken> findDeviceTokenByUser(String userId) {
        return deviceTokenDao.findDeviceToken(userId);
    }

    public List<DeviceToken> findDeviceTokenByUsers(List<String> userIds) {
        return deviceTokenDao.findDeviceToken(userIds);
    }
}
