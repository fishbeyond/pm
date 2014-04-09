package com.hs.whocan.service.session;

import com.hs.whocan.component.account.security.SecurityComponent;
import com.hs.whocan.service.WhocanNeedLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: fish
 */
@Service
@Scope("prototype")
public class SessionSendAck extends WhocanNeedLoginService{
    private String updateTime;
    @Resource
    private SecurityComponent securityComponent;

    @Override
    @Transactional
    public Boolean execute() {
        Long updateTimestamp = Long.valueOf(updateTime);
        securityComponent.modifyTimestamp(userId,new Date(updateTimestamp));
        return true;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
