package com.hs.whocan.server.account.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午6:51
 * To change this template use File | Settings | File Templates.
 */
public interface UserMapperDao {
    public void createUserMapper(UserMapper userMapper);

    public void modifyUserMapperAlias(UserMapper userMapper);

    public void deleteUserMapper(String userId,String friendId);
}
