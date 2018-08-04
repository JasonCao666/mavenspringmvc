package com.sgy.dao.daoInterface;


import com.sgy.entity.Project;
import com.sgy.entity.Report;


import java.util.List;

public interface ReportDao {

    public boolean addReport(Project project);

    public List<Report> listReport(Integer proId);

    public Project getProjectById(String proId);
}
