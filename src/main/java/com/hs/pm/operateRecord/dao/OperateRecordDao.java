package com.hs.pm.operateRecord.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OperateRecordDao {
    @Resource
    private SessionFactory sessionFactory;

    public void createOperateRecord(OperateRecord operateRecord) {
        sessionFactory.getCurrentSession().save(operateRecord);
    }

    public void deleteOperateRecord(String operateId) {
        final String hql = "delete from OperateRecord where operateId = :operateId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("operateId", operateId);
        query.executeUpdate();
    }
}
