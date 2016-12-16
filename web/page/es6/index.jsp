<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <title>ES6 Demo</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="ES6">
    <meta http-equiv="description" content="ES6">

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
        #logger {
            overflow-y: auto;
            max-height: 600px;
            margin-top: 20px
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="col-md-12">
            <ul id="logger" class="list-group">
            </ul>
        </div>
        <div class="col-md-12 form-group">
            <textarea id="inputLog" rows="4" class="form-control" title="inputLog"></textarea>
        </div>
        <div class="col-md-12 form-group">
            <button id="addLog" type="button" class="btn btn-primary">Submit</button>
        </div>
    </div>

</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript">
    (function(){
        class Logger {
            constructor() {
                this.dom = document.querySelector("#logger");
                this.logs = [];
            }

            log(str) {
                this.logs.push(str);
                this.dom.innerHTML = this.logs.map(item => '<li class="list-group-item">' + item.replace(/\n/g, '<br>') + '</li>').join("");
            }
        }

        let logger = new Logger();

        $(document).ready(() => {
            let inputLog = $("#inputLog");

            $("#addLog").click(() => {
               logger.log(inputLog.val());
            });
        });

    })();
</script>

</html>
