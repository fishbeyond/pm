package com.hs.whocan.component.push;

import com.hs.whocan.component.push.devicetoken.DeviceComponent;
import org.springframework.stereotype.Service;

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
    public void push(List<String> userIds,String message){
        List<String> deviceToken = deviceComponent.findDeviceTokenByUsers(userIds);
        pushComponent.push(deviceToken, message);
    }
}
