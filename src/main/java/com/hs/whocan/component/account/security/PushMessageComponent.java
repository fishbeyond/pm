package com.hs.whocan.component.account.security;

import com.hs.whocan.component.account.security.device.DeviceComponent;
import com.hs.whocan.component.push.PushComponent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-4-1
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PushMessageComponent {
    @Resource
    private PushComponent pushComponent;
    @Resource
    private DeviceComponent deviceComponent;

    @Transactional
    public void push(List<String> userIds,String message){
        List<String> deviceToken = deviceComponent.findDeviceTokenByUsers(userIds);
        pushComponent.push(deviceToken, message);
    }
}
