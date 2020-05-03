<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>创建容器</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fiuled">
    <#--边栏sidebar-->
    <#include "../../common/nav.ftl">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="breadcrumb">
                <li>
                    <a href="/container/list">容器管理</a>
                </li>
                <li class="active">
                    创建容器
                </li>
            </ul>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" action="/container/create" method="post">
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">源镜像</label>
                    <div class="col-sm-10">
                        <input type="text" value="${imageId}" readonly="readonly" class="form-control" id="inputPassword3" name="image"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">容器名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputEmail3" name="containerName"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail1" class="col-sm-2 control-label">端口映射</label>
                    <div class="col-sm-5">
                        <input type="number" placeholder="主机端口" class="form-control" id="inputEmail1" name="hostPort"/>
                    </div>
                    <div class="col-sm-5">
                        <input type="number" placeholder="容器端口" class="form-control" id="inputEmail1" name="containerPort"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail1" class="col-sm-2 control-label">数据卷映射</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" placeholder="主机数据卷, 如D:\download\docker-client-master" id="inputEmail1" name="hostPath"/>
                    </div>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" placeholder="容器数据卷, 如/root/bin" id="inputEmail1" name="containerPath"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail1" class="col-sm-2 control-label">环境变量</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" placeholder="容器环境变量，如MYSQL_ROOT_PASSWORD" id="inputEmail1" name="envK"/>
                    </div>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" placeholder="该环境变量对应值，如123456" id="inputEmail1" name="envV"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">磁盘限制</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" placeholder="以GB为单位，如120则表示120GB" id="inputPassword3" name="diskSize"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">内存限制</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" step="0.1" placeholder="以GB为单位，如4则表示4GB" id="inputPassword3" name="memorySize"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">CPU权重</label>
                    <div class="col-sm-10" style="padding-top: 8px">
                        <label>
                            <input type="radio" name="cpuShare" value="0" />
                        </label>10%
                        <label>
                            <input type="radio" name="cpuShare" value="1" />
                        </label>30%
                        <label>
                            <input type="radio" name="cpuShare" value="2" />
                        </label>50%
                        <label>
                            <input type="radio" name="cpuShare" value="3" />
                        </label>70%
                        <label>
                            <input type="radio" name="cpuShare" value="4" checked="checked"/>
                        </label>90%
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">创建容器</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>