package com.hs.whocan.component.push;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-10
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public interface PushService {
    public void push(String deviceToken, String content);

    public void push(List<String> deviceTokens, String content);
}


