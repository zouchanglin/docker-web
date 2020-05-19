<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <h4> 欢迎访问 App Store！</h4>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-3 column">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">Gitlab社区版</a>
                        <div class="list-group-item">
                            <img src="/image/gitlab.jpg" alt="GitLab" style="width: 80%; height: 80%">
                        </div>
                        <div class="list-group-item">
                            GitLab是一个开源应用程序，实现一个自托管的Git项目仓库，可通过Web界面进行访问公开的或者私人项目。
                        </div>
                        <a class="list-group-item active" href="/applications/download?app=gitlab">下载并运行</a>
                    </div>
                </div>
                <div class="col-md-3 column">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">Nexus私服</a>
                        <div class="list-group-item">
                            <img src="/image/maven.jpg" alt="Maven" style="width: 80%; height: 80%">
                        </div>
                        <div class="list-group-item">
                            使用Docker创建一个nexus私服，实现一个自托管的Maven仓库，存储编译后的成果物。
                        </div>
                        <a class="list-group-item active" href="/applications/download?app=nexus">下载并运行</a>
                    </div>
                </div>
                <div class="col-md-3 column">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">Docker Repository</a>
                        <div class="list-group-item">
                            <img src="/image/docker.jpg" alt="docker" style="width: 80%; height: 80%">
                        </div>
                        <div class="list-group-item">
                            Repository是一个私有的Docker仓库，可通过Web界面进行搜索和拉取镜像。
                        </div>
                        <a class="list-group-item active" href="/applications/download?app=repository">下载并运行</a>
                    </div>
                </div>

                <div class="col-md-3 column">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">Spring-Initializr</a>
                        <div class="list-group-item">
                            <img src="/image/spring.jpg" alt="Spring-Initializr" style="width: 80%; height: 80%">
                        </div>
                        <div class="list-group-item">
                            Spring-Initializr是一个SpringBoot初始化器，使用它可以很方便的初始化你的SpringBoot应用程序。
                        </div>
                        <a class="list-group-item active" href="/applications/download?app=spring_initializr">下载并运行</a>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-3 column">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">Docker Repository WebUI</a>
                        <div class="list-group-item">
                            <img src="/image/docker.jpg" alt="Docker Repository WebUI" style="width: 80%; height: 80%">
                        </div>
                        <div class="list-group-item">
                            Docker Repository WebUI 可通过Web界面进行访问镜像资源。
                        </div>
                        <a class="list-group-item active" href="/applications/download?app=repository_web_ui">下载并运行</a>
                    </div>
                </div>
                <div class="col-md-3 column">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">Nginx</a>
                        <div class="list-group-item">
                            <img src="/image/nginx.jpg" alt="Nginx" style="width: 80%; height: 80%">
                        </div>
                        <div class="list-group-item">
                            Nginx是一个高性能的HTTP和反向代理web服务器。
                        </div>
                        <a class="list-group-item active" href="/applications/download?app=nginx">下载并运行</a>
                    </div>
                </div>
                <div class="col-md-3 column">
                    <div class="list-group">
                        <a href="#" class="list-group-item active">RabbitMQ</a>
                        <div class="list-group-item">
                            <img src="/image/rabbitmq.jpg" alt="RabbitMQ" style="width: 80%; height: 80%">
                        </div>
                        <div class="list-group-item">
                            RabbitMQ是实现了消息队列协议的消息中间件。
                        </div>
                        <a class="list-group-item active" href="/applications/download?app=rabbitmq">下载并运行</a>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </div>
</div>
</body>
</html>