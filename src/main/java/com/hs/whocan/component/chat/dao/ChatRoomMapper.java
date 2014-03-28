package com.hs.whocan.component.chat.dao;

import com.hs.whocan.component.chat.dao.entity.ChatRoomEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-27
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="chat_room_mapper")
public class ChatRoomMapper {
    private String mapperId;
    private String roomId;
    private String userId;
    private ChatRoomEntity chatRoomEntity;

    public ChatRoomMapper() {
    }

    public ChatRoomMapper(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId;
    }

    @Column
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    @Column
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
