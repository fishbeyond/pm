package com.hs.pm.api;

import com.hs.pm.dto.PublicChatForm;
import com.hs.pm.server.publicchat.PublicChatService;
import com.hs.pm.server.publicchat.dao.PublicChat;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午1:11
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PublicChatAction {
    @Resource
    private PublicChatService publicChatService;

    public void createPublicChat(PublicChatForm publicChatForm){
        PublicChat publicChat = transform2PublicChat(publicChatForm);
        publicChatService.createPublicChat(publicChat);
    }

    public List<PublicChatForm> findPublicChatByProjectId(String projectId){
        List<PublicChat> publicChats =  publicChatService.findPublicChatByProjectId(projectId);
        List<PublicChatForm> publicChatForms = new ArrayList<PublicChatForm>();
        for(PublicChat publicChat :publicChats){
            publicChatForms.add(transform2PublicChatForm(publicChat)) ;
        }
        return publicChatForms;
    }

    private PublicChat transform2PublicChat(PublicChatForm publicChatForm){
        PublicChat publicChat = new PublicChat();
        publicChat.setCreateTime(new Date());
        publicChat.setFromUserId(publicChatForm.getUserId());
        publicChat.setFromUserName(publicChatForm.getOperatorName());
        publicChat.setProjectId(publicChatForm.getProjectId());
        publicChat.setMessage(publicChatForm.getMessage());
        return publicChat;
    }

    private PublicChatForm transform2PublicChatForm(PublicChat publicChat){
        PublicChatForm publicChatForm = new PublicChatForm();
        publicChatForm.setMessage(publicChat.getMessage());
        publicChatForm.setCreateTime(publicChat.getCreateTime());
        publicChatForm.setProjectId(publicChat.getProjectId());
        publicChatForm.setUserId(publicChat.getFromUserId());
        publicChatForm.setOperatorName(publicChat.getFromUserName());
        return publicChatForm;
    }
}
