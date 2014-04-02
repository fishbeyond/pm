package com.hs.whocan.service;

import com.hs.whocan.component.operaterecord.OperateRecordComponent;
import com.hs.whocan.component.project.ProjectService;
import com.hs.whocan.component.project.dao.Project;
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
    @Resource
    private OperateRecordComponent operateRecordService;

    public List<Project> findProjectByUserId(String userId) {
        return projectService.findProjectByUserId(userId);
    }

    public void createProject(Project project) {
        projectService.createProject(project);
        operateRecordService.createOperateRecord(project.getOperatorName()+":创建工程",project.getProjectId());
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
