package com.hs.whocan.domain.chat;

import com.hs.whocan.domain.chat.dao.Chat;
import com.hs.whocan.domain.chat.dao.ChatDao;
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
public class ChatRoomService {
    @Resource
    private ChatDao chatDao;
    public void createPublicChat(Chat chat){
        chatDao.createPublicChat(chat);
    }

    public List<Chat> findPublicChatByProjectId(String projectId){
        return chatDao.findPublicChatByProjectId(projectId);
    }
}
