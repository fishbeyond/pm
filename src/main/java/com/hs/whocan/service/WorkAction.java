package com.hs.whocan.service;

import com.hs.whocan.component.work.WorkComponent;
import com.hs.whocan.component.work.dao.Work;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-20
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WorkAction {
    @Resource
    private WorkComponent workComponent;

    public List<Work> findWorkByProjectId(String projectId) {
        return workComponent.findWorkByProjectId(projectId);
    }

    public void createWork(Work work){
        workComponent.createWork(work);
    }

    public void modifyWork(Work work){
        workComponent.modifyWork(work);
    }

    public void deleteWork(String workId){
        workComponent.deleteWork(workId);
    }
}
