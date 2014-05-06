package com.hs.whocan.exception;

import java.util.Map;

/**
 * Created by yinwenbo on 14-5-5.
 */
public abstract class TranslatedException extends RuntimeException {

    protected TranslatedException() {
    }

    protected TranslatedException(String message) {
        super(message);
    }

    public Map<String, String> getData(){
        return null;
    }
}
