package com.hs.whocan.service;

import com.hs.whocan.domain.work.WorkService;
import com.hs.whocan.domain.work.dao.Work;
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
    private WorkService workService;

    public List<Work> findWorkByProjectId(String projectId) {
        return workService.findWorkByProjectId(projectId);
    }

    public void createWork(Work work){
        workService.createWork(work);
    }

    public void modifyWork(Work work){
        workService.modifyWork(work);
    }

    public void deleteWork(String workId){
        workService.deleteWork(workId);
    }
}
