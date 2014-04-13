package com.hs.whocan.component.account.security;

import com.hs.whocan.component.account.security.device.DeviceComponent;
import com.hs.whocan.component.account.security.device.dao.DeviceToken;
import com.hs.whocan.component.push.PushComponent;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.component.session.dao.Session;
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
    private DeviceComponent deviceComponent;
    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private SessionComponent sessionComponent;

    @Transactional
    public void push(List<String> userIds, String message) {
        List<DeviceToken> deviceTokens = deviceComponent.findDeviceTokenByUsers(userIds);
        calculateUnreadNum(deviceTokens);
        new Thread(new PushComponent(deviceTokens, message)).start();
    }

    public void calculateUnreadNum(List<DeviceToken> deviceTokens) {
        for (DeviceToken deviceToken : deviceTokens) {
            int unreadNum = 0;
            String userId = deviceToken.getUserId();
            String readTag = securityComponent.findReadTag(userId);
            List<Session> sessions = sessionComponent.findSession(userId);
            for (Session session : sessions) {
                List<Message> list = sessionComponent.findNewMessage(session.getSessionId(), readTag);
                unreadNum += list.size();
            }
            deviceToken.setUnreadNum(unreadNum);
        }
    }

}
