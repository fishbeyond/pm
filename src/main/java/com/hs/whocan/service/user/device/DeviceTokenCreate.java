package com.hs.whocan.service.user.device;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-4-1
 * Time: 下午1:21
 * To change this template use File | Settings | File Templates.
 */
@Service
@Scope("prototype")
public class DeviceTokenCreate {
    private String userId;
    private String deviceToken;
}
