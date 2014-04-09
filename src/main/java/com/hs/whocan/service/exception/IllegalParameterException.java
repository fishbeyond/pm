package com.hs.whocan.service.exception;

import com.hs.whocan.framework.exception.FriendlyMessageException;

/**
 * User: fish
 */
public class IllegalParameterException extends FriendlyMessageException {
    public IllegalParameterException(Object obj) {
        super(obj);
    }

    @Override
    public String getFriendlyMessage() {
        return "参数错误";
    }
}
