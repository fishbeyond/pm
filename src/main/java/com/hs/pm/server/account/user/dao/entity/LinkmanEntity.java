package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.Linkman;
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
@Table(name = "linkman")
public class LinkmanEntity {
    private Linkman linkman;

    public LinkmanEntity() {
        this.linkman = new Linkman();
    }

    public LinkmanEntity(Linkman linkman) {
        this.linkman = linkman;
    }

    public LinkmanEntity(String userId,String phoneNo) {
        this.linkman = new Linkman();
        linkman.setUserId(userId);
        linkman.setPhoneNo(phoneNo);
    }

    @Transient
    public Linkman getLinkman() {
        return linkman;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getLinkmanId() {
        return linkman.getLinkmanId();
    }

    @Column
    public String getUserId() {
        return linkman.getUserId();
    }

    public void setPhoneNo(String phoneNo) {
        linkman.setPhoneNo(phoneNo);
    }

    @Column
    public String getPhoneNo() {
        return linkman.getPhoneNo();
    }

    public void setLinkmanId(String linkmanId) {
        linkman.setLinkmanId(linkmanId);
    }

    public void setUserId(String userId) {
        linkman.setUserId(userId);
    }
}
