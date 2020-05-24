<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Commit This Container</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../../common/nav.ftl">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" style="padding-right: 110px" role="form" action="/container/commit" method="post">
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                        <h4>提交容器 ${containerName}</h4>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">容器</label>
                    <div class="col-sm-10">
                        <input type="text" value="${containerId}" readonly="readonly" class="form-control" id="inputPassword3" name="containerId"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">仓库</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control"
                               value="${serverAddress}/${namespace}/${containerName}"
                               id="inputEmail3" name="repo"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">标记</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputEmail3" name="tag" placeholder="1.0 / 1.1 / 1.2.."/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">备注</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="容器提交信息" id="inputEmail3" name="comment"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">作者</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${author}" id="inputEmail3" name="author"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default btn-success">Start Commit！</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>