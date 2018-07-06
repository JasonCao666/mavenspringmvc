package com.sgy.service.serviceInterFace;

import com.sgy.entity.Task;

import java.util.List;

public interface TaskService {

    public String addTask(Task task);

    public List<Task> listTask();

    public boolean editTask(String id, Task task);

    public boolean delTask(String id);
}


