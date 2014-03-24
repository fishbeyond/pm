package com.hs.pm.server.account.security.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:03
 * To change this template use File | Settings | File Templates.
 */
public interface AccessInfoDao {
    public AccessInfo findAccessInfoByPhoneNo(String phoneNo);

    public String findAccessIdByToken(String token);

    public AccessInfo findAccessInfoByToken(String token);

    public void createAccessInfo(AccessInfo newAccessInfo);

    public AccessInfo findAccessIdByAuthCode(String phoneNo, int authCode);

    public AccessInfo findAccessInfoByUserId(String userId);

    public void modifyAccessInfo(AccessInfo accessInfo);

    public void modifyAccessToken(String accessId,String token);
}
