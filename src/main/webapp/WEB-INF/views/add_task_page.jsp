<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<body>
<div class="header-nav"></div>
<div>${proId}</div>
<div class="container">
<form role="form">
    <div class="form-group">
        <label class="form-label">Task name</label>
        <input type="text" class="form-control" id="taskName">
    </div>
    <div class="form-group">
        <label class="form-label">Task description</label>
        <textarea class="form-control" rows="3" name=textarea id="taskDescription"></textarea>
    </div>
    <div class="form-group">
        <label class="form-label">Anticipate completed time (secs)</label>
        <textarea class="form-control" rows="3" name=textarea id="taskTime"></textarea>
    </div>
    <div class="form-group">
        <label class="form-label">Efficient setup steps</label>
        <div id="taskSteps">
            <input type="text" class="form-control" id="efficientStep1">
            <input type="button" class="btn btn-default" value="Remove" name="efficientStep1" onclick="delSelect(this,this.name);">
        </div>

        <button type="button" class="btn btn-default" id="addEfficient" onclick="addSteps(this.previousSibling);">Add new step</button>
    </div>
    <div class="form-group">
        <label class="form-label">Successful end step</label>
        <input type="text" class="form-control" id="endStep">
    </div>
</form>

<button type="button" class="btn btn-default" data-dismiss="modal" onclick="addReturn()">Return</button>
<button type="button" class="btn btn-primary" id="addTaskButton" data-dismiss="modal" onclick="addTask()">Save Task</button>
</div>
<script>
    $(document).ready(function(){
        $(".header-nav").load("/project/showHeader");
    });
var number=1;
var mysteps = new Array();
mysteps.push("efficientStep1");
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
    mysteps.push("efficientStep"+number+"");
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

        /*for(var i=0;i<mysteps.length;i++){
            str1+=mysteps[i];
        }

        alert(str1);*/
    }
}

function addTask(){

    var task_name=document.getElementById("taskName").value;
    var task_description=document.getElementById("taskDescription").value;
    var task_time=document.getElementById("taskTime").value;
    var task_steps="";
    var task_pro=${proId};
    for(var i=0;i<mysteps.length;i++){
        if(i!=mysteps.length-1) {
            task_steps += document.getElementById(mysteps[i]).value + ",";
        }
        else {
            task_steps += document.getElementById(mysteps[i]).value
        }
    }
    var task_endStep=document.getElementById("endStep").value;
    /*console.log(task_name);
    console.log(task_description);
    console.log(task_time);
    console.log(task_steps);
    console.log(task_endStep);*/
    $.ajax({
        url: "task/addTask",
        type: "POST",
        dataType: "json",
        data: {
            "taskName": task_name,
            "taskDescription": task_description,
            "taskPlanTime": task_time,
            "taskEfficientSteps": task_steps,
            "taskEndStep": task_endStep,
            "taskPro": task_pro,
        },
        success:function(data){
            alert("add task success");
            window.location.href = "task/showTaskPage?proId="+${proId};

        },
        error:function(){
            alert("error");
        }
    });

}
function addReturn(){
    window.location.href = "task/showTaskPage?proId="+${proId};

}
</script>
</body>
</html>
