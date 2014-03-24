package com.hs.pm.api;

import com.hs.pm.server.privatechat.PrivateChatService;
import com.hs.pm.server.privatechat.dao.PrivateChat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午1:42
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PrivateChatAction {
    @Resource
    private PrivateChatService privateChatService;

    public boolean createPrivateChat(PrivateChat privateChat){
        privateChatService.createPrivateChat(privateChat);
        return true;
    }
    public List<PrivateChat> findPrivateChat(String fromUserId, String toUserId){
        return privateChatService.findPrivateChat(fromUserId,toUserId);
    }
}
