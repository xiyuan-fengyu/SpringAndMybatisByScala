<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Spider Test</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="Spider">
    <meta http-equiv="description" content="Spider">

    <link rel="icon" href="<%=ctx%>/image/favicon/favicon32.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="<%=ctx%>/image/favicon/favicon16.ico" type="image/x-icon"/>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=ctx%>/css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        #formDiv {
            padding: 15px 0 0;
        }
    </style>
</head>

<body>

    <div id="formDiv">
        <form action="<%=ctx%>/spider/get" method="get" class="form-horizontal" role="form" target="resultIF">
            <div class="form-group">
                <label for="inputUrl" class="col-md-2 control-label">Html Url</label>
                <div class="col-md-6">
                    <input id="inputUrl" name="url" type="text" class="form-control" placeholder="html url">
                </div>
                <button id="getHtml" type="button" class="col-md-1 btn btn-primary">Get</button>
            </div>
            <div class="form-group">
                <label for="inputCookie" class="col-md-2 control-label">Cookie</label>
                <div class="col-md-6">
                    <textarea id="inputCookie" name="cookie" rows="4" class="form-control" title="inputCookie"></textarea>
                </div>
            </div>
        </form>
    </div>

    <iframe name="resultIF" frameborder="0">
    </iframe>

</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=ctx%>/page/spider/index.js"></script>

</html>
