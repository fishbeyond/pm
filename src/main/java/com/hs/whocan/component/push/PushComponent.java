package com.hs.whocan.component.push;

import com.hs.whocan.component.account.user.dao.DeviceToken;
import com.hs.whocan.component.push.exception.PushFailException;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * info: fish
 * Date: 14-3-10
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public class PushComponent implements Runnable {
    private static final String p12File = PushComponent.class.getClassLoader().getResource("").getPath() + "Certificates_whocan.p12";
    private static final String p12FilePassword = "1234qwer";
    private List<DeviceToken> deviceTokens;
    private String content;

    public PushComponent(List<DeviceToken> deviceTokens, String content) {
        this.deviceTokens = deviceTokens;
        this.content = content;
    }

    @Override
    public void run() {
        try {
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(content);
            payLoad.addSound("default");
            PushNotificationManager pushManager = new PushNotificationManager();
            //测试
            AppleNotificationServerBasicImpl appleNotificationServerBasic =
                    new AppleNotificationServerBasicImpl(p12File, p12FilePassword, false);
            pushManager.initializeConnection(appleNotificationServerBasic);
            for (DeviceToken deviceToken : deviceTokens) {
                Device device = new BasicDevice(deviceToken.getToken());
                payLoad.addBadge(deviceToken.getUnreadNum());
                pushManager.sendNotification(device, payLoad);
            }
            pushManager.stopConnection();
        } catch (Exception e) {
            throw new PushFailException();
        }
    }
}


