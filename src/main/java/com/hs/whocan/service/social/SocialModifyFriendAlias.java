package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.component.account.user.dao.UserMapper;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SocialModifyFriendAlias extends VerifySignInService {
    private String mapperId;
    private String friendId;
    private String alias;
    @Resource
    private UserMapperComponent userMapperComponent;

    public Boolean execute(User user) {
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
}
