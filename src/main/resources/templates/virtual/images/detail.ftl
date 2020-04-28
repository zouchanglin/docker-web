<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>镜像详情</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../../common/nav.ftl">
    <h1>镜像详情</h1>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table">
                <tbody>
                    <tr>
                        <td>
                            architecture
                        </td>
                        <td>
                            ${imageDetailInfo.architecture}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            os
                        </td>
                        <td>
                            ${imageDetailInfo.os!}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            virtualSize
                        </td>
                        <td>
                            ${imageDetailInfo.virtualSize!}
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>