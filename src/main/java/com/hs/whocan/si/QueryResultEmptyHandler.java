package com.hs.whocan.si;

import org.springframework.integration.Message;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.integration.support.MessageBuilder;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * User: yinwenbo
 * Date: 13-9-16
 * Time: 15:36
 */
public class QueryResultEmptyHandler extends AbstractRequestHandlerAdvice {

    private Object emptyResult;

    public Object getEmptyResult() {
        return emptyResult;
    }

    public void setEmptyResult(Object emptyResult) {
        this.emptyResult = emptyResult;
    }

    @Override
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
        Object result = callback.execute();
        if (result == null) {
            return MessageBuilder.withPayload(emptyResult).copyHeaders(message.getHeaders()).build();
        }
        return result;
    }
}
