<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>

<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <style id="taskTag-list">
        .taskTag{
            float:left;
            width: 61px;
            height: 57px;
            left: 191px;
            z-index: 50;
            background-color: rgb(77, 189, 235);
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
        }
    </style>
</head>
<body>
<div>${proId}</div>
<div data-cid="" data-link_cid="" class="taskTag"  id="taskTag">
    <div class="text" style="padding: 0px;"><p style="color:white;">1</p></div>
    <div class="region gesture "></div>
</div>
<div style="float:left;">
    <div>PPPPP</div>
    <div>12345</div>
</div>
</body>
</html>
