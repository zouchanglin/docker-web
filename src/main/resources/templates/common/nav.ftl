<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
        <a class="navbar-brand" href="/">Docker控制中心</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" disabled="true" class="dropdown-toggle" data-toggle="dropdown">物理机监控<strong class="caret"></strong></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="/physical/index">基本信息</a>
                    </li>
                    <li>
                        <a href="/physical/disk">磁盘监控</a>
                    </li>
                    <li>
                        <a href="/physical/cpu">CPU监控</a>
                    </li>
                    <li>
                        <a href="/physical/memory">内存监控</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">镜像管理<strong class="caret"></strong></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">镜像列表</a>
                    </li>
                    <li>
                        <a href="#">搜索镜像</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">容器管理<strong class="caret"></strong></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">容器列表</a>
                    </li>
                    <li>
                        <a href="#">运行中</a>
                    </li>
                    <li>
                        <a href="#">已停止</a>
                    </li>
                    <li>
                        <a href="#">创建容器</a>
                    </li>
                    <li>
                        <a href="#">监控报表</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">系统设置<strong class="caret"></strong></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">加速链接</a>
                    </li>
                    <li>
                        <a href="#">用户中心</a>
                    </li>
                    <li>
                        <a href="#">其它设置</a>
                    </li>
                </ul>
            </li>
        </ul>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" />
            </div> <button type="submit" class="btn btn-default">Search</button>
        </form>
        <ul class="nav navbar-nav navbar-right">

        </ul>
    </div>
</nav>