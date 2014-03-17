package com.hs.pm.project.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectDao {
    public void createProject(Project project);

    public void modifyProject(Project project);

    public List<Project> findProjectByUserId(String userId);

    public void deleteProject(String projectId);

    public void createProjectUserMapper(ProjectUserMapper projectUserMapper);

    List<ProjectUserMapper> findProjectUserMapperByPhoneNo(String phoneNo);
}
