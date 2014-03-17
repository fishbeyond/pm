package com.hs.pm.project;

import com.hs.pm.project.dao.Project;
import com.hs.pm.project.dao.ProjectDao;
import com.hs.pm.project.dao.ProjectUserMapper;
import com.hs.pm.user.dao.User;
import com.hs.pm.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    private UserDao userDao;

    public List<Project> findProjectByUserId(String userId) {
        return projectDao.findProjectByUserId(userId);
    }

    public void createProject(Project project) {
        projectDao.createProject(project);
    }

    public void modifyProject(Project project) {
        projectDao.modifyProject(project);
    }

    public void deleteProject(String projectId) {
        projectDao.deleteProject(projectId);
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

    public void bindProjectAndUser(String userId,String phoneNo){
        List<ProjectUserMapper> projectUserMappers = projectDao.findProjectUserMapperByPhoneNo(phoneNo);
        for(ProjectUserMapper projectUserMapper: projectUserMappers){
            projectUserMapper.setUserId(userId);
        }
    }
}
