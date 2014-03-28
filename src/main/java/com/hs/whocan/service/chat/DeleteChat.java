package com.hs.whocan.service.chat;

import com.hs.whocan.component.chat.ChatRoomComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午7:03
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class DeleteChat {
    private String chatId;
    @Resource
    private ChatRoomComponent chatRoomComponent;
    public boolean execute(){
        chatRoomComponent.deleteChat(chatId);
        return true;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
