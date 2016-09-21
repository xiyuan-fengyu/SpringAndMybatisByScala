/**
 * 下拉刷新插件(目前仅支持手机触摸方式，鼠标拖动不支持)
 * xiyuan_fengyu
 * 2016-09-21
 * 使用方式：
 * 假设需要刷新功能的div的id为mainContent,
 * 通过下面的代码来创建刷新工能
 var refresher = new SwipeRefresh($("#mainContent"));
 refresher.setOnRefreshListener(function(){
    console.log("开始刷新");
 });
 通过代码触发刷新(第一个参数为是否显示circleLoading出现的动画，第二个参数为动画开始的延迟时间，仅当第一个参数为true的时候有效)
 refresher.startRefresh(true, 2000);
 通过以下代码结束刷新
 refresher.stopRefresh();
 */

(function(global){
    "use strict";

    if (global["SwipeRefresh"] == null) {
        var log = new Log(ctx + "/log/add");

        var SwipeRefresh = function(owner, aCircleSize){

            var color = "#0e95ff";

            /**
             * circleLoading 的半径
             * @type {number}
             */
            var circleSize = aCircleSize == null? 20: aCircleSize;

            /**
             刷新状态
             -1: 隐藏状态
             0: 拖动状态
             1: 准备进入刷新状态
             2: 正在刷新
             3: 刷新结束，正在隐藏，隐藏动画结束时，状态改为-1
             * @type {number}
             */
            var refreshStatus = -1;

            /**
             拖动状态
             1：从<=10的位置开始向下拖动，启动下拉刷新检测
             0：从<=10的位置开始拖动，拖动的方向还未确定
             -1：从<=10的位置开始向上拖动，或者从其他位置开始拖动
             * @type {number}
             */
            var dragStatus = 0;

            /**
             * circleLoading 的几种top取值
             * @type {number}
             */
            var topInitial = 0;
            var topWhenRefreshing = 0;
            var topMinForRefresh = 0;
            var topMaxForDrag = 0;

            /**
             * 记录触控点的y坐标
             * @type {number}
             */
            var touchY = 0;

            /**
             * 用于存储拖动刷新过程中超过最大拖动范围的距离
             * @type {number}
             */
            var remainDeltaY = 0;

            /**
             * 记录圆弧的起始和结束角度
             * @type {number}
             */
            var startAngle = 0;
            var endAngle = Math.PI / 3.0;

            owner.on("touchstart", function (e) {
                if (refreshStatus == -1) {
                    var scrollTop = $(this).scrollTop();
                    dragStatus = scrollTop > 10? -1: 0;

                    var touch = e.originalEvent.targetTouches[0];
                    touchY = touch.pageY;

                    remainDeltaY = 0;
                }
            });

            owner.on("touchmove", function (e) {
                var touch = e.originalEvent.targetTouches[0];
                var curY = touch.pageY;
                if (dragStatus == 0) {
                    dragStatus = curY - touchY > 0? 1: -1;
                    if (dragStatus == 1) {
                        refreshStatus = 0;
                        mainLoop();
                    }
                }

                if (dragStatus == -1) {
                    return true;
                }
                else {
                    //console.log("下拉刷新中");
                    var deltaY = curY - touchY;
                    if (remainDeltaY > 0 && deltaY < 0) {
                        if (remainDeltaY + deltaY >= 0) {
                            remainDeltaY += deltaY;
                            deltaY = 0;
                        }
                        else {
                            remainDeltaY = 0;
                            deltaY += remainDeltaY;
                        }
                    }

                    var circleLoadingTop = pxStrToInt($(circleLoading).css("top"));
                    var newTop = circleLoadingTop + deltaY;
                    if (newTop > topMaxForDrag) {
                        remainDeltaY += newTop - topMaxForDrag;
                        newTop = topMaxForDrag;
                    }
                    $(circleLoading).css("top", newTop + "px");

                    touchY = curY;
                    return false;
                }
                //return true;//可以滚动
                //return false;//禁止滚动
            });

            owner.on("touchend", function (e) {
                if (dragStatus == 1 && refreshStatus == 0) {
                    dragStatus = -1;

                    var circleLoadingTop = pxStrToInt($(circleLoading).css("top"));

                    if (circleLoadingTop >= topMinForRefresh) {
                        //放手后可以进入刷新状态
                        refreshStatus = 1;
                        $(circleLoading).animate({
                            top: topWhenRefreshing
                        }, (circleLoadingTop  - topWhenRefreshing) * 2, function () {
                            refreshStatus = 2;
                            onRefresh();
                        });
                    }
                    else {
                        //隐藏circleLoading
                        $(circleLoading).animate({
                            top: topInitial
                        }, (circleLoadingTop  - topInitial) * 2, function () {
                            refreshStatus = -1;
                            $(circleLoading).css("top", topInitial +  "px");
                        });
                    }
                }
            });

            var createCircleLoading = function() {
                var id = new Date().getTime() + "_" + parseInt(Math.random() * 9999);
                var width = circleSize * 2;
                var margin = circleSize;
                var left = owner.width() / 2 - circleSize - margin;

                var paddingTop = owner.css("padding-top");
                if (paddingTop == "") {
                    paddingTop = "0";
                }

                topInitial = -(width + margin);
                topWhenRefreshing = parseInt(paddingTop) + margin;
                topMinForRefresh = topWhenRefreshing + circleSize * 3;
                topMaxForDrag = topWhenRefreshing + circleSize * 6;

                owner.append("<canvas id='" + id + "' width='" + width + "' height='" + width + "' " +
                    "style='position: absolute; left: " + left + "px;  top: " + topInitial + "px; z-index: 999999; '></canvas>");
                var canvas = owner.find("#" + id)[0];

                canvas.drawCircle = function(circle, startAngle, endAngle){
                    var context = this.getContext("2d");
                    context.clearRect(0, 0, this.width, this.height);
                    context.beginPath();
                    context.strokeStyle = color;
                    context.lineWidth = 4 * circle / 20.0;
                    context.lineCap = "round";
                    context.arc(circleSize, circleSize, Math.max(0, circle - context.lineWidth / 2.0), startAngle, endAngle, false);
                    context.stroke();
                };

                return canvas;
            };

            /**
             * 用于绘制 loading 动画的canvas
             */
            var circleLoading = createCircleLoading();

            var angleSpeedIndex = 0;
            var indexRate = 60.0;

            /**
             * 在拖拽的时候，根据circleLoading的top计算出一个circle，用于绘制
             * @param top
             * @returns {number}
             */
            var circleSizeForTop = function (top) {
                return circleSize * (Math.min(top, topWhenRefreshing) - topInitial) / (topWhenRefreshing - topInitial);
            };

            var PI = Math.PI;
            var PI2 = PI * 2;
            var sin = Math.sin;
            /**
             * 在 refreshStatus = 0 的时候，计算始末角度
             * @param index
             * @returns {*[]}
             */
            var computeAngleWhenDrag = function(top) {
                var x = (top - topInitial) / (topMaxForDrag - topInitial);
                startAngle = -PI / 2.0 + PI / 4.0 * x;
                endAngle = -PI / 2.0 + PI * 5 / 4.0 * x;
            };

            /**
             * 在 refreshStatus = 1 的时候，计算始末角度
             * @param index
             * @returns {*[]}
             */
            var computeAngleWhenGotoRefresh = function(top) {
                var x = (top - topWhenRefreshing) / (topMaxForDrag - topWhenRefreshing);
                startAngle = - PI / 4.0 * x;
                endAngle = PI / 3.0 + PI * 5 / 12.0 * x;
            };

            var inscreaseIndexAngle = function() {
                angleSpeedIndex += 1;
                angleSpeedIndex %= (indexRate * 2);
                endAngle += (sin(PI * angleSpeedIndex / indexRate) + 1.2) * PI / 40.0;
                startAngle += (sin(PI * (angleSpeedIndex - indexRate / 3.0) / indexRate) + 1.2) * PI / 40.0;
                if (startAngle >= PI2) {
                    startAngle -= PI2;
                    endAngle -= PI2;
                }
            };

            /**
             * 主循环，每一帧调用update
             * @param privateProps
             */
            var mainLoop = function () {
                if (refreshStatus == -1) {
                    return;
                }
                update();
                setTimeout(mainLoop, 1000 / 45.0);
            };

            var update = function () {
                var top = pxStrToInt($(circleLoading).css("top"));
                if (refreshStatus == 0) {
                    computeAngleWhenDrag(top);
                    circleLoading.drawCircle(circleSizeForTop(top), startAngle, endAngle);
                }
                else if (refreshStatus == 1) {
                    computeAngleWhenGotoRefresh(top);
                    circleLoading.drawCircle(circleSize, startAngle, endAngle);
                }
                else if (refreshStatus == 2){
                    inscreaseIndexAngle();
                    circleLoading.drawCircle(circleSize, startAngle, endAngle);
                }
                else if (refreshStatus == 3) {
                    circleLoading.drawCircle(circleSizeForTop(top), startAngle, endAngle);
                }
            };

            this.setColor = function (c) {
                color = c;
            };

            var onRefreshListener = null;

            this.setOnRefreshListener = function (listener) {
              if (listener && $.isFunction(listener)) {
                  onRefreshListener = listener;
              }
            };

            var onRefresh = function () {
                if (onRefreshListener && $.isFunction(onRefreshListener)) {
                    onRefreshListener();
                }
            };

            this.startRefresh = function(animated, delay) {
                if (animated == false) {
                    $(circleLoading).css("top", topWhenRefreshing +  "px");
                    refreshStatus = 2;
                    mainLoop();
                    onRefresh();
                }
                else {
                    var action = function () {
                        var top = pxStrToInt($(circleLoading).css("top"));
                        refreshStatus = 0;
                        mainLoop();
                        $(circleLoading).animate({
                            top: topWhenRefreshing
                        }, Math.abs(top - topWhenRefreshing) * 10, function () {
                            refreshStatus = 2;
                            onRefresh();
                        });
                    };
                    if (delay == null || delay == 0) {
                        action();
                    }
                    else {
                        setTimeout(function () {
                            action();
                        }, delay);
                    }
                }
            };

            this.stopRefresh = function () {
                var top = pxStrToInt($(circleLoading).css("top"));
                refreshStatus = 3;
                $(circleLoading).animate({
                    top: topInitial
                }, (top  - topInitial) * 10, function () {
                    refreshStatus = -1;
                });
            };

        };

        var pxStrToInt = function (px) {
            if (px == null || px == "") {
                return 0;
            }
            return parseInt(px);
        };

        global["SwipeRefresh"] = SwipeRefresh;
    }

})(this);