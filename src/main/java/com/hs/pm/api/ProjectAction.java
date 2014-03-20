package com.hs.pm.api;

import com.hs.pm.server.project.ProjectService;
import com.hs.pm.server.project.dao.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProjectAction {
    @Resource
    private ProjectService projectService;

    public List<Project> findProjectByUserId(String userId) {
        return projectService.findProjectByUserId(userId);
    }

    public void createProject(Project project) {
        projectService.createProject(project);
    }

    public void modifyProject(Project project) {
        projectService.modifyProject(project);
    }

    public void deleteProject(String projectId) {
        projectService.deleteProject(projectId);
    }

    public void addMemberFromFriend(String projectId, List<String> userIdList) {
        projectService.addMemberFromFriend(projectId, userIdList);
    }

    public void addMemberFromLinkMan(String projectId, List<String> phoneNoList) {
        projectService.addMemberFromLinkMan(projectId, phoneNoList);
    }
}
