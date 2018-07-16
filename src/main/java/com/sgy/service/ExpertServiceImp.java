package com.sgy.service;

import com.sgy.dao.daoInterface.ExpertDao;
import com.sgy.dao.daoInterface.TaskDao;
import com.sgy.entity.Project;
import com.sgy.service.serviceInterFace.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImp implements ExpertService {

    @Autowired
    private ExpertDao expertDao;

    @Override
    public String addProject(Project project) {
        return expertDao.addProject(project);
    }

    @Override
    public List<Project> listProject() {
        return expertDao.listProject();
    }

    @Override
    public boolean editProject(String id, Project project) {
        return false;
    }

    @Override
    public boolean delProject(String id) {
        return false;
    }
}
