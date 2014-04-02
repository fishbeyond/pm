package com.hs.whocan.component.session;

import com.hs.whocan.component.account.user.info.dao.User;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.component.session.dao.SessionDao;
import com.hs.whocan.service.session.SessionInfo;
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
    public SessionInfo querySessionInfo(String userId, Session session) {
        SessionInfo sessionInfo = new SessionInfo();
        List<User> users = sessionDao.findSessionUserBySessionId(session.getSessionId());
        sessionInfo.setUserList(users);
        sessionInfo.setSession(session);
        setSessionInfoName(sessionInfo, userId, users);
        return sessionInfo;
    }

    private void setSessionInfoName(SessionInfo sessionInfo, String userId, List<User> users) {
        if (sessionInfo.getType().equals(SessionType.PRIVATE_SESSION)) {
            for (User user : users) {
                if (userId.equals(user.getUserId())) {
                } else {
                    sessionInfo.setSessionName(user.getUserName());
                }
            }
        } else if (null == sessionInfo.getSessionName() || "".equals(sessionInfo.getSessionName())) {
            int userNum = sessionDao.findUserNumInSession(sessionInfo.getSessionId());
            sessionInfo.setSessionName("群聊(" + userNum + "人)");
        }
    }
}
