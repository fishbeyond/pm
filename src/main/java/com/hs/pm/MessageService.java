package com.hs.pm;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-14
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MessageService {
    public Object receive(Object obj) {
        System.out.println(obj);
        return obj;
    }
}
