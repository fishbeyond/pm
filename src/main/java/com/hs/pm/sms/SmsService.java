package com.hs.pm.sms;

import org.springframework.integration.Message;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SmsService extends AbstractRequestHandlerAdvice {
    @Override
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
        LinkedMultiValueMap payload = (LinkedMultiValueMap)message.getPayload();
        Object result = callback.execute();
        String phoneNo = payload.get("phoneNo").get(0).toString();
        String content = payload.get("smsContent").get(0).toString();
        MtService mtService = new  MtService();
        return mtService.send(phoneNo, content);
    }
}
