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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
        Query query = session.createQuery(hql);
        List<Task> rolelist=null;
        rolelist = query.list();

        return rolelist;
    }

    @Override
    public boolean editTask(String id,Task task) {
        System.out.println(task.getName());
        Session session = sessionFactory.getCurrentSession();

        String hql = "update Task t set t.name=?, t.description =? where t.id =?";
        Query query = session.createQuery(hql);
        query.setString(0, task.getName());
        query.setString(1, task.getDescription());
        query.setInteger(2, Integer.parseInt(id));

        return (query.executeUpdate()>0);
    }

    @Override
    public boolean deleteTask(String id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "delete Task t where t.id = ?";
        Query query = session.createQuery(hql);

        query.setString(0, id);
        return (query.executeUpdate() > 0);
    }
}
