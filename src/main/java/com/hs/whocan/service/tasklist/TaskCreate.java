package com.hs.whocan.service.tasklist;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.tasklist.TaskComponent;
import com.hs.whocan.component.tasklist.dao.Task;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskCreate extends VerifySignInService {
    private String title;
    private String groupId;
    private String description;
    private int rate;
    private int top;
    private String createUser;
    private String owner;
    private String deadline;
    private String status;
    private String parentId;
    private String type;
    @Resource
    private TaskComponent taskComponent;

    @Override
    @Transactional
    public String execute(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Task task = new Task();
        task.setTitle(title);
        task.setGroupId(groupId);
        task.setDescription(description);
        task.setRate(rate);
        task.setTop(top);
        task.setCreateUser(userId);
        task.setOwner(owner);
        task.setCreateTime(new Date());
        task.setDeadline(date);
        task.setStatus(status);
        task.setParentId(parentId);
        task.setType(type);
        taskComponent.create(task);
        return task.getTaskId();
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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

    @Override
    public String toString() {
        return "TaskCreate{" +
                "title='" + title + '\'' +
                ", groupId='" + groupId + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", top=" + top +
                ", createUser='" + createUser + '\'' +
                ", owner='" + owner + '\'' +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", parentId='" + parentId + '\'' +
                ", type='" + type + '\'' +
                ", taskComponent=" + taskComponent +
                '}';
    }
}
