package com.hs.whocan.json;

import com.hs.whocan.service.ServiceInterface;
import org.springframework.stereotype.Service;

/**
 * Created by yinwenbo on 14-4-30.
 */

@Service
public class JsonResultService {

    public Result doService(ServiceInterface service) {
        try {
            return new SuccessResult(service.execute());
        } catch (Exception e) {
            return new ExceptionResult(e);
        }
    }
}
