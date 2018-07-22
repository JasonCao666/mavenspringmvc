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
    <style id="taskTag-list">
        .taskTag{
            float:left;
            width: 61px;
            height: 57px;
            left: 191px;
            z-index: 50;
            background-color: #39ac86;
            border-radius: 7px;
            font-size: 14px;
            padding: 0px;
            border-width: 2px;
            border-style: solid;
            border-color: white;
            text-align: center;
            line-height: 20px;
            font-weight: bold;
            font-style: normal;
            opacity: 1;
            transition: none;
            overflow:hidden;
            vertical-align:top;
        }

        .task_name{
           margin-left:10px;
            width:70%;
            overflow:hidden;
        }
        .task_description{
            font-size: 5pt;
            width: 70%;
            height:30px;
            margin-top: 5px;
            margin-left:10px;
            word-break:keep-all;
            word-wrap: break-word;
            white-space:nowrap;
            overflow:hidden;

            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            text-overflow:ellipsis;
        }
        .task_opts{
            display:inline-block;
            float:right;
            margin-top:26px;
            margin-right:20px;
        }
        .task_content{

            display:inline-block;
            clear:both;
            margin-top: 20px;
            width:80%;
            background-color: white;
            vertical-align:top;

        }
        .task_detail{
            width: 70%;
            height:15%;
            display: inline-block;
        }
        .task_li{
            list-style-type: none;
        }
        .task_list_body{
            background-color:#dcdde0;
        }

    </style>
</head>
<body class="task_list_body">
<div>${proId}</div>


<ul>
    <a class="new-button" href="task/showAddTaskPage?proId=${proId}" rel="route" title="New">
        <div class="horizontal-line"></div>
        <div class="vertilcal-line"></div>
    </a>
    <li class="task_li">
    <div class="task_content">
    <div class="taskTag">
        <div class="text" >
            <p style="color:white;">1</p>
            <p style="color:white;">Task</p>
        </div>
        <div class="region gesture "></div>
    </div>
    <div class="task_detail">
        <div class="task_name">PPPPP</div>
        <div class="task_description">12345</div>
    </div>
    <div class="task_opts">
        <button type="button" class="btn btn-default" aria-label="Left Align" onclick="editTask(this)"
                data-toggle="modal" data-target="#projectEditModal"
                name="">
            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
        </button>

        <button type="button" class="btn btn-default" aria-label="Left Align" onclick="delTaskRequest(this)"
                name="">
            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
        </button>
    </div>
    </div>
    </li>
    <li class="task_li">
    <div class="task_content" >
        <div class="taskTag">
            <div class="text" >
                <p style="color:white;">1</p>
                <p style="color:white;">Task</p>
            </div>
            <div class="region gesture "></div>
        </div>
        <div class="task_detail">
            <div class="task_name">PPPPP</div>
            <div class="task_description">ladjldsaljaksdjladjlakjdlajdlajdadjlajdlakas<br/>
                ladjldsaljaksdjladjlakjdlajdlajdadjlajdlakas <br/>
                ladjldsaljaksdjladjlakjdlajdlajdadjlajdlakas
                ladjldsaljaksdjladjlakjdlajdlajdadjlajdlakas</div>
        </div>
        <div class="task_opts">
            <button type="button" class="btn btn-default" aria-label="Left Align" onclick="editTask(this)"
                    data-toggle="modal" data-target="#projectEditModal"
                    name="">
                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
            </button>

            <button type="button" class="btn btn-default" aria-label="Left Align" onclick="delTaskRequest(this)"
                    name="">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
            </button>
        </div>
    </div>
    </li>
</ul>
</body>
<script>
    /*window.onbeforeunload = onbeforeunload_handler();

    function onbeforeunload_handler(){
        $('#mainContents').empty();
        $.ajax({

            type: "GET",
            url: "task/showTaskPage",
            data: {
                "proId": '1',
            },
            success: function(data) {

                $('#mainContents').append(data);
                return false;
            }
        });

    }*/

</script>
</html>
