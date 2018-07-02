package com.sgy.dao;


import com.sgy.dao.daoInterface.TaskDao;
import com.sgy.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImp implements TaskDao {


    @Override
    public Task load(Integer id) {
        return null;
    }

    @Override
    public Task get(Integer id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public void persist(Task entity) {

    }

    @Override
    public Integer save(Task entity) {
        return null;
    }

    @Override
    public void saveOrUpdate(Task entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void flush() {

    }
}
