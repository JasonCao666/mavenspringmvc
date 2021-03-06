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
    <style>
        .input-content{
            width:100%;
            display:inline-block;
        }

        .input-explain{
            width:40%;
            float:left;
            margin-left: 10px;
            margin-right: 10px;
            word-wrap:break-word;
            word-break:break-all;
        }
        .input-detail{
            width:50%;
            float:left;

        }

    </style>
</head>
<body>
<div class="header-nav"></div>
<!--<div>${taskId}</div>-->
<div class="container">
    <div class="row" style="text-align: center;">
        <h3>Edit Task</h3>
    </div>
    <form role="form">
        <div class="form-group">
            <label class="form-label">Task name</label>
            <div class="input-content">
                <input type="text" class="input-detail" id="taskName" value="${taskName}">
                <div class="input-explain">Give this task a brief name so you can quickly distinguish between tasks later</div>
            </div>
        </div>
        <div class="form-group">
            <label class="form-label">Task description</label>
            <div class="input-content">
                <textarea class="input-detail" rows="3" name=textarea id="taskDescription" >${taskDescription}</textarea>
                <div class="input-explain">Specific instructions for the participant to read, telling that what goal they are trying to achieve</div>
            </div>
        </div>
        <div class="form-group">
            <label class="form-label">Anticipated maximum complete time (secs)</label>
            <div class="input-content">
                <textarea class="input-detail" rows="3" name=textarea id="taskTime" >${taskPlanTime}</textarea>
                <div class="input-explain">How much time you think this task should take</div>
            </div>
        </div>
        <div class="form-group" style="width: 50%;">
            <label class="form-label">Efficient setup steps</label>
            <div>The ordered steps that you think the participants could complete the task efficiently. Please enter single efficient step in each input box (e.g. input box1: Help input box2: My Library)</div>
            <div id="taskSteps">
                <c:forEach items="${taskEfficientSteps}" varStatus="i" var="step" >

                <input type="text" class="form-control" id="efficientStep${i.index}" value="${step}">

                <input type="button" class="btn btn-default" value="Remove" name="efficientStep${i.index}" onclick="delSelect(this,this.name);">
                </c:forEach>
            </div>

            <button type="button" class="btn btn-default" id="addEfficient" onclick="addSteps(this.previousSibling);">Add new step</button>
        </div>
        <div class="form-group">
            <label class="form-label">Successful end step</label>
            <div class="input-content">
            <input type="text" class="input-detail" id="endStep" value="${taskEndStep}">
                <div class="input-explain">The last step you want participants to click to successfully complete the task. (e.g. check)</div>
            </div>
        </div>
    </form>

    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="editReturn()">Return</button>
    <button type="button" class="btn btn-primary" id="addTaskButton" data-dismiss="modal" onclick="editTask()">Update Task</button>
</div>
<script>
    $(document).ready(function(){
        $(".header-nav").load("/project/showHeader");
    });
    var mysteps = new Array();

    var steps="${taskEfficientSteps}";
    steps=steps.substring(1,steps.length-1);
    var stepsArray=steps.split(",");
    console.log(stepsArray.length);

    for(var i=0;i<stepsArray.length;i++){
        mysteps.push("efficientStep"+i);
    }


    var number=mysteps.length;
    function addSteps(afterElement) {
        // 定义新增序号
        number++;
        // 创建div
        var div = document.createElement("div");
        var input=document.createElement("input");
        input.setAttribute("type","text");
        input.setAttribute("class","form-control");
        input.setAttribute("id","efficientStep"+number+"");
        input.setAttribute("style","display:block");

        var button=document.createElement("input");
        button.setAttribute("type","button");
        button.setAttribute("class","btn btn-default");
        button.setAttribute("style","display:block");
        button.setAttribute("name","efficientStep"+number+"");
        button.setAttribute("onclick","delSelect(this,this.name);");
        button.value="Remove";



        div.appendChild(input);
        div.appendChild(button);

        insertAfter(div, afterElement);
        mysteps.push("efficientStep"+number);
        return false;
    }
    function insertAfter(newElement, targetElement) {
        var parent = targetElement.parentNode;
        if (parent.lastChild == targetElement) {
            // 如果最后的节点是目标元素，则直接添加。因为默认是最后
            parent.appendChild(newElement);
        } else {
            parent.insertBefore(newElement, targetElement.nextSibling);
            //如果不是，则插入在目标元素的下一个兄弟节点 的前面。也就是目标元素的后面。
        }
    }

    function delSelect(current_obj,id) {
        str1="";
        if (number > 0) { // If there's more than one text box
            // Remove the last one added
            var current_button = current_obj;
            var input_text = document.getElementById(id);
            current_obj.parentNode.removeChild(input_text);
            current_obj.parentNode.removeChild(current_obj);
            var index = mysteps.indexOf(id);
            if (index > -1) {
                mysteps.splice(index, 1);
            }
        }
    }

    function editTask(){
        var task_Id="${taskId}";
        var task_name=document.getElementById("taskName").value;
        var task_description=document.getElementById("taskDescription").value;
        var task_time=document.getElementById("taskTime").value;
        var task_steps="";
        for(var i=0;i<mysteps.length;i++){
            if(i!=mysteps.length-1) {
                task_steps += document.getElementById(mysteps[i]).value + ",";
            }
            else {
                task_steps += document.getElementById(mysteps[i]).value;
            }
        }
        var task_endStep=document.getElementById("endStep").value;
        /*console.log(task_name);
        console.log(task_description);
        console.log(task_time);
        console.log(task_steps);
        console.log(task_endStep);*/
        $.ajax({
            url: "task/editTask",
            type: "POST",
            dataType: "json",
            data: {
                "taskId":task_Id,
                "taskName": task_name,
                "taskDescription": task_description,
                "taskPlanTime": task_time,
                "taskEfficientSteps": task_steps,
                "taskEndStep": task_endStep,
            },
            success:function(data){
                //alert("edit task success");
                window.location.href = "task/showTaskPage?proId="+"${proId}"+"&proName=${proName}";

            },
            error:function(){
                alert("error");
            }
        });
    }
    function editReturn(){
        window.location.href = "task/showTaskPage?proId="+"${proId}"+"&proName=${proName}";
    }
</script>
</body>
</html>
