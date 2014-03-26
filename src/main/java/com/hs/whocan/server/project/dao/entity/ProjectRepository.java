package com.hs.whocan.server.project.dao.entity;

import com.hs.whocan.server.project.dao.Project;
import com.hs.whocan.server.project.dao.ProjectDao;
import com.hs.whocan.server.project.dao.ProjectUserMapper;
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
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProjectRepository implements ProjectDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void createProject(Project project) {
        sessionFactory.getCurrentSession().save(new ProjectEntity(project));
    }

    @Override
    public void modifyProject(Project project) {
        sessionFactory.getCurrentSession().update(new ProjectEntity(project));
    }

    @Override
    public List<Project> findProjectByUserId(String userId) {
        final String hql = "from ProjectEntity e where e.projectId in (select m.projectId from ProjectUserMapperEntity m where m.userId = :userId)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("userId", userId);
        List<ProjectEntity> entities = (List<ProjectEntity>) query.list();
        List<Project> projects = new ArrayList<Project>();
        for (ProjectEntity projectEntity : entities) {
            projects.add(projectEntity.getProject());
        }
        return projects;
    }

    @Override
    public void deleteProject(String projectId) {
        final String hql = "delete from ProjectEntity e where e.projectId = :projectId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("projectId", projectId);
        query.executeUpdate();
    }

    @Override
    public void deleteProjectUserMapper(String projectId) {
        final String hql = "delete from ProjectUserMapperEntity e where e.projectId = :projectId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("projectId", projectId);
        query.executeUpdate();
    }

    @Override
    public void createProjectUserMapper(ProjectUserMapper projectUserMapper) {
        sessionFactory.getCurrentSession().save(new ProjectUserMapperEntity(projectUserMapper));
    }

    @Override
    public List<ProjectUserMapper> findProjectUserMapperByPhoneNo(String phoneNo) {
        final String hql = "from ProjectUserMapperEntity e where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        List<ProjectUserMapperEntity> entities = (List<ProjectUserMapperEntity>) query.list();
        List<ProjectUserMapper> mappers = new ArrayList<ProjectUserMapper>();
        for (ProjectUserMapperEntity entity : entities) {
            mappers.add(entity.getProjectUserMapper());
        }
        return mappers;
    }

    @Override
    public void bindProjectAndUser(String phoneNo, String userId) {
        final String hql = "update ProjectUserMapperEntity e set e.userId = :userId  where e.phoneNo = :phoneNo";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("phoneNo", phoneNo);
        query.setString("userId", userId);
        query.executeUpdate();
    }

}
