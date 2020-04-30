<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix" style="padding-top: 10px">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    The operation failure!
                </h4> <strong>Error!</strong> ${msg} <a href="${url}" class="alert-link">alert link</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>