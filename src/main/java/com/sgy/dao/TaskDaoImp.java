package com.sgy.dao;


import com.sgy.dao.daoInterface.TaskDao;
import com.sgy.entity.Task;
import net.sf.json.JSONArray;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import org.hibernate.Transaction;

import java.util.List;

@Repository

public class TaskDaoImp implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;


    @Override
    public String addTask(Task task) {
        boolean result=false;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        return "{status:success}";
    }

    @Override
    public List<Task> listTask() {

        String hql="from Task";
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        List<Task> rolelist=null;
        rolelist = query.list();

        return rolelist;
    }
}
