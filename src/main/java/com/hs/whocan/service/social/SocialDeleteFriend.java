package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
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
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SocialDeleteFriend extends VerifySignInService {
    private String friendId;
    @Resource
    private UserMapperComponent userMapperComponent;

    public Boolean execute(User user) {
        userMapperComponent.deleteFriend(userId, friendId);
        return true;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
