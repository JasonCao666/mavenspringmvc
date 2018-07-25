package com.sgy.service.serviceInterFace;

import com.sgy.entity.Task;

import java.util.List;

public interface TaskService {

    public boolean addTask(String proId, Task task);

    public List<Task> listTask(Integer proId);

    public boolean editTask(String id, Task task);

    public boolean delTask(String id);
}


