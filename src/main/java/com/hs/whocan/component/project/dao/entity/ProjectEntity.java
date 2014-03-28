package com.hs.whocan.component.project.dao.entity;

import com.hs.whocan.component.project.dao.Project;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "project")
public class ProjectEntity {
    private Project project;

    public ProjectEntity() {
        this.project = new Project();
    }

    public ProjectEntity(Project project) {
        this.project = project;
    }
    @Transient
    public Project getProject(){
        return project;
    }

    @Id
    public String getProjectId() {
        return project.getProjectId();
    }

    public void setUserId(String userId) {
        project.setUserId(userId);
    }

    public void setDeadline(Date deadline) {
        project.setDeadline(deadline.getTime());
    }
    @Column
    public Date getDeadline() {
        return new Date(project.getDeadline());
    }

    public void setCreateTime(Date createTime) {
        project.setCreateTime(createTime);
    }

    public void setProjectId(String projectId) {
        project.setProjectId(projectId);
    }
    @Column
    public String getProjectName() {
        return project.getProjectName();
    }

    public void setProjectName(String projectName) {
        project.setProjectName(projectName);
    }

    public void setDone(boolean done) {
        project.setDone(done);
    }

    public void setContent(String content) {
        project.setContent(content);
    }
    @Column
    public String getUserId() {
        return project.getUserId();
    }
    @Column
    public Date getCreateTime() {
        return project.getCreateTime();
    }
    @Column
    public boolean getDone() {
        return project.isDone();
    }
    @Column
    public String getContent() {
        return project.getContent();
    }
}
