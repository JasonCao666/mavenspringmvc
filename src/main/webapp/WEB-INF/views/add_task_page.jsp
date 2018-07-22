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
</head>
<body>
<div>${proId}</div>
<div class="container">
<form role="form">
    <div class="form-group">
        <label class="form-label">Task name</label>
        <input type="text" class="form-control" id="proName">
    </div>
    <div class="form-group">
        <label class="form-label">Task description</label>
        <textarea class="form-control" rows="3" name=textarea id="proDescription"></textarea>
    </div>
    <div class="form-group">
        <label class="form-label">Website URL</label>
        <textarea class="form-control" rows="3" name=textarea id="proWebsite"></textarea>
    </div>
</form>

<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
<button type="button" class="btn btn-primary" id="addProButton" data-dismiss="modal">Save Task</button>
</div>
</body>
</html>
