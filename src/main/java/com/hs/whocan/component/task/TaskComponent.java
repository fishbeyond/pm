package com.hs.whocan.component.task;

import com.hs.whocan.component.task.dao.Task;
import com.hs.whocan.component.task.dao.TaskDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fish on 14-4-21.
 */
@Service
public class TaskComponent {
    @Resource
    private TaskDao taskDao;

    public void find (String sessionId){
        taskDao.find(sessionId);
    }
    public void create(Task task){
        taskDao.create(task);
    }

    public void delete(String taskId){
        taskDao.delete(taskId);
    }

    public void modify(Task task){
        taskDao.modify(task);
    }
}
