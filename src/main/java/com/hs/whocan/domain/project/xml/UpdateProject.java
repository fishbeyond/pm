package com.hs.whocan.domain.project.xml;

import com.hs.whocan.domain.project.dao.Project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-14
 * Time: 下午2:31
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "UpdateProject")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateProject {
    @XmlElement(name = "Projects")
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
