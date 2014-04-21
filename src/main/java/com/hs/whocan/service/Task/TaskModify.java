package com.hs.whocan.service.Task;

import com.hs.whocan.service.WhoCanVerifyLoginService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by fish on 14-4-21.
 */
@Service
@Scope("prototype")
public class TaskModify extends WhoCanVerifyLoginService{


    @Override
    public Boolean execute() {
        return true;
    }

}
