package com.sgy.controller;


import com.sgy.entity.Project;
import com.sgy.service.serviceInterFace.ExpertService;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/project")
public class expertController {
    private static final Logger logger = LoggerFactory.getLogger(expertController.class);

    @Autowired
    private ExpertService expertService;

    @RequestMapping("/showPage")
    public String showProPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "test";

    }

    @RequestMapping("/addProject")
    public void saveProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Project project=new Project();

        response.setCharacterEncoding("UTF-8");

        String proName = request.getParameter("proName");
        String proDescription = request.getParameter("proDescription");
        String proWebsite=request.getParameter("proWebsite");
        project.setName(proName);
        project.setDescription(proDescription);
        project.setWebsiteURL(proWebsite);
        System.out.println(proName+" : "+proDescription);

        String jsonStr = expertService.addProject(project);
        JSONObject jsonObj = new JSONObject(jsonStr);
        PrintWriter out = response.getWriter();
        out.print(jsonObj);
        out.close();

    }

    @RequestMapping(value ="/listProject", method = RequestMethod.POST)
    public void listTask(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("controller");
        Project pro=new Project();
        List<Project> list_project = expertService.listProject();
        System.out.println(list_project);
        String jsonArray= JSONArray.fromObject(list_project).toString();
        System.out.println(jsonArray);
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.close();

    }

    @RequestMapping(value = "/editProject", method = RequestMethod.POST)
    public void editProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "{\"result\":\"error\"}";
        Project project=new Project();

        response.setCharacterEncoding("UTF-8");
        String proId=request.getParameter("proId");
        String proName = request.getParameter("proName");
        String proDescription = request.getParameter("proDescription");
        String proWebsite=request.getParameter("proWebsite");

        project.setName(proName);
        project.setDescription(proDescription);
        project.setWebsiteURL(proWebsite);

        System.out.println("Id: "+proId+"Name: "+proName+"Des: "+proDescription);

        if(expertService.editProject(proId,project))
        {
            result = "{\"result\":\"success\"}";
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(result);
        out.close();
    }


    @RequestMapping(value = "delProject", method = RequestMethod.POST)
    public void delTask(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "{\"result\":\"error\"}";


        response.setCharacterEncoding("UTF-8");
        String projectId=request.getParameter("proId");

        if(expertService.delProject(projectId))
        {
            result = "{\"result\":\"success\"}";
        }
        response.setContentType("application/json");


        PrintWriter out = response.getWriter();
        out.write(result);
        out.close();

    }
}
