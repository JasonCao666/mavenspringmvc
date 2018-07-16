<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-3.3.7.css">
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/project_manage.js"></script>
    <link href="/css/app.css" rel="stylesheet">
    <link href="/css/app_icons.css" rel="stylesheet">
    <style id="list_icon_size_page-list">
        .page-list .grid li {
            visibility: visible;
            width: 147px;
            height: 161px;
            margin: 0 15.4px 15.4px 0;
        }</style>
</head>

<body>
<div class="container" style="display: block;">
    <div class="navbar-form" style="margin-top: 20px;">
        <a class="new-button" href="javascript:void(0);" rel="route" title="New" data-toggle="modal" data-target="#projectModal">
        <div class="horizontal-line"></div>
        <div class="vertilcal-line"></div>
        </a>
    </div>

    <div class="row">
        <div class="modal fade" id="projectModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="proModalLabel">Add project</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form">
                            <div class="form-group">
                                <label class="form-label">Project name</label>
                                <input type="text" class="form-control" id="proName">
                            </div>
                            <div class="form-group">
                                <label class="form-label">Project description</label>
                                <textarea class="form-control" rows="3" name=textarea id="proDescription"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="addProButton" data-dismiss="modal">Save Project</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="page-list clearfix" style="margin-top: 20px;">
        <ul class="grid">
            <li class="page-list-item grid-style-item"><a href="/s1701572-00201434/uuuuuuuu" rel="route">
                <div class="hover"></div>
                <div class="content">
                    <div class="header">
                        <div class="title">uuuuuuuu</div>
                    </div>
                    <div class="description">kdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkd
                        kdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkd
                        kdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkd
                    </div>
                </div>
            </a></li>
            <li class="page-list-item grid-style-item"><a href="/s1701572-00201434/hjkk" rel="route">
                <div class="hover"></div>
                <div class="content">
                    <div class="header">
                        <div class="title">hjkk</div>
                    </div>
                    <div class="description"></div>
                </div>
            </a></li>
            <li class="page-list-item grid-style-item"><a href="/s1701572-00201434/Get_started" rel="route">
                <div class="hover"></div>
                <div class="content">
                    <div class="header">
                        <div class="title">Get started</div>
                    </div>
                    <div class="icon"><img src="https://gyazo.com/5f93e65a3b979ae5333aca4f32600611/thumb/400"
                                           class="lazy-load-img"></div>
                </div>
            </a></li>
        </ul>
    </div>
</div>

</body>
</html>