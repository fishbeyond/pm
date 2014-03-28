package com.hs.whocan.service.chat;

import com.hs.whocan.component.chat.ChatRoomComponent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class ChatFindUser extends ChatExecutor{

    public List<String> execute(){
        return chatRoomComponent.findUserIdInRoom(roomId);
    }
}
