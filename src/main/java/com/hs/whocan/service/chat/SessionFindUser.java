package com.hs.whocan.service.chat;

import com.hs.whocan.component.session.SessionComponent;
import com.hs.whocan.service.WhoCanExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class SessionFindUser extends WhoCanExecutor {

    protected String roomId;
    @Resource
    protected SessionComponent sessionComponent;

    public List<String> execute(){
        return sessionComponent.findUserIdInRoom(roomId);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
