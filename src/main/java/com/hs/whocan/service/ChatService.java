package com.hs.whocan.service;

import com.hs.whocan.domain.chat.ChatRoomService;
import com.hs.whocan.domain.chat.dao.Chat;
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
    private ChatRoomService chatRoomService;



    public boolean createChat(ChatForm chatForm) {
        chatRoomService.createPublicChat(chatForm.getChat());
        return true;
    }

    public List<ChatForm> findChatByRoomId(String projectId) {
        List<Chat> chats = chatRoomService.findPublicChatByProjectId(projectId);
        List<ChatForm> chatForms = new ArrayList<ChatForm>();
        for (Chat chat : chats) {
            chatForms.add(new ChatForm(chat));
        }
        return chatForms;
    }

    public void findChatRoomByUserId(){}

    public void addPeopleToChatRoom(){}
}
