package com.hs.whocan.service.social;

import com.hs.whocan.component.account.security.PushMessageComponent;
import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.component.account.user.exception.FriendAlreadyExistException;
import com.hs.whocan.service.WhocanNeedLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SocialAddFriendRegister extends WhocanNeedLoginService {
    private String friendId;

    @Resource
    private UserMapperComponent userMapperComponent;
    @Resource
    private PushMessageComponent pushMessageComponent;

    public Boolean execute() {
        userMapperComponent.addFriendAlreadyRegister(userId, friendId);
        List<String> users = new ArrayList<String>();
        users.add(friendId);
        pushMessageComponent.push(users, "新的好友邀请:来自" + operator);
        return true;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

}
