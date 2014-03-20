package com.hs.pm.api.exception;

import com.hs.pm.server.transform.FriendlyMessageException;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public class TokenDisableException extends FriendlyMessageException {
    @Override
    public String getFriendlyMessage() {
        return "用户不存在";
    }
}
