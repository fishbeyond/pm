package com.hs.whocan.service.chat;

import com.hs.whocan.component.chat.ChatRoomComponent;
import com.hs.whocan.component.chat.dao.Chat;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午7:01
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class FindChat extends WhoCanExecutor {
    private String roomId;
    @Resource
    private ChatRoomComponent chatRoomComponent;

    public List<Chat> execute() {
        return chatRoomComponent.findChatByRoomId(roomId);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
