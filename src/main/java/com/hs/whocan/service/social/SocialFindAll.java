package com.hs.whocan.service.social;

import com.hs.whocan.component.account.user.UserMapperComponent;
import com.hs.whocan.service.WhoCanNeedLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午5:56
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SocialFindAll extends WhoCanNeedLoginService {

    @Resource
    private UserMapperComponent userMapperComponent;

    public List<FriendInfo> execute() {
        List<FriendInfo> alreadyFriends = userMapperComponent.findFriendByUserId(userId);
        List<FriendInfo> inviteFriends = userMapperComponent.findFriendInvite(userId);
        List<FriendInfo> invitedFriends = userMapperComponent.findFriendInvited(userId);
        List<FriendInfo> notAddFriends = userMapperComponent.findFriendNotAdd(userId);
        ArrayList<FriendInfo> list = new ArrayList<FriendInfo>();
        list.addAll(alreadyFriends);
        list.addAll(inviteFriends);
        list.addAll(invitedFriends);
        list.addAll(notAddFriends);
        return list;
    }
}
