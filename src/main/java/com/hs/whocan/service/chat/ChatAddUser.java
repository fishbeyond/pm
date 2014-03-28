package com.hs.whocan.service.chat;

import com.hs.whocan.component.chat.ChatRoomComponent;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class ChatAddUser extends WhoCanExecutor {
    protected String roomId;
    private String userIds;
    @Resource
    protected ChatRoomComponent chatRoomComponent;

    public String execute() {
        String[] userArray = userIds.split(",");
        return chatRoomComponent.addPeopleToChatRoom(roomId,userId,userArray);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
