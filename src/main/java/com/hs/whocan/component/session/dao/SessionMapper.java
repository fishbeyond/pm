package com.hs.whocan.component.session.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-27
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="session_mapper")
public class SessionMapper {
    private String mapperId;
    private String sessionId;
    private String userId;
    private Date addTime;

    public SessionMapper() {
    }

    public SessionMapper(String sessionId, String userId) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.addTime = new Date();
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    @Column
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    @Column
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
