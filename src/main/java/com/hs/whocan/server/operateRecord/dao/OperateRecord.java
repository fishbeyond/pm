package com.hs.whocan.server.operaterecord.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-11
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="operate_record")
public class OperateRecord {
    private String operateId;
    private String operateContent;
    private Date operateTime;
    private String projectId;
    public OperateRecord(){}
    public OperateRecord(String operateContent,String projectId){
        this.operateContent = operateContent;
        this.projectId = projectId;
        this.operateTime = new Date();
    }

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getOperateId() {
        return operateId;
    }
    @Column
    public String getOperateContent() {
        return operateContent;
    }
    @Column
    public Date getOperateTime() {
        return operateTime;
    }
    @Column
    public String getProjectId() {
        return projectId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
