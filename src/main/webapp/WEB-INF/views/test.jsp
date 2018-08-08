<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="com.sgy.entity.Project"%>
<%@page import="com.sgy.service.ExpertServiceImp"%>
<%@page import="com.sgy.util.SpringContextsUtil"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    ExpertServiceImp projectServiceImp = (ExpertServiceImp)SpringContextsUtil.getBean("expertServiceImp");
    List<Project> projects = projectServiceImp.listProject();
    for(int i=0;i<projects.size();i++){
        System.out.print(projects.get(i).getName());
        System.out.print(projects.get(i).getDescription());
    }
    System.out.println("Path: "+path);
    System.out.println("basePath: "+basePath);
    //CalendarMapper calendarMapper = (CalendarMapper)context.getBean("calendarMapper");
    //List<Project> menus = expertServiceImp.listProject();
%>
<!--<!DOCTYPE html>-->
<html lang="en">
<head>
    <!--<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title></title>
    <script src="/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-3.3.7.css">
    <script src="/js/bootstrap.min.js"></script>

    <link href="/css/app.css" rel="stylesheet">
    <link href="/css/app_icons.css" rel="stylesheet">-->
    <script src="/js/project_manage.js"></script>
    <style id="list_icon_size_page-list">
        .page-list .grid li {
            visibility: visible;
            width: 147px;
            height: 161px;
            margin: 20px 15.4px 15.4px 0;
        }</style>
</head>

<body>
<!--<div class="container" style="display: block;">-->

    <div class="row">
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
                        <h4 class="modal-title" id="proModalLabel">Add Evaluation</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form">
                            <div class="form-group">
                                <label class="form-label">* Evaluation name</label>
                                <input type="text" class="form-control" id="proName">
                            </div>
                            <div class="form-group">
                                <label class="form-label">Evaluation description</label>
                                <textarea class="form-control" rows="3" name=textarea id="proDescription"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="form-label">* Website URL</label>
                                <textarea class="form-control" rows="3" name=textarea id="proWebsite"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="addProButton" data-dismiss="modal">Save Evaluation</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="modal fade" id="projectEditModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="proEditModalLabel">Edit evaluation</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form">
                            <input type="text" class="form-control" id="proEditId" style="display:none;">
                            <div class="form-group">
                                <label class="form-label">* Evaluation name</label>
                                <input type="text" class="form-control" id="proEditName">
                            </div>
                            <div class="form-group">
                                <label class="form-label">Evaluation description</label>
                                <textarea class="form-control" rows="3" name=textarea id="proEditDescription"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="form-label">* Website URL</label>
                                <textarea class="form-control" rows="3" name=textarea id="proEditWebsite"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="proEditButton" data-dismiss="modal">Update Evaluation</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
    <div class="modal fade" id="delConfirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="proDelModalLabel">Are you sure to delete this project</h4>
                </div>
                <div class="modal-body" style="text-align: center;">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" style="background:#5cb85c;color: white;" id="confirmButton" data-dismiss="modal" onclick="delPro()">Confirm</button>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div class="page-list clearfix">
        <ul class="grid">
            <%
                for(int i=0;i<projects.size();i++)
                {
                    Project project=projects.get(i);
            %>
            <li class="page-list-item grid-style-item" style="width: 173px;height: 170px;"><a href="javascript:showTaskPage('<%=project.getId()%>','<%=project.getName()%>')" name="<%=project.getId()%>"  rel="route">
                <div class="hover"></div>
                <div class="content">
                    <div class="header">
                        <div class="title"><%=project.getName()%></div>
                    </div>
                    <div class="description"><%=project.getDescription()%></div>

                </div>
                </a>
                <button type="button" class="btn btn-default" aria-label="Left Align" onclick="edit(this)"
                        data-toggle="modal" data-target="#projectEditModal"  data-placement="bottom" title="Edit"
                        name="<%=project.getId()+","+project.getName()+","+project.getDescription()+","+project.getWebsiteURL()%>">
                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                </button>

                <button type="button" class="btn btn-default" aria-label="Left Align" data-toggle="modal" data-target="#delConfirmModal" onclick="delProRequest(this)"
                        name="<%=project.getId()%>" data-placement="bottom" title="Delete">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </button>
                <button type="button" class="btn btn-default" aria-label="Left Align" onclick="showReport(this)"
                        name="<%=project.getId()%>,<%=project.getName()%>" data-placement="bottom" title="Report">
                    <span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
                </button>
                <button type="button" class="btn btn-default" aria-label="Left Align" onclick="showTaskPage('<%=project.getId()%>','<%=project.getName()%>')"
                        name="<%=project.getId()%>,<%=project.getName()%>" data-placement="bottom" title="Tasks">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                </button>
            </li>
            <%
                }

            %>
        </ul>
    </div>
<!--</div>-->

</body>
</html>