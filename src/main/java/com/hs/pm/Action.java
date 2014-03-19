package com.hs.pm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-14
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "Action")
@XmlAccessorType(XmlAccessType.FIELD)
public class Action {

    public static void  main(String[] args){
        long t = 1395195335000l;
        Date date = new Date(t);
        System.out.println(date);
    }
    @XmlElement(name = "ActionName")
    private String actionName;
    @XmlElement(name = "UserId")
    private String userId;
    @XmlElement(name = "UserName")
    private String userName;
    @XmlElement(name = "ProjectName")
    private String projectName;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
