package com.hs.pm.server.work.dao.entity;

import com.hs.pm.server.work.dao.Work;
import com.hs.pm.server.work.dao.WorkDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WorkRepository implements WorkDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void createWork(Work work) {
        sessionFactory.getCurrentSession().save(new WorkEntity(work));
    }

    @Override
    public void modifyWork(Work work) {
        sessionFactory.getCurrentSession().update(new WorkEntity(work));
    }

    @Override
    public void deleteWork(String workId) {
        final String hql = "delete from WorkEntity e where e.workId = :workId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("workId", workId);
        query.executeUpdate();
    }

    @Override
    public List<Work> findWorkById(String projectId) {
        final String hql = "from WorkEntity e where e.projectId = :projectId order by e.createTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("projectId", projectId);
        List<WorkEntity> entities = (List<WorkEntity>) query.list();
        List<Work> works = new ArrayList<Work>();
        for (WorkEntity entity : entities) {
            works.add(entity.getWork());
        }
        return works;
    }
}
