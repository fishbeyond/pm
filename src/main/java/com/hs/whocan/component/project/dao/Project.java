package com.hs.whocan.component.project.dao;

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
    private String userId;
    private Date createTime;
    private String operatorName;
    private long operateTime;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(long operateTime) {
        this.operateTime = operateTime;
    }
}
