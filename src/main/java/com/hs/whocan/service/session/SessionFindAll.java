package com.hs.whocan.service.session;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.component.session.SessionQuery;
import com.hs.whocan.component.session.dao.Session;
import com.hs.whocan.service.WhoCanNeedLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SessionFindAll extends WhoCanNeedLoginService {

    @Resource
    private SessionComponent sessionComponent;
    @Resource
    private SessionQuery sessionQuery;

    @Transactional
    public List<SessionInfo> execute() {
        List<Session> sessions = sessionComponent.findSession(userId);
        List<SessionInfo> sessionInfos = new ArrayList<SessionInfo>();
        for (Session session : sessions) {
            SessionInfo sessionInfo = sessionQuery.querySessionInfo(userId, session);
            sessionInfos.add(sessionInfo);
        }
        return sessionInfos;
    }

}
