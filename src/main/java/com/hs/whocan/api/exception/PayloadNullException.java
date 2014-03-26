package com.hs.whocan.api.exception;

import com.hs.whocan.server.transform.FriendlyMessageException;
import com.hs.whocan.server.transform.JsonResultService;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-21
 * Time: 上午9:33
 * To change this template use File | Settings | File Templates.
 */
public class PayloadNullException extends FriendlyMessageException {
    @Override
    public String getErrorCode() {
        return JsonResultService.DEFAULT_SUCCESS_CODE;
    }
}
