package com.hs.whocan.service.chat;

import com.hs.whocan.component.chat.ChatRoomComponent;
import com.hs.whocan.component.chat.dao.Chat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SendChat {
    @Resource
    private ChatRoomComponent chatRoomComponent;
    private Chat chat;
    public boolean execute() {
        chatRoomComponent.sendChat(chat);
        return true;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
