<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户设置</title>
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
                    用户中心
                </li>
            </ul>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" style="padding-right: 110px" role="form" action="/setting/repository" method="post">
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">仓库地址</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="registry.cn-hangzhou.aliyuncs.com" id="inputPassword3" name="serverAddress"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">用户名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputEmail3" name="userName"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail1" class="col-sm-2 control-label">用户密码</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="inputEmail1" name="password"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail1" class="col-sm-2 control-label">邮件地址</label>
                    <div class="col-sm-5">
                        <input type="email" class="form-control" id="inputEmail1" name="email"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail1" class="col-sm-2 control-label">命名空间</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="inputEmail1" name="namespace"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">修改为此配置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>