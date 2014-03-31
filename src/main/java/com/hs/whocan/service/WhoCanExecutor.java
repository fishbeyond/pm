package com.hs.whocan.service;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-28
 * Time: 下午5:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class WhoCanExecutor {
    protected String userId;
    protected String operatorName;

    protected abstract Object execute();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
