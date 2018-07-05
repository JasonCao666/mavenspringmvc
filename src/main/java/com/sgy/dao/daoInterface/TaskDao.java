package com.sgy.dao.daoInterface;


import com.sgy.entity.Task;

import java.util.List;

public interface TaskDao{

    public String addTask(Task task);

    public List<Task> listTask();

    public boolean editTask(String id, Task task);

    public boolean deleteTask(String id);
}
