package com.hs.whocan.service;

import com.hs.whocan.domain.privatechat.PrivateChatService;
import com.hs.whocan.domain.privatechat.dao.PrivateChat;
import com.hs.whocan.domain.publicchat.PublicChatService;
import com.hs.whocan.domain.publicchat.dao.PublicChat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-26
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ChatService {

    @Resource
    private PublicChatService publicChatService;
    @Resource
    private PrivateChatService privateChatService;

    public boolean createPublicChat(PublicChatForm publicChatForm) {
        publicChatService.createPublicChat(publicChatForm.getPublicChat());
        return true;
    }

    public List<PublicChatForm> findPublicChatByGroupId(String projectId) {
        List<PublicChat> publicChats = publicChatService.findPublicChatByProjectId(projectId);
        List<PublicChatForm> publicChatForms = new ArrayList<PublicChatForm>();
        for (PublicChat publicChat : publicChats) {
            publicChatForms.add(new PublicChatForm(publicChat));
        }
        return publicChatForms;
    }

    public boolean createPrivateChat(PrivateChat privateChat) {
        privateChatService.createPrivateChat(privateChat);
        return true;
    }

    public List<PrivateChat> findPrivateChat(String fromUserId, String toUserId) {
        return privateChatService.findPrivateChat(fromUserId, toUserId);
    }
}
