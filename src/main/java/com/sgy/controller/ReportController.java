package com.sgy.controller;


import com.sgy.entity.Project;
import com.sgy.entity.Report;
import com.sgy.service.serviceInterFace.ReportService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping("/addReport")
    public void addReport(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        System.out.print("ReportController");

        String reportJson = request.getParameter("reportJson");
        System.out.println(reportJson);
        JSONObject json = JSONObject.fromObject(reportJson);
        String proId=json.getString("proId");
        String reportContent=json.getString("reports");
        Project project=reportService.getProjectById(proId);
        System.out.println(proId);
        System.out.println(reportContent);
        JSONArray reportArray=JSONArray.fromObject(reportContent);
        for(int i=0;i<reportArray.size();i++){
            JSONObject jsonRow=reportArray.getJSONObject(i);
            Report report=new Report();
            report.setTaskId(jsonRow.getString("taskId"));
            report.setReportTime(jsonRow.getString("time"));
            report.setReportSteps(jsonRow.getString("steps"));
            report.setProject(project);
            project.getReports().add(report);
        }

        String result = "{\"result\":\"error\"}";
        if(reportService.addReport(project)){
            result = "{\"result\":\"success\"}";
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(result);
        out.close();
    }
}