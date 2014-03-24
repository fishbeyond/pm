package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.LinkMan;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "link_man")
public class LinManEntity {
    private LinkMan linkMan;

    public LinManEntity() {
        this.linkMan = new LinkMan();
    }

    public LinManEntity(LinkMan linkMan) {
        this.linkMan = linkMan;
    }
    @Transient
    public LinkMan getLinkMan(){
        return linkMan;
    }
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getLinkManeId() {
        return linkMan.getLinkManeId();
    }
    @Column
    public String getPhoneNo() {
        return linkMan.getPhoneNo();
    }
    @Column
    public String getUserId() {
        return linkMan.getUserId();
    }

    public void setPhoneNo(String phoneNo) {
        linkMan.setPhoneNo(phoneNo);
    }

    public void setUserId(String userId) {
        linkMan.setUserId(userId);
    }

    public void setLinkManeId(String linkManeId) {
        linkMan.setLinkManeId(linkManeId);
    }

    public void setLinkManName(String linkManName) {
        linkMan.setLinkManName(linkManName);
    }
    @Column
    public String getLinkManName() {
        return linkMan.getLinkManName();
    }
}
