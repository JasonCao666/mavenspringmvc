package com.sgy.service.serviceInterFace;

import com.sgy.entity.Project;
import com.sgy.entity.Report;

import java.util.List;

public interface ReportService {

    public boolean addReport(Project project);

    public List<Report> listReport(Integer proId);

    public Project getProjectById(String proId);
}
