package com.hs.whocan.component.sms;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public void sendAuthCode(String phoneNo, int authCode) {
        new Thread(new MtService(phoneNo, authCode)).start();
    }
}
