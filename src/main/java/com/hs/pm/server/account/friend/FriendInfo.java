package com.hs.pm.server.account.friend;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
public class FriendInfo {
    private String userId;
    private String userName;
    private String phoneNo;
    private String mailAddress;
    private String gender;
    private String remark;
    private String alias;
    private Enum<FriendStatus> statusEnum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Enum<FriendStatus> getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(Enum<FriendStatus> statusEnum) {
        this.statusEnum = statusEnum;
    }
}
