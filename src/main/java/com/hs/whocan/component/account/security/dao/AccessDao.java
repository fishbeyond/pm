package com.hs.whocan.component.account.security.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 上午9:03
 * To change this template use File | Settings | File Templates.
 */
public interface AccessDao {

    public Access findAccessByToken(String token);

    public void createAccess(Access newAccess);

    public void modifyAccessToken(String accessId,String token);

}
