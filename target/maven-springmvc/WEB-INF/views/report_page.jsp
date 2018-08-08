<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-3.3.7.css">
    <script src="/js/bootstrap.min.js"></script>
    <link href="/css/app.css" rel="stylesheet">
    <link href="/css/app_icons.css" rel="stylesheet">
</head>
<body style="background-color: white;">
<div class="header-nav"></div>
<div class="container">
    <div class="row" style="text-align: center;">
        <h2>${proName} Report</h2>
    </div>
    <div class=row" id="reportContent">

    </div>
    <div class=row">

    </div>
</div>
</body>
<script>
    $(document).ready(function(){
        $(".header-nav").load("/project/showHeader");
        prepare();
    });
    function prepare(){

        <c:forEach items="${reportResults}" var="report">
        var div_report_content= document.createElement("div");
        div_report_content.setAttribute("class","report_content");
        var report_overview=document.createElement("h3");
        report_overview.setAttribute("style","font-weight:bold");
        report_overview.innerHTML="${report.taskName} Completion Overview";
        var report_task_decription=document.createElement("h4");
        report_task_decription.innerHTML="${report.taskName} description: ${report.taskDescription}";
        var report_overview_details=document.createElement("ul");
        var overview_participates_number=document.createElement("li");
        overview_participates_number.innerHTML="Total number of participants: "+${report.participateNumber};
        var overview_success_number=document.createElement("li");
        overview_success_number.innerHTML="Success: "+${report.successNumber};
        var overview_fail_number=document.createElement("li");
        overview_fail_number.innerHTML="Fail: "+(${report.participateNumber}-${report.successNumber});
        var overview_finish_in_time_number=document.createElement("li");
        overview_finish_in_time_number.innerHTML="Number of participants finish in time: "+${report.finishInTimeNumber};
        var overview_exceeding_time_number=document.createElement("li");
        overview_exceeding_time_number.innerHTML="Number of participants exceeding time: "+(${report.participateNumber}-${report.finishInTimeNumber});
        var overview_efficient_number=document.createElement("li");
        overview_efficient_number.innerHTML="Number of participants following the efficient steps: "+${report.efficientNumber};
        report_overview_details.appendChild(overview_participates_number);
        report_overview_details.appendChild(overview_success_number);
        report_overview_details.appendChild(overview_fail_number);
        report_overview_details.appendChild(overview_finish_in_time_number);
        report_overview_details.appendChild(overview_exceeding_time_number);
        report_overview_details.appendChild(overview_efficient_number);
        div_report_content.appendChild(report_overview);
        div_report_content.appendChild(report_task_decription);
        div_report_content.appendChild(report_overview_details);


            /*console.log(${report.taskId});
            console.log(${report.successNumber});
            console.log(${report.efficientNumber});
            console.log(${report.finishInTimeNumber});
            console.log(${report.participateNumber});*/

        var efficient_overview=document.createElement("h3");
        efficient_overview.innerHTML="Efficient Task";
        var efficient_values=document.createElement("h4");
        efficient_values.innerHTML="Pre-defined Efficient Steps: ${report.taskSteps}";
        div_report_content.appendChild(efficient_overview);
        div_report_content.appendChild(efficient_values);

        var efficient_string="${report.taskSteps}";
        var efficient_list=efficient_string.split(",");
        console.log(efficient_list);
        var efficient_task_details=document.createElement("ul");

        for(var i=0;i<efficient_list.length;i++){
            var efficient_single_step=document.createElement("h4");
            efficient_single_step.innerHTML="Efficient step "+(i+1)+": "+efficient_list[i];
            efficient_task_details.appendChild(efficient_single_step);

            console.log(i+": "+efficient_single_step);
            var str_array=[];
            var median_array=[];
            var participate_wrong_num=0;

            <c:forEach items="${report.incorrects}" var="incorrect">


                var array=${incorrect};
                //var incorrect_step_string="No one make mistakes";
                var flag=0;
                for(row in array){
                    if(row=="firstStep" && i==0){

                        str_array.push(array[row]);

                        participate_wrong_num++;

                        flag=1;
                        continue;
                    }
                    else if(row==efficient_list[i-1] && i!=0){
                        //incorrect_step_string="";
                        //incorrect_step_string="Next incorrect step: "+array[row]+".";
                        str_array.push(array[row]);
                        participate_wrong_num++;
                        flag=1;
                        continue;
                    }
                    if(flag==1){
                        median_array.push(Number(array[row]));
                    }

                    //incorrect_step_string+=" Steps to return to next efficient step: "+array[row];
                }
                /*if(flag==1){
                    var incorrect_step=document.createElement("li");
                    //incorrect_step.innerHTML=incorrect_step_string;
                    efficient_task_details.appendChild(incorrect_step);
                }*/



            </c:forEach>
            if(str_array.length==0) {
                continue;
            }
            var incorrect_step_description=document.createElement("label");
            incorrect_step_description.innerHTML="Number of participates going wrong from step "+(i+1)+":";
            efficient_task_details.appendChild(incorrect_step_description);
            var incorrect_step_number=document.createElement("li");
            incorrect_step_number.innerHTML=participate_wrong_num;
            efficient_task_details.appendChild(incorrect_step_number);

            var wrong_step_arrary=modeArray(str_array);
            var incorrect_step_details=document.createElement("label");
            incorrect_step_details.innerHTML="The most common wrong step taken from step"+(i+1)+":";
            efficient_task_details.appendChild(incorrect_step_details);
            for(var j=0;j<wrong_step_arrary.length;j++){
                var incorrect_step=document.createElement("li");
                incorrect_step.innerHTML=wrong_step_arrary[j];
                efficient_task_details.appendChild(incorrect_step);
            }
            var median_number_description=document.createElement("label");
            median_number_description.innerHTML="Median number of wrong steps until recoveries from step "+(i+1)+":";
            efficient_task_details.appendChild(median_number_description);
            var median_value;

            median_value=median(median_array);

            var median_number=document.createElement("li");
            median_number.innerHTML=median_value;
            efficient_task_details.appendChild(median_number);

            div_report_content.appendChild(efficient_task_details);



            //console.log(efficient_list[i]+modeString(str_array));
        }


        var hr=document.createElement("hr");
        div_report_content.appendChild(hr);
        document.getElementById("reportContent").appendChild(div_report_content);
        </c:forEach>




        /*for(var i=0;i<${reportResults.size()};i++){
            alert(${reportResults.get(i).taskId});
            alert(${reportResults.get(i).successNumber});
        }*/
    }
    /*function prepare(){

        $.ajax({
            url: "report/getReportResult",
            type: "POST",
            dataType: "json",
            data:{
                "proId":${proId},
            },
            success:function(data){

            }
        });
    }*/

    function modeArray(array)
    {
        if (array.length == 0)
            return null;
        var modeMap = {},
            maxCount = 1,
            modes = [];

        for(var i = 0; i < array.length; i++)
        {
            var el = array[i];

            if (modeMap[el] == null)
                modeMap[el] = 1;
            else
                modeMap[el]++;

            if (modeMap[el] > maxCount)
            {
                modes = [el];
                maxCount = modeMap[el];
            }
            else if (modeMap[el] == maxCount)
            {
                modes.push(el);
                maxCount = modeMap[el];
            }
        }
        return modes;
    }

    function median(values) {
        var newArray=[];
        for(var i=0;i<values.length;i++){
            if(values[i]!=-1){
                newArray.push(values[i]);
            }
        }

        newArray.sort( function(a,b) {return a - b;} );

        var half = Math.floor(newArray.length/2);

        if(newArray.length % 2)
            return newArray[half];
        else
            return (newArray[half-1] + newArray[half]) / 2.0;
    }
</script>
</html>
