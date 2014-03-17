package com.hs.pm.work.dao.entity;

import com.hs.pm.work.dao.Work;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "work")
public class WorkEntity {
    private Work work;

    public WorkEntity() {
        this.work = new Work();
    }

    public WorkEntity(Work work) {
        this.work = work;
    }
    @Transient
    public Work getWork(){
        return this.work;
    }
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getWorkId() {
        return work.getWorkId();
    }

    public void setWorkName(String workName) {
        work.setWorkName(workName);
    }

    public void setCreateUserId(String createUserId) {
        work.setCreateUserId(createUserId);
    }

    public void setProjectId(String projectId) {
        work.setProjectId(projectId);
    }

    public void setInfo(String info) {
        work.setInfo(info);
    }
    @Column
    public String getInfo() {
        return work.getInfo();
    }
    @Column
    public Date getDeadline() {
        return work.getDeadline();
    }

    public boolean isDone() {
        return work.isDone();
    }

    public void setCreateTime(Date createTime) {
        work.setCreateTime(createTime);
    }

    public void setDeadline(Date deadline) {
        work.setDeadline(deadline);
    }

    public void setDone(boolean done) {
        work.setDone(done);
    }
    @Column
    public String getProjectId() {
        return work.getProjectId();
    }

    public void setWorkId(String workId) {
        work.setWorkId(workId);
    }
    @Column
    public String getWorkName() {
        return work.getWorkName();
    }
    @Column
    public String getCreateUserId() {
        return work.getCreateUserId();
    }
    @Column
    public Date getCreateTime() {
        return work.getCreateTime();
    }
}
