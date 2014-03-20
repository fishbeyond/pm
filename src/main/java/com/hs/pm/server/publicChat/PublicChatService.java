package com.hs.pm.server.publicchat;

import com.hs.pm.server.publicchat.dao.PublicChat;
import com.hs.pm.server.publicchat.dao.PublicChatDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PublicChatService {
    @Resource
    private PublicChatDao publicChatDao;
    public boolean createPublicChat(PublicChat publicChat){
        publicChatDao.createPublicChat(publicChat);
        return true;
    }

    public boolean findPublicChatByProjectId(String projectId){
        publicChatDao.findPublicChatByProjectId(projectId);
        return true;
    }
}
