package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.PushMessageComponent;
import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.component.session.dao.Message;
import com.hs.whocan.service.ValidatorService;
import com.hs.whocan.service.WhoCanVerifyLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
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
public class SocialAddFriendRegister extends WhoCanVerifyLoginService {


    @NotNull
    private String friendId;

    @Resource
    private ValidatorService validatorService;
    @Resource
    private UserMapperComponent userMapperComponent;
    @Resource
    private PushMessageComponent pushMessageComponent;

    @Transactional
    public Boolean execute() {
        validatorService.validate(this);
        if (userId.equals(friendId)) {
            return true;
        }
        if (userMapperComponent.isExistInvitation(userId,friendId)) {
            userMapperComponent.createFriend(userId, friendId);
        } else {
            userMapperComponent.createInvitation(userId, friendId, null);
        }

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
