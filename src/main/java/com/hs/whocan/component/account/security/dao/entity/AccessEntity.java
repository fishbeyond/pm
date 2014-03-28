package com.hs.whocan.component.account.security.dao.entity;

import com.hs.whocan.component.account.security.dao.Access;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:20
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "access_info")
public class AccessEntity {

    private Access access;
    public AccessEntity(){
        this.access = new Access();
    }
    public AccessEntity(Access access){
        this.access = access;
    }
    @Transient
    public Access getAccess() {
        return access;
    }

    @Id
    public String getAccessId() {
        return access.getAccessId();
    }
    @Column
    public String getPassword() {
        return access.getPassword();
    }

    public void setAliveTime(long aliveTime) {
        access.setAliveTime(aliveTime);
    }

    public void setPassword(String password) {
        access.setPassword(password);
    }
    @Column
    public long getAliveTime() {
        return access.getAliveTime();
    }

    public void setAccessId(String accessId) {
        access.setAccessId(accessId);
    }
    @Column
    public Date getAccessTime() {
        return access.getAccessTime();
    }

    public void setAccessTime(Date accessTime) {
        access.setAccessTime(accessTime);
    }

    public void setAccessToken(String accessToken) {
        access.setAccessToken(accessToken);
    }
    @Column
    public String getAccessToken() {
        return access.getAccessToken();
    }
}
