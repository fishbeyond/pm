package com.hs.pm.project;

import com.hs.pm.operateRecord.OperateRecordService;
import com.hs.pm.operateRecord.dao.OperateRecordDao;
import com.hs.pm.project.dao.Project;
import com.hs.pm.project.dao.ProjectDao;
import com.hs.pm.project.dao.ProjectUserMapper;
import com.hs.pm.security.dao.AccessInfo;
import com.hs.pm.security.dao.AccessInfoDao;
import com.hs.pm.user.dao.User;
import com.hs.pm.user.dao.UserDao;
import com.hs.pm.utils.UUIDGenerator;
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
    private UserDao userDao;
    @Resource
    private AccessInfoDao accessInfoDao;
    @Resource
    private OperateRecordService operateRecordService;
    @Resource
    private UUIDGenerator uuidGenerator;

    public List<Project> findProjectByUserId(String userId) {
        return projectDao.findProjectByUserId(userId);
    }

    public boolean createProject(Project project) {
        project.setCreateTime(new Date());
        project.setDone(false);
        String projectId = uuidGenerator.shortUuid();
        project.setProjectId(projectId);
        projectDao.createProject(project);
        ProjectUserMapper projectUserMapper = new ProjectUserMapper(project.getCreateUserId(), projectId);
        projectDao.createProjectUserMapper(projectUserMapper);
        operateRecordService.createOperateRecord(project.getOperatorName()+":创建工程",project.getProjectId());
        return true;
    }

    public boolean modifyProject(Project project) {
        projectDao.modifyProject(project);
        return true;
    }

    //todo 是否删除所有相关数据
    public boolean deleteProject(String projectId) {
        projectDao.deleteProject(projectId);
        projectDao.deleteProjectUserMapper(projectId);
        return true;
    }

    public boolean addMemberFromFriend(String projectId, List<String> userIdList) {
        for (String userId : userIdList) {
            ProjectUserMapper projectUserMapper = new ProjectUserMapper();
            projectUserMapper.setProjectId(projectId);
            projectUserMapper.setUserId(userId);
            projectDao.createProjectUserMapper(projectUserMapper);
        }
        return true;
    }

    public boolean addMemberFromLinkMan(String projectId, List<String> phoneNoList) {
        for (String phoneNo : phoneNoList) {
            ProjectUserMapper projectUserMapper = new ProjectUserMapper();
            projectUserMapper.setProjectId(projectId);
            projectUserMapper.setPhoneNo(phoneNo);
            projectDao.createProjectUserMapper(projectUserMapper);
            String userId = uuidGenerator.shortUuid();
            userDao.createUser(new User(userId,phoneNo));
            accessInfoDao.createAccessInfo(new AccessInfo(userId,phoneNo));
        }
        return true;
    }

}
