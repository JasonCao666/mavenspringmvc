package com.sgy.controller;


import com.sgy.entity.Project;
import com.sgy.entity.Report;
import com.sgy.entity.ReportResult;
import com.sgy.entity.Task;
import com.sgy.service.serviceInterFace.ReportService;
import com.sgy.util.SortList;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    @RequestMapping("/showReportPage")
    public String showTaskPage(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {

        String proId = request.getParameter("proId");
        String proName = request.getParameter("proName");
        System.out.println(proId+","+proName);
        model.addAttribute("proId", proId);
        model.addAttribute("proName", proName);
        Project project=reportService.getProjectById(proId);
        List<Report> reportList=new ArrayList<>(project.getReports());
        List<Task> taskList=new ArrayList<>(project.getTasks());

        List<ReportResult> reportResults=new ArrayList<>(taskList.size());

        Pattern pattern = Pattern.compile("^(ht|f)tps?");
        for(int j=0;j<reportList.size();j++){
            String[] stepsAndURL=reportList.get(j).getReportSteps().split(",");
            List<String> steps = new ArrayList<String>();
            for(int i=0;i<stepsAndURL.length;i++){
                Matcher matcher = pattern.matcher(stepsAndURL[i]);
                if(matcher.find()){

                    continue;
                }
                else{
                    steps.add(replaceBlank(stepsAndURL[i]));
                }
            }

            reportList.get(j).setSteps(steps);
        }

        System.out.println(reportList.size());
        for(int i=0;i<reportList.size();i++){
            for(int j=0;j<reportList.get(i).getSteps().size();j++){
                System.out.println(reportList.get(i).getSteps().get(j));
            }
            System.out.println("---------------");
        }




        for(int i=0;i<taskList.size();i++){
            int successNumber=0;
            int finishInTimeNumber=0;
            int participateNumber=0;
            int efficientNumber=0;
            //the length of this list is according to the length of efficient steps

            String incorrectSteps="";
            for(int j=0;j<reportList.size();j++){//针对一个report进行

                if(taskList.get(i).getId()==Integer.parseInt(reportList.get(j).getTaskId())){
                    participateNumber++;

                    List<String> reportSteps=reportList.get(j).getSteps();
                    //System.out.println("taskList:"+taskList.get(i).getTask_end_step().length()+"reportSteps:"+reportSteps.get(reportSteps.size()-1).length());
                    if(taskList.get(i).getTask_end_step().equals(reportSteps.get(reportSteps.size()-1))){//判断成功
                        successNumber++;
                        String[] efficientSteps=taskList.get(i).getTask_efficient_step().split(",");
                        int index=0;
                        int flag=0;
                        for(int k=0;k<efficientSteps.length;k++){//比对step

                            //System.out.println("efficientSteps[k]"+efficientSteps[k]);
                            int medianSteps=0;

                            for(int m=index;m<reportSteps.size();m++){

                                index++;
                                medianSteps++;

                                if(k==0 && flag==0 && !efficientSteps[k].equals(reportSteps.get(m))){//第一步就错
                                    incorrectSteps+="{\"firstStep\":\""+reportSteps.get(m)+"\",\"medianSteps\":";
                                    flag=1;
                                }
                                if(efficientSteps[k].equals(reportSteps.get(m))){
                                    if(flag==1){
                                        incorrectSteps+="\""+medianSteps+"\"},";
                                        flag=0;
                                    }
                                    //System.out.println("k: "+efficientSteps[k]+" m: "+reportSteps.get(m));
                                    //System.out.println("k+1: "+efficientSteps[k+1]+" m+1: "+reportSteps.get(m+1));
                                    //System.out.println("efficientSteps.get(k):"+efficientSteps[k]+" reportSteps.get(m):"+reportSteps.get(m));
                                    if(k!=efficientSteps.length-1){
                                        if(!efficientSteps[k+1].equals(reportSteps.get(m+1)))
                                        {
                                            System.out.println("if not equal: k: "+efficientSteps[k]+" m+1: "+reportSteps.get(m+1));
                                            incorrectSteps+="{\""+efficientSteps[k]+"\":\""+reportSteps.get(m+1)+"\",\"medianSteps\":";
                                            flag=1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if(incorrectSteps!="" && !incorrectSteps.substring(incorrectSteps.length()-1).equals(",")){
                            incorrectSteps+="\""+(-1)+"\"},";
                            flag=0;
                        }
                        if(Integer.parseInt(taskList.get(i).getTask_plan_time())>Integer.parseInt(reportList.get(j).getReportTime())){//判断超时
                            finishInTimeNumber++;
                        }
                        String[] str=new String[reportSteps.size()];
                        if(Arrays.equals(efficientSteps,reportSteps.toArray(str))){
                            efficientNumber++;
                        }
                    }
                    else{
                        String[] efficientSteps=taskList.get(i).getTask_efficient_step().split(",");
                        int index=0;
                        int flag=0;
                        for(int k=0;k<efficientSteps.length;k++){//比对step

                            //System.out.println("efficientSteps[k]"+efficientSteps[k]);
                            int medianSteps=0;

                            for(int m=index;m<reportSteps.size();m++){

                                index++;
                                medianSteps++;

                                if(k==0 && flag==0 && !efficientSteps[k].equals(reportSteps.get(m))){//第一步就错
                                    incorrectSteps+="{\"firstStep\":\""+reportSteps.get(m)+"\",\"medianSteps\":";
                                    flag=1;
                                }
                                if(efficientSteps[k].equals(reportSteps.get(m))){
                                    if(flag==1){
                                        incorrectSteps+="\""+medianSteps+"\"},";
                                        flag=0;
                                    }

                                    if(k!=efficientSteps.length-1){
                                        if(!efficientSteps[k+1].equals(reportSteps.get(m+1)))
                                        {
                                            System.out.println("if not equal: k: "+efficientSteps[k]+" m+1: "+reportSteps.get(m+1));
                                            incorrectSteps+="{\""+efficientSteps[k]+"\":\""+reportSteps.get(m+1)+"\",\"medianSteps\":";
                                            flag=1;
                                        }
                                    }
                                    break;
                                }
                                /*else{
                                    if(flag==1){
                                        incorrectSteps+="\""+(-1)+"\"},";
                                        flag=0;
                                    }
                                    break;
                                }*/
                            }
                            if(!incorrectSteps.substring(incorrectSteps.length()-1).equals(",")){
                                incorrectSteps+="\""+(-1)+"\"},";
                                flag=0;
                            }
                        }
                    }


                }
            }

            System.out.println("task: "+taskList.get(i).getId());
            System.out.println("description: "+taskList.get(i).getDescription());
            System.out.println("incorrectSteps: "+incorrectSteps.substring(0,incorrectSteps.length()-1));
            JSONArray incorrectArray=JSONArray.fromObject("["+incorrectSteps.substring(0,incorrectSteps.length()-1)+"]");
            System.out.println("successNumber: "+successNumber);
            System.out.println("finishInTimeNumber:"+ finishInTimeNumber);
            System.out.println("participateNumber:"+ participateNumber);
            System.out.println("efficientNumber: "+efficientNumber);
            ReportResult reportResult=new ReportResult();
            reportResult.setTaskId(taskList.get(i).getId());
            reportResult.setTaskName(taskList.get(i).getName());
            reportResult.setTaskDescription(taskList.get(i).getDescription());
            reportResult.setTaskSteps(taskList.get(i).getTask_efficient_step());
            reportResult.setParticipateNumber(participateNumber);
            reportResult.setSuccessNumber(successNumber);
            reportResult.setFinishInTimeNumber(finishInTimeNumber);
            reportResult.setIncorrectStepsAndNumber(incorrectSteps);
            reportResult.setEfficientNumber(efficientNumber);
            reportResult.setIncorrects(incorrectArray);
            for(int n=0;n<incorrectArray.size();n++){
                JSONObject incorrects = incorrectArray.getJSONObject(n);
                if(incorrects.get("firstStep")!=null){
                    System.out.println("firstStep"+incorrects.get("firstStep"));
                }
            }
            reportResults.add(reportResult);

        }

        SortList<ReportResult> sortList = new SortList<ReportResult>();
        sortList.Sort(reportResults, "getTaskId", "asc");
        model.addAttribute("reportResults",reportResults);
        for(int i=0;i<reportResults.size();i++){
            JSONArray testArray=reportResults.get(i).getIncorrects();
            for(int j=0;j<testArray.size();j++){
                System.out.println(testArray.getJSONObject(j).get("firstStep"));
            }
        }
        return "report_page";

    }

    /*@RequestMapping("/getReportResult")
    public void getReportResult(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String proId = request.getParameter("proId");
        Project project=reportService.getProjectById(proId);
        List<Report> reportList=new ArrayList<>(project.getReports());
        List<Task> taskList=new ArrayList<>(project.getTasks());

        List<ReportResult> reportResults=new ArrayList<>(taskList.size());

        Pattern pattern = Pattern.compile("^(ht|f)tps?");
        for(int j=0;j<reportList.size();j++){
            String[] stepsAndURL=reportList.get(j).getReportSteps().split(",");
            List<String> steps = new ArrayList<String>();
            for(int i=0;i<stepsAndURL.length;i++){
                Matcher matcher = pattern.matcher(stepsAndURL[i]);
                if(matcher.find()){

                    continue;
                }
                else{
                    steps.add(replaceBlank(stepsAndURL[i]));
                }
            }

            reportList.get(j).setSteps(steps);
        }

        System.out.println(reportList.size());
        for(int i=0;i<reportList.size();i++){
            for(int j=0;j<reportList.get(i).getSteps().size();j++){
                System.out.println(reportList.get(i).getSteps().get(j));
            }
            System.out.println("---------------");
        }




        for(int i=0;i<taskList.size();i++){
            int successNumber=0;
            int finishInTimeNumber=0;
            int participateNumber=0;
            int efficientNumber=0;
            //the length of this list is according to the length of efficient steps

            String incorrectSteps="";
            for(int j=0;j<reportList.size();j++){//针对一个report进行

                if(taskList.get(i).getId()==Integer.parseInt(reportList.get(j).getTaskId())){
                    participateNumber++;
                    List<String> reportSteps=reportList.get(j).getSteps();
                   //System.out.println("taskList:"+taskList.get(i).getTask_end_step().length()+"reportSteps:"+reportSteps.get(reportSteps.size()-1).length());
                    if(taskList.get(i).getTask_end_step().equals(reportSteps.get(reportSteps.size()-1))){//判断成功
                        successNumber++;
                        String[] efficientSteps=taskList.get(i).getTask_efficient_step().split(",");
                        int index=0;
                        int flag=0;
                        for(int k=0;k<efficientSteps.length;k++){//比对step

                            //System.out.println("efficientSteps[k]"+efficientSteps[k]);
                            int medianSteps=0;

                            for(int m=index;m<reportSteps.size();m++){

                                index++;
                                medianSteps++;

                                if(k==0 && flag==0 && !efficientSteps[k].equals(reportSteps.get(m))){//第一步就错
                                    incorrectSteps+="{\"firstStep\":\""+reportSteps.get(m)+"\",\"medianSteps\":";
                                    flag=1;
                                }
                                if(efficientSteps[k].equals(reportSteps.get(m))){
                                    if(flag==1){
                                        incorrectSteps+="\""+medianSteps+"\"},";
                                        flag=0;
                                    }
                                    //System.out.println("k: "+efficientSteps[k]+" m: "+reportSteps.get(m));
                                    //System.out.println("k+1: "+efficientSteps[k+1]+" m+1: "+reportSteps.get(m+1));
                                    //System.out.println("efficientSteps.get(k):"+efficientSteps[k]+" reportSteps.get(m):"+reportSteps.get(m));
                                    if(k!=efficientSteps.length-1){
                                        if(!efficientSteps[k+1].equals(reportSteps.get(m+1)))
                                        {
                                            System.out.println("if not equal: k: "+efficientSteps[k]+" m+1: "+reportSteps.get(m+1));
                                            incorrectSteps+="{\""+efficientSteps[k]+"\":\""+reportSteps.get(m+1)+"\",\"medianSteps\":";
                                            flag=1;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if(Integer.parseInt(taskList.get(i).getTask_plan_time())>Integer.parseInt(reportList.get(j).getReportTime())){//判断超时
                            finishInTimeNumber++;
                        }
                        String[] str=new String[reportSteps.size()];
                        System.out.println(Arrays.equals(efficientSteps,reportSteps.toArray(str)));
                        if(Arrays.equals(efficientSteps,reportSteps.toArray(str))){
                            efficientNumber++;
                        }
                    }
                }
            }

            System.out.println("task: "+taskList.get(i).getId());
            System.out.println("description: "+taskList.get(i).getDescription());
            System.out.println("incorrectSteps: "+incorrectSteps.substring(0,incorrectSteps.length()-1));
            JSONArray incorrectArray=JSONArray.fromObject("["+incorrectSteps.substring(0,incorrectSteps.length()-1)+"]");
            System.out.println("successNumber: "+successNumber);
            System.out.println("finishInTimeNumber:"+ finishInTimeNumber);
            System.out.println("participateNumber:"+ participateNumber);
            System.out.println("efficientNumber: "+efficientNumber);
            ReportResult reportResult=new ReportResult();
            reportResult.setTaskId(taskList.get(i).getId());
            reportResult.setParticipateNumber(participateNumber);
            reportResult.setSuccessNumber(successNumber);
            reportResult.setFinishInTimeNumber(finishInTimeNumber);
            reportResult.setIncorrectStepsAndNumber(incorrectSteps);
            reportResult.setEfficientNumber(efficientNumber);
            reportResult.setIncorrects(incorrectArray);
            for(int n=0;n<incorrectArray.size();n++){
                JSONObject incorrects = incorrectArray.getJSONObject(n);
                if(incorrects.get("firstStep")!=null){
                    System.out.println(incorrects.get("firstStep"));
                }

            }
            reportResults.add(reportResult);

        }
        model.addAttribute(reportResults);
    }*/

    public String replaceBlank(String str){
        Pattern pt=Pattern.compile("^\\s*|\\s*$");
        Matcher mt=pt.matcher(str);
        str=mt.replaceAll("");
        return str;
    }


}