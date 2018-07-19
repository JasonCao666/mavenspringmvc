package com.sgy.controller;
import com.sgy.entity.Task;
import com.sgy.service.serviceInterFace.TaskService;

import net.sf.json.JSONArray;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
@RequestMapping("/task")
public class TaskController {

    //private static final Logger logger = LoggerFactory.getLogger(expertController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping("/showTaskPage")
        public String showTaskPage(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {

            String proId = request.getParameter("proId");

            model.addAttribute("proId", proId);
            System.out.println("proId"+proId);
            return "task_list";

    }




    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    @ResponseBody
    public void saveTask(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Task task=new Task();


        response.setCharacterEncoding("UTF-8");

        String taskName = request.getParameter("taskName");
        String taskDescription = request.getParameter("taskDescription");
        task.setName(taskName);
        task.setDescription(taskDescription);

        System.out.println(taskName+" : "+taskDescription);

        String jsonStr = taskService.addTask(task);
        JSONObject jsonObj = new JSONObject(jsonStr);
        PrintWriter out = response.getWriter();
        out.print(jsonObj);
        out.close();

    }


    @RequestMapping("listTask")
    public void listTask(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("controller");
        Task task=new Task();
        List<Task> list_task = taskService.listTask();
        System.out.println(list_task);
        String jsonArray= JSONArray.fromObject(list_task).toString();
        System.out.println(jsonArray);
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.close();

    }

    @RequestMapping(value = "editTask", method = RequestMethod.POST)
    @ResponseBody
    public void editTask(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "{\"result\":\"error\"}";
        Task task=new Task();

        response.setCharacterEncoding("UTF-8");
        String taskId=request.getParameter("taskId");
        String taskName = request.getParameter("taskName");
        String taskDescription = request.getParameter("taskDescription");

        task.setName(taskName);
        task.setDescription(taskDescription);

        System.out.println("Id: "+taskId+"Name: "+taskName+"Des: "+taskDescription);

        if(taskService.editTask(taskId,task))
        {
            result = "{\"result\":\"success\"}";
        }
        response.setContentType("application/json");


        PrintWriter out = response.getWriter();
        out.write(result);
        out.close();

    }

    @RequestMapping(value = "delTask", method = RequestMethod.POST)
    @ResponseBody
    public void delTask(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "{\"result\":\"error\"}";


        response.setCharacterEncoding("UTF-8");
        String taskId=request.getParameter("taskId");

        if(taskService.delTask(taskId))
        {
            result = "{\"result\":\"success\"}";
        }
        response.setContentType("application/json");


        PrintWriter out = response.getWriter();
        out.write(result);
        out.close();

    }


}
