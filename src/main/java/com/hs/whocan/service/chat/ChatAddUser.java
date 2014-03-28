package com.hs.whocan.service.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class ChatAddUser extends ChatExecutor {
    private String userIds;

    public String execute() {
        String[] userArray = userIds.split(",");
        return chatRoomComponent.addPeopleToChatRoom(roomId,userId,userArray);
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
