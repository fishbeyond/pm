package com.hs.whocan.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * User: fish
 */
@Service
@Scope("prototype")
public class ReceiveError{
    private String errorMsg;

    public Boolean execute(){
        throw new RuntimeException(errorMsg);
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
