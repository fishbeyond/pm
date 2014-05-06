package com.hs.whocan.component.session.exception;

import com.hs.whocan.exception.FriendlyMessageException;

/**
 * User: fish
 */
public class SessionNotExistException extends FriendlyMessageException {
    @Override
    public String getFriendlyMessage() {
        return "指定session不存在";
    }
}
