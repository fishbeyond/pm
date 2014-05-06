package com.hs.whocan.service.tasklist;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yinwenbo on 14-5-5.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FindTasklistByGroupId extends VerifySignInService {

    private String groupId;

    @Resource
    private TasklistQuery tasklistQuery;

    @Override
    public List<Object> execute(User user) {
        return tasklistQuery.findListByGroupId(this);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
