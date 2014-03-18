package com.hs.pm.security.dao.entity;

import com.hs.pm.security.dao.AccessInfo;
import org.hibernate.annotations.GenericGenerator;

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
public class AccessInfoEntity {

    private AccessInfo accessInfo;
    public AccessInfoEntity(){
        this.accessInfo = new AccessInfo();
    }
    public AccessInfoEntity(AccessInfo accessInfo){
        this.accessInfo = accessInfo;
    }
    @Transient
    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getAccessId() {
        return accessInfo.getAccessId();
    }

    public void setAuthCode(int authCode) {
        accessInfo.setAuthCode(authCode);
    }
    @Column
    public String getPassword() {
        return accessInfo.getPassword();
    }

    public void setAliveTime(long aliveTime) {
        accessInfo.setAliveTime(aliveTime);
    }
    @Column
    public int getAuthCode() {
        return accessInfo.getAuthCode();
    }

    public void setPassword(String password) {
        accessInfo.setPassword(password);
    }
    @Column
    public long getAliveTime() {
        return accessInfo.getAliveTime();
    }

    public void setPhoneNo(String phoneNo) {
        accessInfo.setPhoneNo(phoneNo);
    }

    public void setAccessId(String accessId) {
        accessInfo.setAccessId(accessId);
    }
    @Column
    public Date getAccessTime() {
        return accessInfo.getAccessTime();
    }
    @Column
    public String getPhoneNo() {
        return accessInfo.getPhoneNo();
    }

    public void setAccessTime(Date accessTime) {
        accessInfo.setAccessTime(accessTime);
    }

    public void setAccessToken(String accessToken) {
        accessInfo.setAccessToken(accessToken);
    }
    @Column
    public String getAccessToken() {
        return accessInfo.getAccessToken();
    }
}
