<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <title>AngularJs Demo</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="AngularJs">
    <meta http-equiv="description" content="AngularJs">

    <link rel="icon" href="<%=ctx%>/image/favicon/favicon32.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="<%=ctx%>/image/favicon/favicon16.ico" type="image/x-icon"/>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=ctx%>/css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        input.ng-invalid,
        input.ng-invalid:focus {
            border-color: #ff7832;
            webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(255,120,50,.6);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(255,120,50,.6);
        }

        .alert {
            margin-bottom: 0;
        }
    </style>
</head>

<body>
    <div ng-app="app" ng-controller="controller" class="container">
        <br>
        <br>

        <form class="form-horizontal" role="form" name="myForm">
            <div class="form-group">
                <label for="inputName" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <input ng-model="name" type="text" class="form-control" id="inputName" placeholder="Name">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-10 col-sm-offset-2">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th ng-click="sortKey = 'id'">#</th>
                            <th ng-click="sortKey = 'name'">Name</th>
                            <th ng-click="sortKey = 'age'">Age</th>
                            <th ng-click="sortKey = 'sex'">Sex</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in persons | filter:name | orderBy:sortKey">
                            <td>{{$index + 1}}</td>
                            <td ng-click="choosePerson(item)">{{item.name}}</td>
                            <td>{{item.age}}</td>
                            <td>{{item.sex | sexFormat }}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="form-group">
                <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input ng-model="email" name="email" type="email" class="form-control" id="inputEmail" placeholder="Email">
                </div>
            </div>
            <div ng-show="myForm.email.$error.email" alert-warning=""></div>
        </form>


        <br>
        <br>
        <form class="form-horizontal" role="form" name="loginForm">
            <div class="form-group">
                <label for="inputPhone" class="col-sm-2 control-label">Phone</label>
                <div class="col-sm-10">
                    <input ng-model="phone" name="phone" type="text" class="form-control" id="inputPhone" placeholder="Phone">
                </div>
            </div>
            <div ng-show="phone != null && !$string.isPhone(phone)" class="form-group">
                <div class="col-sm-10 col-sm-offset-2">
                    <div class="alert alert-warning" role="alert">手机格式有误</div>
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input ng-model="password" name="password" type="password" class="form-control" id="inputPassword" placeholder="Password">
                </div>
            </div>
            <div ng-show="password != null && $string.isEmpty(password)" class="form-group">
                <div class="col-sm-10 col-sm-offset-2">
                    <div class="alert alert-warning" role="alert">请填写密码</div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-10 col-sm-offset-2">
                    <button ng-click="login()" ng-disabled="!$string.isPhone(phone) || $string.isEmpty(password)" type="button" class="btn btn-primary">Login</button>
                </div>
            </div>
        </form>


        <br>
        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="inputPrice" class="col-sm-2 control-label">Price</label>
                <div class="col-sm-10">
                    <input ng-model="price" type="text" class="form-control" id="inputPrice" placeholder="Price">
                </div>
            </div>
            <div class="form-group">
                <label for="inputNum" class="col-sm-2 control-label">Num</label>
                <div class="col-sm-10">
                    <input ng-model="num" type="text" class="form-control" id="inputNum" placeholder="Num">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10 col-sm-offset-2">
                    <label class="form-control-static">Total: {{totalPrice()}}</label>
                </div>
            </div>
        </form>

        <br>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="selectName" class="col-sm-2 control-label">Name</label>
                <div class="col-sm-5">
                    <select ng-model="name" id="selectName" class="form-control">
                        <option value=""></option>
                        <option ng-repeat="item in persons" value="{{item.name}}">{{item.name}}</option>
                    </select>
                </div>
            </div>
        </form>
    </div>

</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- angularJs -->
<script src="//cdn.bootcss.com/angular.js/1.6.0/angular.min.js"></script>
<script>
    (function(){
        var ctx = "<%=ctx%>";

        angular.module("app", [])
            .controller("controller", function ($scope, $http, $interval, $myService) {
                $scope.name = "";

                $scope.$string = {
                    isEmpty: function (str) {
                        return str == null || str == "";
                    },
                    nonEmpty: function (str) {
                        return str != null && str != "";
                    },
                    isPhone: function (str) {
                        return str != null && str.match("^1[34578][0-9]{9,9}$") != null;
                    }
                };

                $scope.totalPrice = function () {
                    if ($scope.price && $scope.num) {
                        return $scope.price * $scope.num;
                    }
                    else {
                        return 0;
                    }
                };

                $http.get("angularJs/persons").then(function (res) {
                    if (res.data && res.data.success) {
                        $scope.persons = res.data.data;
                    }
                });

                //$timeout 与 $interval 类似
                $interval(function () {
                    $myService.sayHello($scope.name);
                }, 1000);

                $scope.choosePerson = function (person) {
                    console.log(person);
                };

                $scope.login = function () {
                    console.log("angularJs/login?phone=" + $scope.phone + "&password=" + $scope.password);
                };

            })
            .directive("alertWarning", function () {
                return {
                    restrict: "A",
                    template: '<div class="alert alert-warning" role="alert">邮箱地址格式错误</div>'
                };
            })
            .service("$myService", function () {
                this.sayHello = function (to) {
                    if (to != null && to != "" && to != this.lastVal) {
                        this.lastVal = to;
                        console.log("Hello, " + to + " !");
                    }
                };
            })
            .filter("sexFormat", function () {
                return function (sexInt) {
                    if (sexInt == 0) {
                        return "Boy";
                    }
                    else if (sexInt == 1) {
                        return "Girl";
                    }
                    else return "?";
                };
            });

    })();
</script>

</html>
