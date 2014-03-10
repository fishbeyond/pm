package com.hs.pm.linkman;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 上午9:11
 * To change this template use File | Settings | File Templates.
 */
public class Linkman {
    private int ownerId;
    private String phoneNo;
    private String mailAddress;
    private String linkmanName;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }
}
