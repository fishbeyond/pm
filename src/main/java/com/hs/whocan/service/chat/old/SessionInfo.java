package com.hs.whocan.service.chat.old;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.session.dao.Session;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class SessionInfo {
    private String sessionId;
    private String userId;
    private String sessionName;
    private Date createTime;
    private List<User> userList;

    public void setChatRoom(Session session) {
        this.sessionId = session.getSessionId();
        this.userId = session.getUserId();
        this.sessionName = session.getSessionName();
        this.createTime = session.getCreateTime();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
