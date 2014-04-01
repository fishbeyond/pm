package com.hs.whocan.component.account.user.linkman.dao;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-25
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@Service
public interface LinkmanDao {

    public void createLinkman(String userId,String[] phones);

    public List<LinkMan> findLinkmanByUserId(String userId);
}
