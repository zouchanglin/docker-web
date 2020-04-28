<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search images list</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../../common/nav.ftl">
    <h1>Search images list</h1>
    <div class="row clearfix">
        <form class="navbar-form navbar-left" role="search" action="/images/search" method="get">
            <div class="form-group">
                <input type="text" name="key" class="form-control" />
            </div> <button type="submit" class="btn btn-default">Search Images</button>
        </form>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        name
                    </th>
                    <th>
                        description
                    </th>
                    <th>
                        stars
                    </th>
                    <th>
                        official
                    </th>
                    <th>
                        automated
                    </th>
                    <th>

                    </th>
                </tr>
                </thead>
                <tbody>
                <#if searchImageList ??>


                <#list searchImageList as searchImage>
                    <tr>
                        <td>
                            ${searchImage.name}
                        </td>
                        <td>
                            ${searchImage.description}
                        </td>
                        <td>
                            ${searchImage.stars}
                        </td>
                        <td>
                            ${searchImage.official !}
                        </td>
                        <td>
                            ${searchImage.automated !}
                        </td>
                        <td>
                            <button type="button" class="btn btn-default btn-success">Download</button>
                        </td>
                    </tr>
                </#list>
                </#if>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>