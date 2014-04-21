package com.hs.whocan.service.Task;

import com.hs.whocan.component.task.TaskComponent;
import com.hs.whocan.component.task.dao.Task;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope("prototype")
public class TaskFind extends WhoCanVerifyLoginService{
    private String sessionId;
     @Resource
    private TaskComponent taskComponent;


    @Override
    public List<Task> execute() {
        return taskComponent.find(sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
