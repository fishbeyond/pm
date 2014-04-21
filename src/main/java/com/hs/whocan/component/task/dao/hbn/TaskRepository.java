package com.hs.whocan.component.task.dao.hbn;

import com.hs.whocan.component.task.dao.Task;
import com.hs.whocan.component.task.dao.TaskDao;
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
        sessionFactory.getCurrentSession().save(task);
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
