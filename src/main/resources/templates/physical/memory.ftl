<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宿主机内存信息</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/echarts.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <h4>>宿主机内存信息</h4>
    <div class="row clearfix" id="row">
        <div class="col-md-10 column" style="padding-left: 50px">
            <div class="progress progress-striped active">
                <div id="memory_load_progress" class="progress-bar progress-bar-success" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 10%">
                </div>
            </div>
        </div>
        <div class="col-md-2 column">
            <span id="memory_load_msg"></span>
        </div>
    </div>
    <div id="main" style="width: 1000px;height:500px;">

    </div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化ECharts实例
        let myChart = echarts.init(document.getElementById('main'));

        // 使用刚指定的配置项和数据显示图表
        //myChart.setOption(option);
    </script>
</div>
<script>
    let websocket = null;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://${projectUrl}/webSocket/memory");
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
        let memoryData = JSON.parse(event.data);
        //计算使用比率
        let ratio = (parseFloat(memoryData.usedNow) / parseFloat(memoryData.total) * 100).toFixed(2);
        document.getElementById('memory_load_progress').style = 'width: '+ ratio + '%';
        document.getElementById('memory_load_msg').innerText = ratio + '% 已使用';

        //绘制图表
        option = {
            xAxis: {
                type: 'category',
                data: memoryData.time
            },
            yAxis: {
                type: 'value'
            },
            legend: {
                data: ['可用内存', '已用内存', '可用交换内存', '已用交换内存']
            },
            tooltip: {
                trigger: 'axis'
            },
            series: [
                {
                    data: memoryData.used,
                    type: 'line',
                    name: '已用内存',
                    smooth: true
                },
                {
                    data: memoryData.free,
                    type: 'line',
                    name: '可用内存',
                    smooth: true
                },
                {
                    data: memoryData.freeSwap,
                    type: 'line',
                    name: '可用交换内存',
                    smooth: true
                },
                {
                    data: memoryData.usedSwap,
                    type: 'line',
                    name: '已用交换内存',
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