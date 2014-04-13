package com.hs.whocan.component.account.security.device;

import com.hs.whocan.component.account.security.device.dao.DeviceDao;
import com.hs.whocan.component.account.security.device.dao.DeviceToken;
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
    private DeviceDao deviceDao;
    public void createDeviceToken(DeviceToken deviceToken){
        DeviceToken oldDeviceToken = deviceDao.findDeviceToken(deviceToken.getUserId(),deviceToken.getToken());
        if(null==oldDeviceToken){
            deviceDao.createDeviceToken(deviceToken);
        }else {
            oldDeviceToken.setToken(deviceToken.getToken());
            deviceDao.modifyDeviceToken(oldDeviceToken);
        }
    }
    public List<String> findTokenByUser(String userId){
        return deviceDao.findToken(userId);
    }
    public List<DeviceToken> findDeviceTokenByUser(String userId){
        return deviceDao.findDeviceToken(userId);
    }

    public List<DeviceToken> findDeviceTokenByUsers(List<String> userIds){
        return deviceDao.findDeviceToken(userIds);
    }
}
