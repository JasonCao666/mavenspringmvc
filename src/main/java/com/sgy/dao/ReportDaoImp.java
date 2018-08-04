package com.sgy.dao;


import com.sgy.dao.daoInterface.ReportDao;
import com.sgy.entity.Project;
import com.sgy.entity.Report;
import com.sgy.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class ReportDaoImp implements ReportDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    public boolean addReport(Project project) {
        boolean result=false;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.update(project);
            session.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<Report> listReport(Integer proId) {
        Session session = sessionFactory.getCurrentSession();
        Project project = (Project)session.get(Project.class, proId);

        List<Report> reportList=new ArrayList<>(project.getReports());

        return reportList;
    }

    @Override
    public Project getProjectById(String proId) {
        Session session = sessionFactory.getCurrentSession();
        Project project_current = (Project)session.get(Project.class, Integer.parseInt(proId));
        return project_current;
    }
}
