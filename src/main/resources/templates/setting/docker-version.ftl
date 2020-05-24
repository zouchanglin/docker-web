<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宿主机</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <h4>Docker引擎信息</h4>
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td>
                DockerAPI版本
            </td>
            <td>
                ${dockerVersion.apiVersion}
            </td>
        </tr>
        <tr>
            <td>
                操作系统架构
            </td>
            <td>
                ${dockerVersion.arch}
            </td>
        </tr>
        <tr>
            <td>
                引擎构建时间
            </td>
            <td>
                ${dockerVersion.buildTime}
            </td>
        </tr>
        <tr>
            <td>
                Git Commit
            </td>
            <td>
                ${dockerVersion.gitCommit}
            </td>
        </tr>
        <tr>
            <td>
                Go Version
            </td>
            <td>
                ${dockerVersion.goVersion}
            </td>
        </tr>
        <tr>
            <td>
                内核版本
            </td>
            <td>
                ${dockerVersion.os} / ${dockerVersion.kernelVersion}
            </td>
        </tr>

        <tr>
            <td>
                Docker Version
            </td>
            <td>
                ${dockerVersion.version}
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>