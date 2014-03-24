package com.hs.pm.server.project;

import com.hs.pm.server.project.dao.Project;
import com.hs.pm.server.project.dao.ProjectDao;
import com.hs.pm.server.project.dao.ProjectUserMapper;
import com.hs.pm.server.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 下午1:22
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ProjectService {
    @Resource
    private ProjectDao projectDao;

    @Resource
    private UUIDGenerator uuidGenerator;

    public List<Project> findProjectByUserId(String userId) {
        return projectDao.findProjectByUserId(userId);
    }

    public void createProject(Project project) {
        project.setCreateTime(new Date());
        project.setDone(false);
        String projectId = uuidGenerator.shortUuid();
        project.setProjectId(projectId);
        projectDao.createProject(project);
        ProjectUserMapper projectUserMapper = new ProjectUserMapper(project.getUserId(), projectId);
        projectDao.createProjectUserMapper(projectUserMapper);
    }

    public void modifyProject(Project project) {
        projectDao.modifyProject(project);
    }

    //todo 是否删除所有相关数据
    public void deleteProject(String projectId) {
        projectDao.deleteProject(projectId);
        projectDao.deleteProjectUserMapper(projectId);
    }

    public void addMemberFromFriend(String projectId, List<String> userIdList) {
        for (String userId : userIdList) {
            ProjectUserMapper projectUserMapper = new ProjectUserMapper();
            projectUserMapper.setProjectId(projectId);
            projectUserMapper.setUserId(userId);
            projectDao.createProjectUserMapper(projectUserMapper);
        }
    }

    public void addMemberFromLinkMan(String projectId, List<String> phoneNoList) {
        for (String phoneNo : phoneNoList) {
            ProjectUserMapper projectUserMapper = new ProjectUserMapper();
            projectUserMapper.setProjectId(projectId);
            projectUserMapper.setPhoneNo(phoneNo);
            projectDao.createProjectUserMapper(projectUserMapper);
        }
    }

}
