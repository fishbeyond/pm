package com.hs.whocan.service.session;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.WhoCanExecutor;
import com.hs.whocan.service.session.old.SessionInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import javax.annotation.Resource;
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
public class SessionFindAll extends WhoCanExecutor {

    @Resource
    private SessionComponent sessionComponent;

    public List<SessionInfo> execute() {
        return sessionComponent.findSessionInfo(userId);
    }

}
