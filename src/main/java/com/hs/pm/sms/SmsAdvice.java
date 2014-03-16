package com.hs.pm.sms;

import org.springframework.integration.Message;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-10
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SmsAdvice extends AbstractRequestHandlerAdvice {
    @Override
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
        LinkedMultiValueMap payload = (LinkedMultiValueMap) message.getPayload();
        Object result = callback.execute();
        String phoneNo = payload.get("phoneNo").get(0).toString();
        String content = payload.get("smsContent").get(0).toString();
        SmsService smsService = new SmsService();
        smsService.send(phoneNo,content);
        return result;
    }
}
