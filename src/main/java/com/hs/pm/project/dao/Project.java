package com.hs.pm.project.dao;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-14
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "Projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
    @XmlTransient
    private String projectId;
    @XmlElement(name = "ProjectName")
    private String projectName;
    @XmlElement(name = "Content")
    private String content;
    @XmlElement(name = "Deadline")
    private long deadline;
    @XmlElement(name = "IsDone")
    private boolean isDone;
    @XmlElement(name = "Operator")
    private String createUserId;
    @XmlElement(name = "OperateTime")
    private long createTime;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
