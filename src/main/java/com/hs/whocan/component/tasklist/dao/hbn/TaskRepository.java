package com.hs.whocan.component.tasklist.dao.hbn;

import com.hs.whocan.component.tasklist.dao.Task;
import com.hs.whocan.component.tasklist.dao.TaskDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fish on 14-4-21.
 */
@Repository
public class TaskRepository implements TaskDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<Task> find(String sessionId) {
        final String hql ="from TaskEntity e where e.groupId = :groupId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<TaskEntity> entities = (List<TaskEntity>)query.list();
        List<Task> taskList = new ArrayList<Task>();
        for(TaskEntity entity:entities){
            taskList.add(entity.getTask());
        }
        return taskList;
    }

    @Override
    public void create(Task task) {
        TaskEntity entity = new TaskEntity(task);
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(String taskId) {
        final String hql = "delete from TaskEntity e where e.taskId = :taskId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("taskId",taskId);
        query.executeUpdate();
    }

    @Override
    public void modify(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }
}
