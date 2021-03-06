<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>已退出容器列表</title>
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
            <ul class="breadcrumb">
                <li>
                    <a href="/container/list">容器管理</a>
                </li>
                <li class="active">
                    已退出容器
                </li>
            </ul>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        CONTAINER ID
                    </th>
                    <th>
                        IMAGE
                    </th>
                    <th>
                        COMMAND
                    </th>
                    <th>
                        CREATED
                    </th>
                    <th>
                        STATUS
                    </th>
                    <th>
                        STATE
                    </th>
                    <th>
                        PORTS
                    </th>
                    <th>
                        NAMES
                    </th>
                    <th>

                    </th>
                </tr>
                </thead>
                <tbody>
                <#list containers as container>
                    <#if container.state == "running">
                        <tr class="success">
                    <#elseif container.state == "exited">
                        <tr class="warning">
                    <#else>
                        <tr>
                    </#if>
                        <td>
                            ${container.containerId}
                        </td>
                        <td>
                            ${container.image}
                        </td>
                        <td>
                            ${container.command}
                        </td>
                        <td>
                            ${container.created}
                        </td>
                        <td>
                            ${container.status}
                        </td>
                        <td>
                            ${container.state}
                        </td>
                        <td>
                            ${container.ports}
                        </td>
                        <td>
                            ${container.names}
                        </td>
                        <td>
                            <a href="/container/start?containerId=${container.containerId}" type="button" class="btn btn-sm btn-default btn-success">启动</a>
                            <a href="/container/remove?containerId=${container.containerId}" type="button" class="btn btn-sm btn-danger">删除</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>