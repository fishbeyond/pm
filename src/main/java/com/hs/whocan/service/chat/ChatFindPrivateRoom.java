package com.hs.whocan.service.chat;

import com.hs.whocan.component.chat.ChatRoomComponent;
import com.hs.whocan.service.WhoCanExecutor;
import com.hs.whocan.service.chat.old.ChatRoomInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class ChatFindPrivateRoom extends WhoCanExecutor {

    protected String friendId;

    @Resource
    protected ChatRoomComponent chatRoomComponent;

    public ChatRoomInfo execute() {
        return chatRoomComponent.findPrivateRoom(userId, friendId);
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
