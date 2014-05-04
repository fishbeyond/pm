package com.hs.whocan.json;

/**
 * Created by yinwenbo on 14-4-30.
 */
public class SuccessResult extends Result {

    public static final String SUCCESS_CODE = "0000";

    public SuccessResult(Object data) {
        this.setCode(SUCCESS_CODE);
        this.setMsg("");
        this.setData(data);
    }
}
