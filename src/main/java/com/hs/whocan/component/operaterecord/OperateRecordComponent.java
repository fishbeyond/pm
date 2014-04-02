package com.hs.whocan.component.operaterecord;

import com.hs.whocan.component.operaterecord.dao.OperateRecord;
import com.hs.whocan.component.operaterecord.dao.OperateRecordDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * info: fish
 * Date: 14-3-12
 * Time: 上午9:18
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OperateRecordComponent {
    @Resource
    private OperateRecordDao operateRecordDao;

    public void createOperateRecord(String operateContent, String projectId) {
        OperateRecord operateRecord = new OperateRecord(operateContent, projectId);
        operateRecordDao.createOperateRecord(operateRecord);
    }
}
