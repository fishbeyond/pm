package com.hs.pm.projectDetail;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-11
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProjectDetailDao {
    @Resource
    private SessionFactory sessionFactory;
    public void save(ProjectDetail projectDetail){
        sessionFactory.getCurrentSession().save(projectDetail);
    }
}
