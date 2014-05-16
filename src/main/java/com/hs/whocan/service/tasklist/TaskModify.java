package com.hs.whocan.service.tasklist;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.service.VerifySignInService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskModify extends VerifySignInService {


    @Override
    @Transactional
    public Boolean execute(User user) {
        return true;
    }

}
