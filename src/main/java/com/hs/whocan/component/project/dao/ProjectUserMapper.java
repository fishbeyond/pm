package com.hs.whocan.component.project.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午11:17
 * To change this template use File | Settings | File Templates.
 */

public class ProjectUserMapper {
    private String mapperId;
    private String userId;
    private String userName;
    private String phoneNo;
    private String projectId;

    public ProjectUserMapper() {
    }

    public ProjectUserMapper(String userId,String projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
