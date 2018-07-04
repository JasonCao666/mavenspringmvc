package com.sgy.dao.daoInterface;


import com.sgy.entity.Task;

import java.util.List;

public interface TaskDao{

    public String addTask(Task task);

    public List<Task> listTask();
}
