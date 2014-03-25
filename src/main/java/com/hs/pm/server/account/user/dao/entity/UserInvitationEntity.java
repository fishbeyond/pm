package com.hs.pm.server.account.user.dao.entity;

import com.hs.pm.server.account.user.dao.UserInvitation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-24
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user_invitation")
public class UserInvitationEntity {
    private UserInvitation userInvitation;

    public UserInvitationEntity() {
        this.userInvitation = new UserInvitation();
    }

    public UserInvitationEntity(UserInvitation userInvitation) {
        this.userInvitation = userInvitation;
    }

    @Transient
    public UserInvitation getUserInvitation(){
        return userInvitation;
    }
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getInvitationId() {
        return userInvitation.getInvitationId();
    }

    public void setFriendId(String friendId) {
        userInvitation.setFriendId(friendId);
    }

    public void setUserId(String userId) {
        userInvitation.setUserId(userId);
    }

    @Column
    public String getFriendId() {
        return userInvitation.getFriendId();
    }
    @Column
    public String getUserId() {
        return userInvitation.getUserId();
    }

    public void setInvitationId(String invitationId) {
        userInvitation.setInvitationId(invitationId);
    }
}
