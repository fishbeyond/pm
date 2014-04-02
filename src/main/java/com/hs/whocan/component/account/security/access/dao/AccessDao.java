package com.hs.whocan.component.account.security.access.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:03
 * To change this template use File | Settings | File Templates.
 */
public interface AccessDao {
    public Access findAccessInfoByPhoneNo(String phoneNo);

    public String findAccessIdByToken(String token);

    public Access findAccessInfoByToken(String token);

    public void createAccessInfo(Access newAccess);

    public Access findAccessIdByAuthCode(String phoneNo, int authCode);

    public Access findAccessInfoByUserId(String userId);

    public void modifyAccessInfo(Access access);

    public void modifyAccessToken(String accessId,String token);
}
