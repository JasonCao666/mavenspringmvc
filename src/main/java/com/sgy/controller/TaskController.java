package com.sgy.controller;
import com.sgy.entity.Task;
import com.sgy.service.serviceInterFace.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/task")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(expertController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "savePerson", method = RequestMethod.GET)
    @ResponseBody
    public String saveTask(){
        Task task=new Task();
        task.setName("GY");
        task.setDescription("Xi huan wo");
        taskService.addTask(task);
        return "success!";
    }
}
