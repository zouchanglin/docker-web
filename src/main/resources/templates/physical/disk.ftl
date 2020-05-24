<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宿主机磁盘信息</title>
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
    <h4>宿主机磁盘信息</h4>
    <div id="main" style="width: 1000px;height:500px;">

    </div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化ECharts实例
    let myChart = echarts.init(document.getElementById('main'));
    option = {
        title: {
            text: '宿主机磁盘信息',
            subtext: '代表磁盘总量',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已使用', '未使用']
        },
        series: [
            {
                name: '以GB为单位',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: ${diskInfo.used}, name: '已使用'},
                    {value: ${diskInfo.free}, name: '未使用'}
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
</script>
<script>
    let websocket = null;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://${projectUrl}/webSocket/disk");
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
        let diskData = JSON.parse(event.data);
        option = {
            title: {
                text: '宿主机磁盘信息',
                subtext: '代表磁盘总量',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['已使用', '未使用']
            },
            series: [
                {
                    name: '以GB为单位',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: [
                        {value: diskData.used, name: '已使用'},
                        {value: diskData.free, name: '未使用'}
                    ],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表
        myChart.setOption(option);
    };
</script>
</body>
</html>