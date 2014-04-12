package com.hs.whocan.component.session;

import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.component.session.dao.SessionDao;
import com.hs.whocan.service.session.dto.SessionUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午7:33
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SessionQuery {

    @Resource
    private SessionDao sessionDao;
    @Transactional
    public SessionUserInfo querySessionInfo(String userId, Session session) {
        SessionUserInfo sessionUserInfo = new SessionUserInfo();
        List<User> users = sessionDao.findSessionUserBySessionId(session.getSessionId());
        sessionUserInfo.setUserList(users);
        sessionUserInfo.setSession(session);
        setSessionInfoName(sessionUserInfo, userId, users);
        return sessionUserInfo;
    }

    private void setSessionInfoName(SessionUserInfo sessionUserInfo, String userId, List<User> users) {
        if (sessionUserInfo.getType().equals(SessionType.PRIVATE_SESSION)) {
            for (User user : users) {
                if (userId.equals(user.getUserId())) {
                } else {
                    sessionUserInfo.setSessionName(user.getUserName());
                }
            }
        } else if (null == sessionUserInfo.getSessionName() || "".equals(sessionUserInfo.getSessionName())) {
            int userNum = sessionDao.findUserNumInSession(sessionUserInfo.getSessionId());
            sessionUserInfo.setSessionName("群聊(" + userNum + "人)");
        }
    }
}
