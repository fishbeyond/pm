package com.hs.whocan.service.chat;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午6:19
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionDeleteUser extends WhoCanExecutor {

    protected String roomId;
    private String deleteUserId;
    @Resource
    protected SessionComponent sessionComponent;

    public boolean execute(){
        sessionComponent.deletePeopleFromChatRoom(roomId,deleteUserId);
        return true;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }
}
