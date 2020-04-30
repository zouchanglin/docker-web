<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Build image</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../../common/nav.ftl">
    <h1>Build image</h1>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" action="/images/build" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="exampleInputEmail1">repository</label><input type="text" name="repository" class="form-control" id="exampleInputEmail1" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">tag</label><input type="text" name="tag" class="form-control" id="exampleInputPassword1" />
                </div>
                <div class="form-group">
                    <label for="exampleInputFile">File input</label><input type="file" name="file" id="exampleInputFile" />
                    <p class="help-block">
                        The file must be less than 50M!
                    </p>
                </div>
                <button type="submit" class="btn btn-default">Start Build！</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>