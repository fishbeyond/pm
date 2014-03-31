package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SocialDeleteFriend implements WhoCanExecutor {
    private String friendId;
    @Resource
    private UserMapperComponent userMapperComponent;
    private String userId;


    public Boolean execute() {
        userMapperComponent.deleteFriend(userId, friendId);
        return true;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}