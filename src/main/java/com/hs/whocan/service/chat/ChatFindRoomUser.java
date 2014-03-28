package com.hs.whocan.service.chat;

import com.hs.whocan.component.chat.ChatRoomComponent;
import com.hs.whocan.service.chat.old.ChatRoomInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ChatFindRoomUser extends ChatExecutor{

    public List<ChatRoomInfo> execute() {
        return chatRoomComponent.findChatRoomInfo(userId);
    }
}
