package com.sgy.dao.daoInterface;


import com.sgy.entity.Task;

import java.util.List;

public interface TaskDao{

    public boolean addTask(String proId, Task task);

    public List<Task> listTask(Integer proId);

    public boolean editTask(String id, Task task);

    public boolean deleteTask(String id);
}
