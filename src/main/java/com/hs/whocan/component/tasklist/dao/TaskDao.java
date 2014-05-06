package com.hs.whocan.component.tasklist.dao;

import java.util.List;

/**
 * Created by fish on 14-4-21.
 */
public interface TaskDao {
    public List<Task> find(String sessionId);

    public void create(Task task);

    public void delete(String taskId);

    public void modify(Task task);
}
