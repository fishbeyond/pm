package com.hs.whocan.server.work.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-17
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public interface WorkDao {
    public void createWork(Work work);
    public void modifyWork(Work work);
    public void deleteWork(String workId);
    public List<Work> findWorkById(String projectId);

}
