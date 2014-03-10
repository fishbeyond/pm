package com.hs.pm.linkman;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LinkmanTransformer {

    public List<Linkman> transform(LinkedMultiValueMap payload){
        System.out.println(payload);
        int n = payload.size()/4;
        for(int i=0;i<4;i++){

        }
        Map map = payload.toSingleValueMap();
        Linkman linkman = new Linkman();
        Class<? extends Linkman> aClass = linkman.getClass();
        return null;
    }
}
