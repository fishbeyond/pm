package com.hs.whocan.domain.push.devicetoken;

import com.hs.whocan.domain.push.devicetoken.dao.DeviceDao;
import com.hs.whocan.domain.push.devicetoken.dao.DeviceToken;
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
public class DeviceService {
    @Resource
    private DeviceDao deviceDao;
    public boolean createDeviceToken(DeviceToken deviceToken){
        deviceDao.createDeviceToken(deviceToken);
        return true;
    }
    public List<String> findDeviceTokenByUser(String userId){
        return deviceDao.findDeviceTokenByUser(userId);
    }
}
