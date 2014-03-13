package com.hs.pm.linkman;

import com.hs.pm.projectDetail.ProjectDetail;
import com.hs.pm.projectDetail.ProjectDetailService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fish
 * Date: 14-3-10
 * Time: 上午9:43
 * To change this template use File | Settings | File Templates.
 */
public class ContactDao {
    @Resource
    private SessionFactory sessionFactory;

    @Transactional
    public boolean uploadContact(LinkedMultiValueMap contactList) {
        int num = contactList.size() / 5;
        Session currentSession = sessionFactory.getCurrentSession();
        for (int i = 0; i < num; i++) {
            Contact contact = new Contact();
            String projectId = contactList.get("contactList[" + i + "]" + "[projectId]").get(0).toString();
            String userId = contactList.get("contactList[" + i + "]" + "[userId]").get(0).toString();
            String phoneNo = contactList.get("contactList[" + i + "]" + "[phoneNo]").get(0).toString();
            String mailAddress = contactList.get("contactList[" + i + "]" + "[mailAddress]").get(0).toString();
            String userName = contactList.get("contactList[" + i + "]" + "[userName]").get(0).toString();
            Integer iProjectId = Integer.valueOf(projectId);
            contact.setProjectId(iProjectId);
            contact.setUserId(userId);
            contact.setUserName(userName);
            contact.setPhoneNo(phoneNo);
            contact.setMailAddress(mailAddress);
            currentSession.save(contact);
            ProjectDetail projectDetail = new ProjectDetail();
            projectDetail.setProjectId(iProjectId);
            projectDetail.setDetail(userName + "加入项目");
            projectDetail.setOperateTime(new Date());
            currentSession.save(projectDetail);
        }
        currentSession.flush();
        currentSession.clear();
        return true;
    }
}
