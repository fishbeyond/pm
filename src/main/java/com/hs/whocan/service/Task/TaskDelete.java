package com.hs.whocan.service.Task;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.task.TaskComponent;
import com.hs.whocan.service.NeedSignInService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope("prototype")
public class TaskDelete extends NeedSignInService {
    private String taskId;

    @Resource
    private TaskComponent taskComponent;

    @Override
    public Boolean execute(User user) {
        taskComponent.delete(taskId);
        return true;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
