package com.hs.whocan.component.session.dao.hbm;

import com.hs.whocan.component.session.SessionType;
import com.hs.whocan.component.session.dao.Session;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 下午7:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "session")
public class SessionEntity {
    private Session session;

    public SessionEntity() {
        session = new Session();
    }

    public SessionEntity(Session session) {
        this.session = session;
    }

    @Transient
    public Session getSession() {
        return session;
    }

    @Id
    public String getSessionId() {
        return session.getSessionId();
    }

    public void setSessionId(String sessionId) {
        session.setSessionId(sessionId);
    }

    @Column
    public String getSessionName() {
        return session.getSessionName();
    }

    public void setSessionName(String sessionName) {
        session.setSessionName(sessionName);
    }

    public void setCreateTime(Date createTime) {
        session.setCreateTime(createTime);
    }

    public void setUserId(String userId) {
        session.setUserId(userId);
    }

    @Column
    public Date getCreateTime() {
        return session.getCreateTime();
    }

    @Column
    public String getUserId() {
        return session.getUserId();
    }
    @Column
    public String getType() {
        return session.getType();
    }

    public void setType(String type) {
        session.setType(type);
    }
}
