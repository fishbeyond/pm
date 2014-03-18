package com.hs.pm.privateChat.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-18
 * Time: 下午5:20
 * To change this template use File | Settings | File Templates.
 */
public interface PrivateChatDao {
    public void createPrivateChat(PrivateChat privateChat);

    public List<PrivateChat> findPrivateChat(String fromUserId, String toUserId);
}
