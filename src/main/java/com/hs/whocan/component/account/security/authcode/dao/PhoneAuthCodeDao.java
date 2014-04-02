package com.hs.whocan.component.account.security.authcode.dao;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public interface PhoneAuthCodeDao {

    public PhoneAuthCode findPhoneAuthCode(String phoneNo);

    public void createPhoneAuthCode(PhoneAuthCode phoneAuthCode);

    public PhoneAuthCode findPhoneAuthCode(String phoneNo, int authCode);

    public void modifyPhoneAuthCodeByPhoneNo(PhoneAuthCode phoneAuthCode);
}
