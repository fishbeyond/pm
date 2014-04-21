package com.hs.whocan.service.Task;

import com.hs.whocan.component.task.TaskComponent;
import com.hs.whocan.component.task.dao.Task;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope("prototype")
public class TaskCreate extends WhoCanVerifyLoginService {
    private String title;
    private String groupId;
    private String description;
    private int rate;
    private int top;
    private String createUser;
    private String owner;
    private long deadline;
    private String status;
    private String parentId;
    private String type;
    @Resource
    private TaskComponent taskComponent;

    @Override
    public Boolean execute() {
        Task task = new Task();
        task.setTitle(title);
        task.setGroupId(groupId);
        task.setDescription(description);
        task.setRate(rate);
        task.setTop(top);
        task.setCreateUser(userId);
        task.setOwner(owner);
        task.setCreateTime(new Date());
        task.setDeadline(new Date(deadline));
        task.setStatus(status);
        task.setParentId(parentId);
        task.setType(type);
        taskComponent.create(task);
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
