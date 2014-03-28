package com.hs.whocan.component.project.xml;

import com.hs.whocan.component.project.dao.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-14
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "CreateProject")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateProject {
    @XmlElement(name = "Projects")
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
