package com.hs.pm;

import com.hs.pm.project.CreateProject;
import com.hs.pm.project.UpdateProject;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * user: fish
 * Date: 14-3-14
 * Time: 上午9:27
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class Message {
    @XmlElementWrapper(name = "Actions")
    @XmlElement(name = "CreateProject")
    private List<CreateProject> createProjects;
    @XmlElement(name = "UpdateProject")
    private List<UpdateProject> updateProjects;

    public List<UpdateProject> getUpdateProjects() {
        return updateProjects;
    }

    public void setUpdateProjects(List<UpdateProject> updateProjects) {
        this.updateProjects = updateProjects;
    }

    public List<CreateProject> getCreateProjects() {
        return createProjects;
    }

    public void setCreateProjects(List<CreateProject> createProjects) {
        this.createProjects = createProjects;
    }
}
