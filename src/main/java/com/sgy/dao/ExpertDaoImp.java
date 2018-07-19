package com.sgy.dao;

import com.sgy.dao.daoInterface.ExpertDao;
import com.sgy.entity.Project;
import com.sgy.entity.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ExpertDaoImp implements ExpertDao{

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String addProject(Project project) {
        boolean result=false;
        Session session = sessionFactory.getCurrentSession();
        session.save(project);
        return "{status:success}";
    }

    @Override
    public List<Project> listProject() {

        String hql="from Project";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        List<Project> prolist=null;
        prolist = query.list();
        return prolist;
    }

    @Override
    public boolean editProject(String id, Project project) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "update Project p set p.name=?, p.description =?, p.websiteURL=? where p.id =?";
        Query query = session.createQuery(hql);
        query.setString(0, project.getName());
        query.setString(1, project.getDescription());
        query.setString(2, project.getWebsiteURL());
        query.setInteger(3, Integer.parseInt(id));

        return (query.executeUpdate()>0);
    }

    @Override
    public boolean deleteProject(String id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete Project p where p.id = ?";
        Query query = session.createQuery(hql);

        query.setString(0, id);
        return (query.executeUpdate() > 0);
    }
}
