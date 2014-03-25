package com.hs.pm.server.account.user.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<Linkman> findLinkmanByUserId(String userId);
}
