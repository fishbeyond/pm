package com.hs.pm.linkman;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Transactional
    public boolean uploadLinkMan(LinkedMultiValueMap linkmanList) {
        int num = linkmanList.size() / 4;
        Session currentSession = sessionFactory.getCurrentSession();
        for (int i = 0; i < num; i++) {
            Linkman linkman = new Linkman();
            String sOwnerId = linkmanList.get("linkmanList[" + i + "]" + "[ownerId]").get(0).toString();
            String phoneNo = linkmanList.get("linkmanList[" + i + "]" + "[phoneNo]").get(0).toString();
            String mailAddress = linkmanList.get("linkmanList[" + i + "]" + "[mailAddress]").get(0).toString();
            String linkmanName = linkmanList.get("linkmanList[" + i + "]" + "[linkmanName]").get(0).toString();
            linkman.setOwnerId(Integer.valueOf(sOwnerId));
            linkman.setLinkmanName(linkmanName);
            linkman.setPhoneNo(phoneNo);
            linkman.setMailAddress(mailAddress);
            currentSession.save(linkman);
        }
        currentSession.flush();
        currentSession.clear();
        return true;
    }
}
