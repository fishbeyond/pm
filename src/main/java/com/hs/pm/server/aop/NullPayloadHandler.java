package com.hs.pm.server.aop;

import com.hs.pm.api.exception.PayloadNullException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class NullPayloadHandler {
    public Object handle(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        if(null==result){
            throw new PayloadNullException();
        }
        return result;
    }

}
