package com.hs.pm.linkman;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "linkman")
public class Linkman {
    private int linkmanId;
    private int ownerId;
    private String phoneNo;
    private String mailAddress;
    private String linkmanName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getLinkmanId() {
        return linkmanId;
    }
    @Column
    public int getOwnerId() {
        return ownerId;
    }
    @Column
    public String getPhoneNo() {
        return phoneNo;
    }
    @Column
    public String getMailAddress() {
        return mailAddress;
    }
    @Column
    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanId(int linkmanId) {
        this.linkmanId = linkmanId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }
}
