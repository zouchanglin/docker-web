<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Success</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix" style="padding-top: 10px">
        <div class="col-md-12 column">
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    The operation successful!
                </h4> <strong>Success!</strong> ${msg}
            </div>
        </div>
    </div>
</div>

<script>
    setTimeout('location.href="${url}"', 1500);
</script>

</body>
</html>