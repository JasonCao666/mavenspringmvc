package com.sgy.dao.daoInterface;

import com.sgy.entity.Project;

import java.util.List;

public interface ExpertDao {

    public String addProject(Project project);

    public List<Project> listProject();

    public boolean editProject(String id, Project project);

    public boolean deleteProject(String id);
}
