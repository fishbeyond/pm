package com.hs.whocan.component.account.security.exception;

import com.hs.whocan.exception.FriendlyMessageException;

/**
 * User: fish
 */
public class AuthCodeDisableException extends FriendlyMessageException {
    @Override
    public String getFriendlyMessage() {
        return "验证码失效";
    }
}
