package com.hs.pm.push;

//import com.dbay.apns4j.IApnsService;
//import com.dbay.apns4j.impl.ApnsServiceImpl;
//import com.dbay.apns4j.model.ApnsConfig;
//import com.dbay.apns4j.model.Feedback;
//import com.dbay.apns4j.model.Payload;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public class PushService {
    //example
    public static void main(String[] args) throws Exception {

        String deviceToken = "f2a070af d6fc27ca c1844810 f6904fcd b28e6fc7 1d207d63 5a01f1af e0850f0a";
        String deviceToken2 = "0c0028e4 ca4049d6 52dfdafb c4b37c25 2c0386aa d14545eb f3859b56 d5593c23";
        Device basicDevice = new BasicDevice(deviceToken);
        Device basicDevice2 = new BasicDevice(deviceToken2);
        List<Device> basicDevices = new ArrayList<Device>();
        basicDevices.add(basicDevice);
        basicDevices.add(basicDevice2);

        String content = "此次升级更新的东西";
        String p12File = "D:\\github\\ios_hqt_jenkins_nopassword.p12";
        String p12FilePassword = "wiscom";//此处注意导出的证书密码不能为空因为空密码会报错
        push(p12File,p12FilePassword,content,basicDevices);

    }

    public static void push(String p12File, String p12Pass,String content,List<Device> devices) {
        try {
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(content);
            payLoad.addBadge(1);//应用图标上小红圈上的数值
            payLoad.addSound("default");

            PushNotificationManager pushManager = new PushNotificationManager();

            AppleNotificationServerBasicImpl appleNotificationServerBasic = new AppleNotificationServerBasicImpl(p12File,p12Pass,false);
            pushManager.initializeConnection(appleNotificationServerBasic);
            pushManager.sendNotifications(payLoad,devices);
            pushManager.stopConnection();
        } catch (Exception e) {
            System.out.println("iphone 推送消息异常：" + e.getMessage());
        }
    }

}


