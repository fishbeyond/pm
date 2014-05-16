package com.hs.whocan.service.tasklist;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.tasklist.TaskComponent;
import com.hs.whocan.component.tasklist.dao.Task;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskFind extends VerifySignInService {
    private String sessionId;
     @Resource
    private TaskComponent taskComponent;


    @Override
    @Transactional
    public List<Task> execute(User user) {
        return taskComponent.find(sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
