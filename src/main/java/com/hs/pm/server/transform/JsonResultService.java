package com.hs.pm.server.transform;

import org.springframework.stereotype.Service;

@Service
public class JsonResultService {

    public static final String DEFAULT_SUCCESS_CODE = "0000";
    public static final String DEFAULT_EXCEPTION_CODE = "2000";

    private String successCode = DEFAULT_SUCCESS_CODE;
    private String successMsg = "";
    private String exceptionCode = DEFAULT_EXCEPTION_CODE;
    private String exceptionMsg = "";


    public JsonResult createSuccessResult(Object data) {
        return new JsonResult(successCode, successMsg, data);
    }

    public JsonResult createExceptionResult(Exception e) {
        FriendlyMessageException fme = getFriendlyMessageException(e);
        if (fme == null) {
            return new JsonResult(exceptionCode, exceptionMsg);
        }
        return new JsonResult(fme.getErrorCode(), fme.getFriendlyMessage());
    }

    public JsonResult createExceptionResult(String code, String msg) {
        return new JsonResult(code, msg);
    }

    public JsonResult createExceptionResult(String msg) {
        return new JsonResult(exceptionCode, msg);
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    private FriendlyMessageException getFriendlyMessageException(Throwable e) {
        if (e == null) {
            return null;
        }
        if (e instanceof FriendlyMessageException) {
            return (FriendlyMessageException) e;
        }
        return getFriendlyMessageException(e.getCause());
    }
}
