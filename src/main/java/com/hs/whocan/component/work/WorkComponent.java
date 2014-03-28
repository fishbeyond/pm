package com.hs.whocan.component.work;

import com.hs.whocan.component.work.dao.Work;
import com.hs.whocan.component.work.dao.WorkDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class WorkComponent {
    @Resource
    private WorkDao workDao;

    public List<Work> findWorkByProjectId(String projectId) {
        return workDao.findWorkById(projectId);
    }

    public void createWork(Work work){
        workDao.createWork(work);
    }

    public void modifyWork(Work work){
        workDao.modifyWork(work);
    }

    public void deleteWork(String workId){
        workDao.deleteWork(workId);
    }
}
