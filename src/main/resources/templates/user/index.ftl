<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>登录页</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <form class="form-signin" action="/login" method="post">
        <h3 class="form-signin-heading">登录至Docker-Web</h3>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus name="username">
        <label for="inputPassword" class="sr-only">Password</label>
        <input  style="margin-top: 10px" type="password" id="inputPassword" class="form-control" placeholder="Password" required name="password">
        <div class="checkbox">
            <label>
                <input type="checkbox" value="true" name="keep"> 10天内免登录
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

</div> <!-- /container -->
</body>
</html>