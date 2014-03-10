package com.hs.pm.linkman;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 上午9:43
 * To change this template use File | Settings | File Templates.
 */
public class LinkmanDao {
    @Resource
    private SessionFactory sessionFactory;

    public void uploadLinkMan(LinkedMultiValueMap linkmanList) {
        List<Linkman> transform = transform(linkmanList);
    }
    public List<Linkman> transform(LinkedMultiValueMap linkmanList){
        int num = linkmanList.size()/4;
        for(int i=0;i<num;i++){
            Linkman linkman = new Linkman();
            List list = linkmanList.get("linkmanList[" + i + "]" + "ownerId");
        }
        return null;
    }
}
