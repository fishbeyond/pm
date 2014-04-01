package com.hs.whocan.component.push.exception;

import com.hs.whocan.framework.exception.FriendlyMessageException;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-4-1
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class PushFailException extends FriendlyMessageException {
    @Override
    public String getFriendlyMessage() {
        return "推送失败";
    }
}
