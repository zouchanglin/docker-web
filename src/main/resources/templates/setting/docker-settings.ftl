<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Docker地址设置</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="breadcrumb">
                <li>
                    <a href="#">系统设置</a>
                </li>
                <li class="active">
                    Docker IP配置
                </li>
            </ul>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" style="padding-right: 110px" role="form" action="/setting/docker" method="post">
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">服务IP</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="${dockerConfig.dockerIp}" id="inputPassword3" name="dockerIp"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">服务端口</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" value="${dockerConfig.dockerPort?c}" id="inputEmail3" name="dockerPort"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">修改为此IP</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>