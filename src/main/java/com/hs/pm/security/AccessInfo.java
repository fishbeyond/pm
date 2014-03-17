package com.hs.pm.security;

import java.util.Date;

/**
 * Created by fish on 14-3-17.
 */
public class AccessInfo {
    private String accessId;
    private String phoneNo;
    private String mailAddress;
    private String password;
    private int authCode;
    private Date accessTime;
    private boolean isActive;
    private long aliveTime;
}
