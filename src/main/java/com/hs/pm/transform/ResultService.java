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
    public String handle(String result) {
        return result == null ? "null" : result;
    }

    public List handle(List result) {
        return (result == null) ? (new ArrayList<String>()) : result;
    }
}
