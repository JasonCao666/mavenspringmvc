package com.sgy.dao;


import com.sgy.dao.daoInterface.TaskDao;
import com.sgy.entity.Project;
import com.sgy.entity.Task;
import com.sun.tools.internal.jxc.gen.config.Classes;
import net.sf.json.JSONArray;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class TaskDaoImp implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;


    @Override
    public boolean addTask(String proId, Task task) {
        boolean result=false;
        Session session = sessionFactory.getCurrentSession();
        Project project = (Project)session.get(Project.class, Integer.parseInt(proId));
        System.out.println(project.getId());
        task.setProject(project);
        Integer flag=(Integer)session.save(task);
        System.out.println("size"+project.getTasks().size());
        if(flag>0)
        {
            result=true;
        }
        return result;
    }

    @Override
    public List<Task> listTask(Integer proId) {

        Session session = sessionFactory.getCurrentSession();
        Project project = (Project)session.get(Project.class, proId);

        List<Task> taskList=new ArrayList<>(project.getTasks());

        return taskList;
    }

    @Override
    public boolean editTask(String id,Task task) {
        Session session = sessionFactory.getCurrentSession();
        Task task_current = (Task)session.get(Task.class, Integer.parseInt(id));
        task_current.setName(task.getName());
        task_current.setDescription(task.getDescription());
        task_current.setTask_plan_time(task.getTask_plan_time());
        task_current.setTask_efficient_step(task.getTask_efficient_step());
        task_current.setTask_end_step(task.getTask_end_step());

        try{
            session.update(task_current);
            session.flush();
            return true;
        }catch(Exception e){
            return false;
        }

        /*String hql = "update Task t set t.name=?, t.description =? where t.id =?";
        Query query = session.createQuery(hql);
        query.setString(0, task.getName());
        query.setString(1, task.getDescription());
        query.setInteger(2, Integer.parseInt(id));

        return (query.executeUpdate()>0);*/
    }

    @Override
    public boolean deleteTask(String id) {
        Session session = sessionFactory.getCurrentSession();
        Task task_current = (Task)session.get(Task.class, Integer.parseInt(id));
        try{
            session.delete(task_current);
            session.flush();
            return true;
        }catch(Exception e){
            return false;
        }
        /*String hql = "delete Task t where t.id = ?";
        Query query = session.createQuery(hql);

        query.setString(0, id);
        return (query.executeUpdate() > 0);*/
    }
}
