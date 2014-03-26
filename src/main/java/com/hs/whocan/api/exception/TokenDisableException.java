package com.hs.whocan.api.exception;

import com.hs.whocan.server.transform.FriendlyMessageException;

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
        return "请重新登陆";
    }
}
