package com.hs.whocan.service.security;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.component.account.security.access.dao.Access;
import com.hs.whocan.component.account.user.UserComponent;
import com.hs.whocan.component.account.user.info.dao.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-31
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SecurityFilter {
    @Resource
    private SecurityComponent securityComponent;
    @Resource
    private UserComponent userComponent;

    @Transactional
    public List<String> findUserIdByToken(String token) {
        Access access = securityComponent.findAccessInfoByToken(token);
        List<String> list = new ArrayList<String>();
        list.add(access.getAccessId());
        return list;
    }
    @Transactional
    public List<String> findOperatorByToken(String userId) {
        User user = userComponent.findUserNameInfoById(userId);
        List<String> list = new ArrayList<String>();
        list.add(user.getUserName());
        return list;
    }
}
