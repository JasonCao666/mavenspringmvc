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

</body>
<script>
    $(document).ready(function(){
        $(".header-nav").load("/project/showHeader");
        prepare();
    });
    function prepare(){

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
    }
</script>
</html>
