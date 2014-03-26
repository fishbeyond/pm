package com.hs.whocan.domain.publicchat;

import com.hs.whocan.domain.publicchat.dao.PublicChat;
import com.hs.whocan.domain.publicchat.dao.PublicChatDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public void createPublicChat(PublicChat publicChat){
        publicChatDao.createPublicChat(publicChat);
    }

    public List<PublicChat> findPublicChatByProjectId(String projectId){
        return publicChatDao.findPublicChatByProjectId(projectId);
    }
}
