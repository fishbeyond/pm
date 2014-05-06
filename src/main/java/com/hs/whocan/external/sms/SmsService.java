package com.hs.whocan.external.sms;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */
public interface SmsService {
    public void sendAuthCode(String phoneNo, int authCode);
}
