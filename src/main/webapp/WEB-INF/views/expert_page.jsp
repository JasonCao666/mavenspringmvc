<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="/js/jquery-3.3.1.js"></script>
    <script src="/js/expert.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-3.3.7.css">
    <script src="/js/bootstrap.min.js"></script>
</head>

<div class="container" style="width:600px;" id="about-box">

    <div class="row" style="text-align: center">
        <h3>Task</h3>
    </div>
    <div class="row">


       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                    Add task
       </button>
                <!-- Modal -->
    </div>

     <div class="row">
         <table class="table table-hover table-bordered scrolltable" id="table">
             <tbody>
             <thead id="tem">
                <tr id="temtr">
                    <th id="task_name">task name</th>
                    <th id="task_description">task description</th>
                    <th id="operation">operation</th>
                </tr>
            </thead>
             </tbody>
         </table>
     </div>
     <div class="row">
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Add task</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form">
                                    <div class="form-group">
                                        <label class="form-label">Task name</label>
                                        <input type="text" class="form-control" id="taskName">
                                    </div><!-- /input-group -->
                                    <div class="form-group">
                                        <label class="form-label">Task description</label>
                                        <textarea class="form-control" rows="3" name=textarea id="taskDescription"></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="addTaskButton" data-dismiss="modal" >Save Task</button>
                            </div>
                        </div>
                    </div>
     </div>
     </div>
     <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="editModalLabel">Edit task</h4>
                            </div>
                            <div class="modal-body">
                                <form role="form">
                                    <input type="text" class="form-control" id="edit_taskId" style="display:none;">
                                    <div class="form-group">
                                        <!--<span class="input-group-addon">
                                            <input type="checkbox">
                                        </span>-->
                                        <label class="form-label">Task name</label>
                                        <input type="text" class="form-control" id="edit_taskName">
                                    </div><!-- /input-group -->
                                    <div class="form-group">
                                        <label class="form-label">Task description</label>
                                        <textarea class="form-control" rows="3" name=textarea id="edit_taskDescription"></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="editTaskButton" data-dismiss="modal" >Save Edit</button>
                            </div>
                        </div>
                    </div>
     </div>






    <div class="row">
        <div id="userProcedure"></div>
    </div>
</div>

</body>
</html>