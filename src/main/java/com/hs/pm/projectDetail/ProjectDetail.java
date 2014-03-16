package com.hs.pm.projectDetail;

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
@Table(name="project_detail")
public class ProjectDetail {
    private int detailId;
    private String detail;
    private Date operateTime;
    private int projectId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getDetailId() {
        return detailId;
    }
    @Column
    public String getDetail() {
        return detail;
    }
    @Column
    public Date getOperateTime() {
        return operateTime;
    }
    @Column
    public int getProjectId() {
        return projectId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
