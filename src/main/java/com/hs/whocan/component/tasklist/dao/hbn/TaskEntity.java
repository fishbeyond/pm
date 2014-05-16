package com.hs.whocan.component.tasklist.dao.hbn;

import com.hs.whocan.component.tasklist.dao.Task;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fish on 14-4-21.
 */
@Entity
@Table(name="task")
public class TaskEntity {

    private Task task;
    public TaskEntity() {
        this.task = new Task();
    }

    public TaskEntity(Task task) {
        this.task = task;
    }
    @Transient
    public Task getTask(){
        return this.task;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getTaskId() {
        return task.getTaskId();
    }

    @Column
    public String getGroupId() {
        return task.getGroupId();
    }

    public void setTaskId(String taskId) {
        task.setTaskId(taskId);
    }

    public void setOwner(String owner) {
        task.setOwner(owner);
    }

    @Column
    public Date getCreateTime() {
        return task.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        task.setCreateTime(createTime);
    }

    @Column
    public String getCreateUser() {
        return task.getCreateUser();
    }

    public void setDescription(String description) {
        task.setDescription(description);
    }

    @Column
    public String getParentId() {
        return task.getParentId();
    }

    @Column
    public int getRate() {
        return task.getRate();
    }

    public void setCreateUser(String createUser) {
        task.setCreateUser(createUser);
    }

    @Column
    public Date getDeadline() {
        return task.getDeadline();
    }

    public void setTitle(String title) {
        task.setTitle(title);
    }

    public void setGroupId(String groupId) {
        task.setGroupId(groupId);
    }

    public void setTop(int top) {
        task.setTop(top);
    }

    public void setStatus(String status) {
        task.setStatus(status);
    }

    @Column
    public String getTitle() {
        return task.getTitle();
    }

    @Column
    public String getStatus() {
        return task.getStatus();
    }

    public void setDeadline(Date deadline) {
        task.setDeadline(deadline);
    }

    @Column
    public String getOwner() {
        return task.getOwner();
    }

    @Column
    public int getTop() {
        return task.getTop();
    }

    public void setParentId(String parentId) {
        task.setParentId(parentId);
    }

    public void setType(String type) {
        task.setType(type);
    }

    @Column
    public String getDescription() {
        return task.getDescription();
    }

    @Column
    public String getType() {
        return task.getType();
    }

    public void setRate(int rate) {
        task.setRate(rate);
    }
}
