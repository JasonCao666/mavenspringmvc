<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title></title>
    <script src="/js/jquery-3.3.1.js"></script>

</head>
<body>
<div class="main-grids" id="mainContents">
    <h4>主页面</h4>
</div>
<script>

    $('#mainContents').empty();
    $.ajax({

        type: "GET",
        url: "project/showPage",
        success: function(data) {
            $('#mainContents').append(data);
        }
    });

</script>
</body>
</html>
