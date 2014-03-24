package com.hs.pm.server.account.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public class LinkMan {
    private String linkManeId;
    private String userId;
    private String linkManName;
    private String phoneNo;

    public String getLinkManeId() {
        return linkManeId;
    }

    public void setLinkManeId(String linkManeId) {
        this.linkManeId = linkManeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLinkManName() {
        return linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
