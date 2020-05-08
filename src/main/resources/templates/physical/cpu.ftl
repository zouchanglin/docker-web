<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宿主机CPU信息</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/echarts.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <h1>宿主机CPU信息</h1>
    <div id="main" style="width: 1000px;height:500px;">

    </div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化ECharts实例
        let myChart = echarts.init(document.getElementById('main'));
    </script>
</div>
<script>
    let websocket = null;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://${projectUrl}/webSocket/cpu");
    }else {
        alert('该浏览器不支持WebSocket')
    }
    websocket.onopen = function (event) {
        console.log('建立连接');
    };
    websocket.onclose = function (event) {
        console.log('关闭连接');
    };
    websocket.onerror = function (event) {
        alert('websocket发生错误');
    };
    window.onbeforeunload = function () {
        websocket.close();
    };

    websocket.onmessage = function (event) {
        console.log('收到消息' + event.data);
        let cpuData = JSON.parse(event.data);

        //绘制图表
        option = {
            xAxis: {
                type: 'category',
                data: cpuData.time
            },
            yAxis: {
                type: 'value'
            },
            legend: {
                data: ['CPU系统使用率', 'CPU用户使用率', 'CPU当前等待率', 'CPU当前空闲率', 'CPU平均负载']
            },
            tooltip: {
                trigger: 'axis'
            },
            series: [
                {
                    data: cpuData.systemUsed,
                    type: 'line',
                    name: 'CPU系统使用率',
                    smooth: true
                },
                {
                    data: cpuData.userUsed,
                    type: 'line',
                    name: 'CPU用户使用率',
                    smooth: true
                },
                {
                    data: cpuData.wait,
                    type: 'line',
                    name: 'CPU当前等待率',
                    smooth: true
                },
                {
                    data: cpuData.free,
                    type: 'line',
                    name: 'CPU当前空闲率',
                    smooth: true
                },
                {
                    data: cpuData.load,
                    type: 'line',
                    name: 'CPU平均负载',
                    smooth: true
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表
        myChart.setOption(option);
    };
</script>
</body>
</html>