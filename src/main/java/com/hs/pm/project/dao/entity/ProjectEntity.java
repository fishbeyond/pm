package com.hs.pm.project.dao.entity;

import com.hs.pm.project.dao.Project;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getProjectId() {
        return project.getProjectId();
    }

    public void setCreateUserId(String createUserId) {
        project.setCreateUserId(createUserId);
    }

    public void setDeadline(long deadline) {
        project.setDeadline(deadline);
    }
    @Column
    public long getDeadline() {
        return project.getDeadline();
    }

    public void setCreateTime(long createTime) {
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
    public String getCreateUserId() {
        return project.getCreateUserId();
    }
    @Column
    public long getCreateTime() {
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
