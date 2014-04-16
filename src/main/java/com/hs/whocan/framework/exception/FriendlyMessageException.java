package com.hs.whocan.framework.exception;

/**
 * Created by yinwenbo on 13-12-25.
 */
public class FriendlyMessageException extends RuntimeException {

    private Object data;
    private String msg;

    public FriendlyMessageException() {
    }

    public FriendlyMessageException(Object data) {
        this.data = data;
    }

    public FriendlyMessageException(Throwable throwable) {
        super(throwable);
    }

    public FriendlyMessageException(Throwable cause, Object data) {
        super(cause);
        this.data = data;
    }

    public FriendlyMessageException(Throwable cause, String msg, Object data) {
        super(cause);
        this.msg = msg;
        this.data = data;
    }

    public String getErrorCode() {
        return this.getClass().getSimpleName();
    }

    public String getFriendlyMessage() {
//        return this.getClass().getSimpleName();
        return this.msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
