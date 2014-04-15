package com.hs.whocan.service.session;

import com.hs.whocan.component.account.security.PushMessageComponent;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.SessionQuery;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.framework.utils.UUIDGenerator;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import com.hs.whocan.service.session.dto.SessionUserInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionAddUser extends WhoCanVerifyLoginService {
    private String sessionId;
    private String userIds;
    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private SessionQuery sessionQuery;
    @Resource
    private PushMessageComponent pushMessageComponent;
    @Resource
    private UUIDGenerator uuidGenerator;
    @Resource
    private UserComponent userComponent;

    public SessionUserInfo execute() {
        List<String> userIdList = getAddUserIdList();
        List<String> relateSessionUserIdList = getRelateSessionUserIdList(userIdList, userId, sessionId);
        Session session = sessionComponent.createSession(sessionId, userId);
        sessionComponent.relateSessionUser(session.getSessionId(), relateSessionUserIdList);
        Message message = getAddUserMessage(relateSessionUserIdList,session.getSessionId());
        sessionComponent.sendMessage(message, relateSessionUserIdList);
        SessionUserInfo sessionUserInfo = sessionQuery.querySessionUserInfo(userId, session);
        pushMessageComponent.push(userIdList, "您被加入群:" + sessionUserInfo.getSessionName());
        return sessionUserInfo;
    }

    private Message getAddUserMessage(List<String> relateSessionUserIdList,String sessionId) {
        StringBuilder content = new StringBuilder();
        for (String addUserId : relateSessionUserIdList) {
            User user = userComponent.findUserById(addUserId);
            content.append(user.getUserName() + ", ");
        }
        content.append("被加入群");
        Message message = new Message();
        message.setContent(content.toString());
        message.setCreateTime(new Date());
        message.setSessionId(sessionId);
        message.setFromUser(userId);
        message.setMsgType("SYSTEM");
        message.setMessageId(uuidGenerator.shortUuid());
        return message;
    }

    private List<String> getAddUserIdList() {
        String[] userArray = userIds.split(",");
        List<String> userList = Arrays.asList(userArray);
        return userList;
    }

    private List<String> getRelateSessionUserIdList(List<String> addUserIdList, String userId, String sessionId) {
        List<String> list = new ArrayList<String>();
        list.addAll(addUserIdList);
        if (null == sessionId) {
            list.add(userId);
        }
        return list;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
