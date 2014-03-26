package com.hs.whocan.domain.push;

import com.hs.whocan.domain.push.devicetoken.dao.DeviceDao;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-10
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PushServiceImpl implements PushService {
    @Resource
    private DeviceDao deviceDao;
    private static final String p12File = "D:\\github\\Certificates_whocan.p12";
    private static final String p12FilePassword = "1234qwer";
    private String deviceToken = "25ecb9e6226034c17b162230fbffbe30fdb7f635afaf7112d43ad902e7bcba8a";//test

    @Override
    public void push(List<String> deviceTokens, String content) {
        try {
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(content);
            payLoad.addBadge(1);
            payLoad.addSound("default");
            List<Device> devices = new ArrayList<Device>();
            for (String deviceToken : deviceTokens) {
                Device device = new BasicDevice(deviceToken);
                devices.add(device);
            }
            PushNotificationManager pushManager = new PushNotificationManager();
            //测试
            AppleNotificationServerBasicImpl appleNotificationServerBasic =
                    new AppleNotificationServerBasicImpl(p12File, p12FilePassword, false);
            pushManager.initializeConnection(appleNotificationServerBasic);
            pushManager.sendNotifications(payLoad, devices);
            pushManager.stopConnection();
        } catch (Exception e) {
            throw new RuntimeException("iphone 推送消息异常：", e);
        }
    }

}


