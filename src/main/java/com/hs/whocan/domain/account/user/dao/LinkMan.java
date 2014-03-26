package com.hs.whocan.domain.account.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public class LinkMan {
    private String linkmanId;
    private String userId;
    private String phoneNo;

    public String getLinkmanId() {
        return linkmanId;
    }

    public void setLinkmanId(String linkmanId) {
        this.linkmanId = linkmanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
