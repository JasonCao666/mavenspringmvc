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


    $("#proEditButton").click(function () {
        editTaskRequest();


    });


    $("#addProButton").click(function () {
        addProRequest();

    });




});



/*function prepare(){

    $.ajax({
        type: "POST",
        url: "project/listProject",
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
}*/




function addProRequest() {
    var proName=document.getElementById("proName").value;
    var proDescription=document.getElementById("proDescription").value;
    var proWebsite=document.getElementById("proWebsite").value;
    proDescription= proDescription.replace(/\n|\r\n/g,"<br>");
    alert(proName);
    //var reg=new RegExp("<br>","g"); var newstr=remContent.replace(reg,"\n");
    $.ajax({
        url: "project/addProject",
        type: "POST",
        dataType: "json",
        data: {
            "proName": proName,
            "proDescription": proDescription,
            "proWebsite": proWebsite,
        },
        success:function(data){
            alert("add task success");
            location.reload();

        },
        error:function(){
            alert("error");
        }
    });
}

function edit(e){
    var edit_pro_id=e.name.split(",")[0];
    var edit_pro_name=e.name.split(",")[1];
    var edit_pro_description=e.name.split(",")[2];
    var edit_pro_website=e.name.split(",")[3];
    document.getElementById('proEditId').value=edit_pro_id;
    document.getElementById('proEditName').value=edit_pro_name;
    document.getElementById('proEditDescription').value=edit_pro_description;
    document.getElementById('proEditWebsite').value=edit_pro_website;

}

function editTaskRequest() {

    var edit_proId = document.getElementById("proEditId").value;
    var edit_proName = document.getElementById("proEditName").value;
    var edit_proDescription = document.getElementById("proEditDescription").value;
    var edit_proWebsite = document.getElementById("proEditWebsite").value;
    var url="project/editProject";

    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        data: {
            "proId": edit_proId,
            "proName": edit_proName,
            "proDescription": edit_proDescription,
            "proWebsite":edit_proWebsite,
        },
        success: function (data) {
            if ("success" == data.result) {
                alert("edit success");
                location.reload();
            }
            else {

                alert("edit fail");
            }
        }
    });
}

function delProRequest(e){
    var id=e.name;
    $.ajax({
        url: "project/delProject",
        type: "POST",
        dataType: "json",
        data: {
            "proId": id,
        },
        success: function (data) {
            if ("success" == data.result) {
                alert("del success");
                location.reload();
            }
            else {
                alert("del fail");
            }
        }
    });

}

function showTaskPage(id){
    window.location.href = "task/showTaskPage?proId="+id;
    /*$('#mainContents').empty();
    $.ajax({
        type: "GET",
        url: "task/showTaskPage",
        data: {
            "proId": id,
        },
        success: function(data) {

            //$('#mainContents').append(data);
        }
    });*/
}

