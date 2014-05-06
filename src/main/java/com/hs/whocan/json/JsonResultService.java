package com.hs.whocan.json;

import com.hs.whocan.exception.ExceptionTranslatorService;
import com.hs.whocan.exception.TranslatedException;
import com.hs.whocan.service.ServiceInterface;
import com.hs.whocan.service.ValidatorService;
import com.hs.whocan.si.EmptyResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by yinwenbo on 14-4-30.
 */

@Service
public class JsonResultService {

    public static final String SUCCESS_CODE = "0000";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ValidatorService validatorService;

    @Resource
    private ExceptionTranslatorService exceptionTranslatorService;


    public JsonResult doService(ServiceInterface service) {
        try {
            validatorService.validate(service);
            return getSuccessResult(service.doService());
        } catch (TranslatedException e) {
            logger.error("{}", e);
            return createJsonResult(e);
        } catch (Exception e) {
            logger.error("{}", e);
            return createJsonResult(e);
        }
    }

    public JsonResult getSuccessResult(Object data) {
        return createJsonResult(SUCCESS_CODE, data);
    }

    public JsonResult getExceptionResult(Exception e) {
        Throwable cause = e;
        while (cause != null) {
            if (cause instanceof TranslatedException) {
                return createJsonResult((TranslatedException)cause);
            }
            cause = e.getCause();
        }
        return createJsonResult(e);
    }

    public JsonResult getServiceNotExistsResult(){
        final String code = "ServiceNotExists";
        String message = exceptionTranslatorService.trans(code, null);
        return new JsonResult(code, message, null);
    }

    private JsonResult createJsonResult(String code, Object data) {
        String message = exceptionTranslatorService.trans(code, null);
        if (data instanceof EmptyResultBean) {
            return new JsonResult(code, message, null);
        }
        return new JsonResult(code, message, data);
    }

    private JsonResult createJsonResult(TranslatedException te) {
        String code = te.getClass().getSimpleName();
        String message = exceptionTranslatorService.trans(code, te.getData());
        Map<String, String> data = te.getData();
        return new JsonResult(code, message, data);
    }

    private JsonResult createJsonResult(Exception e) {
        String code = e.getClass().getSimpleName();
        String message = exceptionTranslatorService.trans(code, null);
        return new JsonResult(code, message, null);
    }
}
