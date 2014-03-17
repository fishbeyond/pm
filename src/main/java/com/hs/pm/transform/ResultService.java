package com.hs.pm.transform;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 下午3:13
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ResultService {
    public String handler(String str) {
        return str == null ? "null" : str;
    }

    public List handler(List list) {
        return (list == null) ? (new ArrayList<String>()) : list;
    }
}
