package com.hs.whocan.component.tasklist;

import com.hs.whocan.component.tasklist.dao.Task;
import com.hs.whocan.component.tasklist.dao.TaskDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fish on 14-4-21.
 */
@Service
public class TaskComponent {
    @Resource
    private TaskDao taskDao;

    public List<Task> find (String sessionId){
        return taskDao.find(sessionId);
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
