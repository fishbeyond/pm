package com.hs.whocan.component.account.user;

import com.hs.whocan.component.account.user.dao.DeviceToken;
import com.hs.whocan.external.push.PushComponent;
import com.hs.whocan.component.session.dao.MessageUserMapperDao;
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
    private MessageUserMapperDao messageUserMapperDao;

    @Transactional
    public void push(List<String> userIds, String message) {
        List<DeviceToken> deviceTokens = deviceComponent.findDeviceTokenByUsers(userIds);
        calculateUnreadNum(deviceTokens);
        new Thread(new PushComponent(deviceTokens, message)).start();
    }

    public void calculateUnreadNum(List<DeviceToken> deviceTokens) {
        for (DeviceToken deviceToken : deviceTokens) {
            String userId = deviceToken.getUserId();
            int unreadNum = messageUserMapperDao.findUnreadNum(userId);
            deviceToken.setUnreadNum(unreadNum == 0 ? 1 : unreadNum);
        }
    }

}
