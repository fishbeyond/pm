package com.hs.whocan.component.account.security.exception;

import com.hs.whocan.framework.exception.FriendlyMessageException;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public class AuthCodeErrorException extends FriendlyMessageException {
    @Override
    public String getErrorCode() {
        return "1000";
    }

    @Override
    public String getFriendlyMessage() {
        return "验证码错误,请重新获取";
    }
}
