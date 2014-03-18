package com.hs.pm.push;

import com.hs.pm.push.dao.DeviceDao;
import com.hs.pm.push.dao.DeviceToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
}
