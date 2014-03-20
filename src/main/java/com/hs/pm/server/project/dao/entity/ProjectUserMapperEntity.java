package com.hs.pm.server.project.dao.entity;

import com.hs.pm.server.project.dao.ProjectUserMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午11:16
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "project_user_mapper")
public class ProjectUserMapperEntity {
    private ProjectUserMapper projectUserMapper;

    public ProjectUserMapperEntity() {
        this.projectUserMapper = new ProjectUserMapper();
    }

    public ProjectUserMapperEntity(ProjectUserMapper projectUserMapper) {
        this.projectUserMapper = projectUserMapper;
    }
    @Transient
    public ProjectUserMapper getProjectUserMapper(){
        return projectUserMapper;
    }
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getMapperId() {
        return projectUserMapper.getMapperId();
    }

    public void setPhoneNo(String phoneNo) {
        projectUserMapper.setPhoneNo(phoneNo);
    }
    @Column
    public String getUserName() {
        return projectUserMapper.getUserName();
    }

    @Column
    public String getPhoneNo() {
        return projectUserMapper.getPhoneNo();
    }

    public void setProjectId(String projectId) {
        projectUserMapper.setProjectId(projectId);
    }

    public void setUserName(String userName) {
        projectUserMapper.setUserName(userName);
    }
    @Column
    public String getUserId() {
        return projectUserMapper.getUserId();
    }

    public void setUserId(String userId) {
        projectUserMapper.setUserId(userId);
    }

    @Column
    public String getProjectId() {
        return projectUserMapper.getProjectId();
    }

    public void setMapperId(String mapperId) {
        projectUserMapper.setMapperId(mapperId);
    }

}
