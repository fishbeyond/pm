package com.hs.whocan.server.sms;

import org.springframework.stereotype.Service;

/**
 * 发送短信
 */
@Service
public class MtService implements Runnable {
    //输入软件序列号和密码
    private static final String sn = "SDK-BBX-010-14082";
    private static final String pwd = "226755";
    private static final Client client = new Client(sn, pwd);
    private String phoneNo;
    private String content;

    public MtService() {
    }

    public MtService(String phoneNo, int authCode) {
        this.phoneNo = phoneNo;
        this.content = "您的验证码是：" + authCode + "[互看]";
    }

    @Override
    public void run() {
        //我们的Demo最后是拼成xml了，所以要按照xml的语法来转义
        if (content.indexOf("&") >= 0) {
            content = content.replace("&", "&amp;");
        }

        if (content.indexOf("<") >= 0) {

            content = content.replace("<", "&lt;");

        }
        if (content.indexOf(">") >= 0) {
            content = content.replace(">", "&gt;");
        }
        //短信发送
        String result_mt = client.mt(phoneNo, content, "", "", "");
        if (result_mt.startsWith("-") || result_mt.equals(""))//以负号判断是否发送成功
        {
            System.out.print("发送失败！返回值为：" + result_mt + "。请查看webservice返回值对照表");
        }
        //输出返回标识，为小于19位的正数，String类型的，记录您发送的批次
        else {
            System.out.print("发送成功，返回值为：" + result_mt);
        }
    }
}
