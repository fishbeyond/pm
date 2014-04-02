package com.hs.whocan.component.account.user.friend.dao.entity;

import com.hs.whocan.component.account.user.friend.dao.UserMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user_mapper")
public class UserMapperEntity {
    private UserMapper userMapper;

    public UserMapperEntity() {
        this.userMapper = new UserMapper();
    }

    public UserMapperEntity(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Transient
    public UserMapper getUserMapper(){
        return this.userMapper;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getMapperId() {
        return userMapper.getMapperId();
    }

    public void setUserId(String userId) {
        userMapper.setUserId(userId);
    }

    @Column
    public String getFriendId() {
        return userMapper.getFriendId();
    }

    @Column
    public String getUserId() {
        return userMapper.getUserId();
    }

    public void setMapperId(String mapperId) {
        userMapper.setMapperId(mapperId);
    }

    public void setFriendId(String friendId) {
        userMapper.setFriendId(friendId);
    }

    public void setAlias(String alias) {
        userMapper.setAlias(alias);
    }

    public String getAlias() {
        return userMapper.getAlias();
    }
}
