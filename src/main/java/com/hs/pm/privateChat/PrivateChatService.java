package com.hs.pm.privateChat;

import com.hs.pm.privateChat.dao.PrivateChat;
import com.hs.pm.privateChat.dao.PrivateChatDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Resources_es;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午5:20
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PrivateChatService {
    @Resource
    private PrivateChatDao privateChatDao;
    public boolean createPrivateChat(PrivateChat privateChat){
        privateChat.setCreateTime(new Date());
        privateChatDao.createPrivateChat(privateChat);
        return true;
    }
    public List<PrivateChat> findPrivateChat(String fromUserId, String toUserId){
        return privateChatDao.findPrivateChat(fromUserId,toUserId);
    }
}
