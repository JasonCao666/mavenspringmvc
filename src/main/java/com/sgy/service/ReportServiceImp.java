package com.sgy.service;

import com.sgy.dao.daoInterface.ReportDao;
import com.sgy.entity.Project;
import com.sgy.entity.Report;
import com.sgy.service.serviceInterFace.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImp implements ReportService{
    @Autowired
    private ReportDao reportDao;

    @Override
    public boolean addReport(Project project) {
        return reportDao.addReport(project);
    }

    @Override
    public List<Report> listReport(Integer proId) {
        return reportDao.listReport(proId);
    }

    @Override
    public Project getProjectById(String proId) {
        return reportDao.getProjectById(proId);
    }
}
