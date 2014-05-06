package com.hs.whocan.si;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinwenbo on 14-1-9.
 */
public class ListTransformer {

    public List transformer(Object o){
        if (o instanceof List) {
            return (List)o;
        }
        if (o instanceof EmptyResultBean) {
            return new ArrayList(0);
        }
        List<Object> result = new ArrayList(1);
        result.add(o);
        return result;

    }
}
