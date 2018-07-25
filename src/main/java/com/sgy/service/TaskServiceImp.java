package com.sgy.service;

import com.sgy.dao.daoInterface.TaskDao;
import com.sgy.entity.Task;
import com.sgy.service.serviceInterFace.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskDao taskDao;


    @Override
    public boolean addTask(String proId, Task task) {
        return taskDao.addTask(proId,task);
    }

    @Override
    public List<Task> listTask(Integer proId) {
        return taskDao.listTask(proId);
    }

    @Override
    public boolean editTask(String id, Task task) {
        return taskDao.editTask(id, task);
    }

    @Override
    public boolean delTask(String id) {
        return taskDao.deleteTask(id);
    }
}
