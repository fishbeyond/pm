package com.hs.whocan.component.account.security.exception;

import com.hs.whocan.exception.FriendlyMessageException;

/**
 * User: fish
 */
public class TokenErrorException extends FriendlyMessageException{
    @Override
    public String getFriendlyMessage() {
        return "用户不存在";
    }
}
