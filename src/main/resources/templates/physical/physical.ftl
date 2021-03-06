<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宿主机</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <h4>宿主机基本信息</h4>
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td>
                操作系统名称
            </td>
            <td>
                ${basicInfoVO.osName}
            </td>
        </tr>
        <tr>
            <td>
                操作系统架构
            </td>
            <td>
                ${basicInfoVO.osArch}
            </td>
        </tr>
        <tr>
            <td>
                操作系统的版本
            </td>
            <td>
                ${basicInfoVO.osVersion}
            </td>
        </tr>
        <tr>
            <td>
                CPU核心数量
            </td>
            <td>
                ${basicInfoVO.cpuNumber}
            </td>
        </tr>
        <tr>
            <td>
                用户账户名称
            </td>
            <td>
                ${basicInfoVO.userName}
            </td>
        </tr>
        <tr>
            <td>
                用户主目录
            </td>
            <td>
                ${basicInfoVO.homePath}
            </td>
        </tr>

        <tr>
            <td>
                运行环境版本
            </td>
            <td>
                ${basicInfoVO.javaVersion}
            </td>
        </tr>
        <tr>
            <td>
                JVM实现名称
            </td>
            <td>
                ${basicInfoVO.vmName}
            </td>
        </tr>
        <tr>
            <td>
                JRE供应商
            </td>
            <td>
                <a href="${basicInfoVO.javaVendorUrl}">${basicInfoVO.javaVendor}</a>
            </td>
        </tr>
        <tr>
            <td>
                JVM规范版本
            </td>
            <td>
                ${basicInfoVO.vmSpecificationVersion}
            </td>
        </tr>
        <tr>
            <td>
                JVM规范供应商
            </td>
            <td>
                ${basicInfoVO.vmSpecificationVendor}
            </td>
        </tr>
        <tr>
            <td>
                JVM规范名称
            </td>
            <td>
                ${basicInfoVO.vmSpecificationName}
            </td>
        </tr>
        <tr>
            <td>
                JVM实现版本
            </td>
            <td>
                ${basicInfoVO.vmVersion}
            </td>
        </tr>

        <tr>
            <td>
                JRE规范供应商
            </td>
            <td>
                ${basicInfoVO.vmSpecificationVendor}
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>