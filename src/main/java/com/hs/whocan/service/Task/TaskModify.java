package com.hs.whocan.service.Task;

import com.hs.whocan.component.account.user.dao.User;
import com.hs.whocan.service.NeedSignInService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope("prototype")
public class TaskModify extends NeedSignInService {


    @Override
    public Boolean execute(User user) {
        return true;
    }

}
