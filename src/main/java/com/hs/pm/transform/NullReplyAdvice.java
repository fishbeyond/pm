package com.hs.pm.transform;

import org.springframework.integration.Message;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-5
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class NullReplyAdvice extends AbstractRequestHandlerAdvice {

    @Override
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
        LinkedMultiValueMap payload = (LinkedMultiValueMap)message.getPayload();
        Object result = callback.execute();
        if (result == null) {
            result = new ArrayList<String>();
        }
        return result;
    }

}
