package com.hs.pm.work;

import com.hs.pm.transform.ResultService;
import com.hs.pm.work.dao.Work;
import com.hs.pm.work.dao.WorkDao;
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
public class WorkService {
    @Resource
    private WorkDao workDao;
    @Resource
    private ResultService resultService;

    public List<Work> findWorkByProjectId(String projectId) {
        List<Work> works = workDao.findWorkById(projectId);
        return resultService.handle(works);
    }

    public boolean createWork(Work work){
        workDao.createWork(work);
        return true;
    }

    public boolean modifyWork(Work work){
        workDao.modifyWork(work);
        return true;
    }

    public boolean deleteWork(String workId){
        workDao.deleteWork(workId);
        return true;
    }
}
