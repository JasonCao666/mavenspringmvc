package com.sgy.service.serviceInterFace;


import com.sgy.entity.Project;
import com.sgy.entity.Task;

import java.util.List;

public interface ExpertService {

    public String addProject(Project project);

    public List<Project> listProject();

    public boolean editProject(String id, Project project);

    public boolean delProject(String id);
}
