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
    public String addTask(Task task) {
        return taskDao.addTask(task);
    }

    @Override
    public List<Task> listTask() {
        return taskDao.listTask();
    }
}
