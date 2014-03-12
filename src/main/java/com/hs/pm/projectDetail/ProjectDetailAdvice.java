package com.hs.pm.projectDetail;

import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-11
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class ProjectDetailAdvice extends AbstractRequestHandlerAdvice {
    @Resource
    private ProjectDetailService projectDetailService;

    @Override
    @Transactional
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
        MessageHeaders headers = message.getHeaders();
        LinkedMultiValueMap payload = (LinkedMultiValueMap) message.getPayload();
        Object result = callback.execute();
        String detail = headers.get("detail").toString();
        payload.set("detail",detail);
        projectDetailService.save(message);
        return result == null ? null : result;
    }
}

