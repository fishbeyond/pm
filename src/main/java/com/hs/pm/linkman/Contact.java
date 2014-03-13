package com.hs.pm.linkman;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "contact")
public class Contact {
    private String contactId;
    private int projectId;
    private String userId;
    private String phoneNo;
    private String mailAddress;
    private String userName;

    @Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    public String getContactId() {
        return contactId;
    }
    @Column
    public int getProjectId() {
        return projectId;
    }
    @Column
    public String getUserId() {
        return userId;
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
    public String getUserName() {
        return userName;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
