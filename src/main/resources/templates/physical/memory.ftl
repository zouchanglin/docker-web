<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宿主机内存信息</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/echarts.min.js"></script>
</head>
<body>
<div class="container">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <h3>宿主机内存信息</h3>
    <div class="row clearfix" id="row">
        <div class="col-md-9 column">
            <div class="progress progress-striped active">
                <div id="memory_load_progress" class="progress-bar progress-bar-success" role="progressbar"
                     aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                     style="width: 10%">
                </div>
            </div>
        </div>
        <div class="col-md-3 column">
            <span id="memory_load_msg"></span>
        </div>
    </div>
    <div id="main" style="width: 1000px;height:600px;">

    </div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化ECharts实例
        let myChart = echarts.init(document.getElementById('main'));
        option = {
            legend: {},
            tooltip: {
                trigger: 'axis',
                showContent: false
            },
            dataset: {
                source: [
                    ['product', '2012', '2013', '2014', '2015', '2016', '2017', '2018', '2019'],
                    ['Matcha Latte', '41.1', '30.4', '65.1', 53.3, 83.8, 98.7, 53.3, 15.8],
                    ['Milk2 Tea1', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1, 63.3, 12.9],
                    ['Milk3 Tea2', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1, 63.3, 12.9],
                    ['Milk4 Tea3', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1, 63.3, 12.9],
                ]
            },
            xAxis: {type: 'category'},
            yAxis: {gridIndex: 0},
            grid: {top: '55%'},
            series: [
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {
                    type: 'pie',
                    id: 'pie',
                    radius: '30%',
                    center: ['50%', '25%'],
                    label: {
                        formatter: '{b}: {@2012} ({d}%)'
                    },
                    encode: {
                        itemName: 'product',
                        value: '2012',
                        tooltip: '2012'
                    }
                }
            ]
        };

        myChart.on('updateAxisPointer', function (event) {
            var xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
                var dimension = xAxisInfo.value + 1;
                myChart.setOption({
                    series: {
                        id: 'pie',
                        label: {
                            formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                        },
                        encode: {
                            value: dimension,
                            tooltip: dimension
                        }
                    }
                });
            }
        });

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
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

    };
</script>
</body>
</html>