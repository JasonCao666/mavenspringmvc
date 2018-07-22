<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title></title>
    <script src="/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-3.3.7.css">
    <script src="/js/bootstrap.min.js"></script>
    <link href="/css/app.css" rel="stylesheet">
    <link href="/css/app_icons.css" rel="stylesheet">

</head>
<body>
<div class="container" style="display: block;" id="mainContents">
    <input type="text" class="form-control" id="currentPage" style="display:none;">
    <h4>主页面</h4>

</div>
<script>
    $(document).ready(function(){

        prepare();


    });
    function prepare(){
        $('#mainContents').empty();
        $.ajax({

            type: "GET",
            url: "project/showPage",
            success: function(data) {
                $('#mainContents').append(data);
                $('#currentPage').value="project/showPage";
            }
        });
    }


</script>

</body>
</html>
