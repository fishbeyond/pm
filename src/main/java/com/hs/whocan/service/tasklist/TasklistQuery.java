package com.hs.whocan.service.tasklist;

import java.util.List;
import java.util.Map;

/**
 * Created by yinwenbo on 14-5-5.
 */
public interface TasklistQuery {

    public List<Object> findListByGroupId(FindTasklistByGroupId param);
}
