package com.hs.whocan.component.account.user.exception;

import com.hs.whocan.framework.exception.FriendlyMessageException;

/**
 * User: fish
 */
public class FriendAlreadyExistException extends FriendlyMessageException{
    @Override
    public String getFriendlyMessage() {
        return "已添加该好友";
    }
}
