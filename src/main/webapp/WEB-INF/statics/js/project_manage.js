var taskList=new Array();
var ps_list = $("#u_tem").html();
var selected_single_task;


$(document).ready(function(){


    //prepare();


    var createButton;

    $('#myTabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    });


    $("#addTaskButton").click(function () {
        alert(1);
        addTaskRequest();
        prepare();
    });

    $("#editTaskButton").click(function () {
        editTaskRequest();
        prepare();
    });

    $("#addProButton").click(function () {
        addProRequest();
    });



});



function prepare(){

    $.ajax({
        type: "POST",
        url: "listTask",
        dataType: 'json',
        success: function(data) {
            $("#temtr").nextAll().remove();

            var json = eval(data);

            for (var i = 0; i < json.length; i++) {
                //var item=json[i]["list"];
                var realrow = $("#temtr").clone();
                //给每一行赋值
                realrow.find("#task_name").text(json[i]['name']);
                realrow.find("#task_description").html(json[i]['description']);
                realrow.find("#operation").html("<button type=\"button\" class=\"btn btn-primary\" id=\"edit\" " +
                    "name=\""+ json[i]['id']+","+ json[i]['name']+","+json[i]['description']+
                    "\"onclick=\"edit(this)\" data-toggle=\"modal\" data-target=\"#editModal\">edit</button>" +
                    "<button type=\"button\" class=\"btn btn-primary\" id=\"del\" " +
                    " name=\""+ json[i]['id'] +"\" onclick=\"delTaskRequest(this)\">delete</button>");
                //将新行添加到表格中
                realrow.appendTo("#tem");
            }
        }
    });
}




function addProRequest() {
    var proName=document.getElementById("proName").value;
    var proDescription=document.getElementById("proDescription").value;
    proDescription= proDescription.replace(/\n|\r\n/g,"<br>");
    alert(proName);
    //var reg=new RegExp("<br>","g"); var newstr=remContent.replace(reg,"\n");

    $.ajax({
        url: "expert/addProject",
        type: "POST",
        dataType: "json",
        data: {
            "proName": proName,
            "proDescription": proDescription,
        },
        success:function(data){
            alert("add task success");
            prepare();
        },
        error:function(){
            alert("error");
        }
    });
}

function edit(e){
    var edit_task_id=e.name.split(",")[0];
    var edit_task_name=e.name.split(",")[1];
    var edit_task_description=e.name.split(",")[2];
    document.getElementById('edit_taskId').value=edit_task_id;
    document.getElementById('edit_taskName').value=edit_task_name;
    document.getElementById('edit_taskDescription').value=edit_task_description;

}

function editTaskRequest() {

    var edit_taskId = document.getElementById("edit_taskId").value;
    var edit_taskName = document.getElementById("edit_taskName").value;
    var edit_description = document.getElementById("edit_taskDescription").value;
    $.ajax({
        url: "editTask",
        type: "POST",
        dataType: "json",
        data: {
            "taskId": edit_taskId,
            "taskName": edit_taskName,
            "taskDescription": edit_description,
        },
        success: function (data) {
            if ("success" == data.result) {

                alert("edit success");
                prepare();

            }
            else {

                alert("edit fail");
            }
        }
    });
}

function delTaskRequest(e){
    var id=e.name;
    $.ajax({
        url: "delTask",
        type: "POST",
        dataType: "json",
        data: {
            "taskId": id,
        },
        success: function (data) {
            if ("success" == data.result) {
                alert("edit success");
                prepare();
            }
            else {
                alert("edit fail");
            }
        }
    });

}

function addProRequest() {
    var proName=document.getElementById("proName").value;
    var proDescription=document.getElementById("proDescription").value;
    proDescription= proDescription.replace(/\n|\r\n/g,"<br>");
    //var reg=new RegExp("<br>","g"); var newstr=remContent.replace(reg,"\n");

    $.ajax({
        url: "addProject",
        type: "POST",
        dataType: "json",
        data: {
            "proName": proName,
            "proDescription": proDescription,
        },
        success:function(data){
            alert("add project success");
        },
        error:function(){
            alert("error");
        }
    });
}
