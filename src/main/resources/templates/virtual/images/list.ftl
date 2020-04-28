<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>本地镜像列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../../common/nav.ftl">
    <h1>本地镜像列表</h1>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        REPOSITORY
                    </th>
                    <th>
                        TAG
                    </th>
                    <th>
                        IMAGE ID
                    </th>
                    <th>
                        CREATED
                    </th>
                    <th>
                        SIZE
                    </th>
                    <th>

                    </th>
                </tr>
                </thead>
                <tbody>
                <#list images as image>
                    <tr>
                        <td>
                            ${image.repository}
                        </td>
                        <td>
                            ${image.tag}
                        </td>
                        <td>
                            ${image.imageId}
                        </td>
                        <td>
                            ${image.created}
                        </td>
                        <td>
                            ${image.size} M
                        </td>
                        <td>
                            <a type="button" class="btn btn-sm btn-default btn-danger" href="/images/remove?imageId=${image.imageId}">删除</a>
                            <a type="button" class="btn btn-sm btn-default btn-info" href="/images/inspect?imageId=${image.imageId}">详情</a>
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