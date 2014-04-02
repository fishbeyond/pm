package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.component.account.user.friend.dao.UserMapper;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SocialModifyFriendAlias implements WhoCanExecutor {
    private String mapperId;
    private String friendId;
    private String alias;
    @Resource
    private UserMapperComponent userMapperComponent;
    private String userId;

    public Boolean execute() {
        UserMapper userMapper = new UserMapper();
        userMapper.setUserId(userId);
        userMapper.setAlias(alias);
        userMapper.setFriendId(friendId);
        userMapperComponent.modifyUserMapperAlias(userMapper);
        return true;
    }

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
