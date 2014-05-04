package com.hs.whocan.json;

/**
 * Created by yinwenbo on 14-4-30.
 */
public class ExceptionResult extends Result {

    public ExceptionResult() {
    }

    public ExceptionResult(Exception exception) {
        this.setCode(exception.getClass().getSimpleName());
        this.setMsg(exception.getMessage());
    }
}
