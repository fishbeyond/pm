package com.hs.whocan.json;


import org.springframework.stereotype.Service;

/**
 * Created by yinwenbo on 14-4-30.
 */

public class ServiceNotExistsResult extends ExceptionResult {

    public ServiceNotExistsResult() {
        this.setCode("ServiceNotExists");
        this.setMsg("服务不存在");
    }
}
