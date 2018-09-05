//环境数据
function hcsstu() {
    var myChart = echarts.init(document.getElementById("hj_chart")),
        // 指定图表的配置项和数据
        option = {
            color:[
                "#5969fe",
                "#00f8fe",
                "#2b8dff",
                "#00f9ff",
                "#5377fe",
                "#00fec5",
                "#1ec9fc",
                "#00bb90",
                "#5377fe",
                "#00fec5",
                "#1ec9fc",
                "#00bb90",
                "#5377fe",
                "#00fec5",
                "#5377fe",
                "#00fec5"
            ],
            /*tooltip : {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },*/
            legend: {
                show: true,
                type:"scroll",//多图例翻页
                right:"6%",
                top:"5%",
                itemGap: 5,
                orient: 'horizontal',
                inactiveColor: '#f2f2f2',//图例关闭时颜色
                pageButtonItemGap:0,
                pageButtonGap:3,
                pageIconColor:"#00fec5",
                pageIconInactiveColor:"#5969fe",
                pageIconSize:10,
                pageTextStyle:{
                    color:"#ffffff",//图例翻页数值颜色
                },
                textStyle: {
                    color: "#03faff",//图例文字颜色
                },
                data:['温度','湿度','TVOC','菌落','甲醛','PM2.5','ECO2','有害气体']
            },
            /*toolbox: {
                feature: {
                    saveAsImage: {}
                },
                top:"3%",
            },*/
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['周一','周二','周三','周四','周五','周六','周日'],
                    axisLabel:{//坐标轴刻度
                        // interval:1,
                        // margin:5,
                        color:"#03faff",//刻度标签颜色
                    },
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel:{//坐标轴刻度
                        // interval:1,
                        // margin:5,
                        color:"#03faff",//刻度标签颜色
                    },
                }
            ],
            series : [
                {
                    name:'有害气体',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[1.10, 1.22, 0.91, 1.24, 0.80, 2.10, 2.10]
                },
                {
                    name:'ECO2',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[1.20, 1.52, 1.21, 1.34, 0.90, 2.20, 2.20]
                },
                {
                    name:'PM2.5',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[1.20, 1.62, 1.31, 1.44, 1.00, 2.30, 2.30]
                },
                {
                    name:'甲醛',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[1.20, 1.72, 1.51, 1.54, 1.10, 2.40, 2.40]
                },
                {
                    name:'菌落',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[2.20, 1.82, 1.91, 2.34, 2.90, 3.10, 3.10]
                },
                {
                    name:'TVOC',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[1.50, 2.32, 2.01, 1.54, 1.90, 3.20, 4.10]
                },
                {
                    name:'温度',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data:[26, 25, 28, 27, 30, 22, 21]
                },
                {
                    name:'湿度',
                    type:'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    areaStyle: {normal: {}},
                    data:[72, 63, 70, 53, 59, 63, 52]
                }
            ],
            animationDuration:2000,//动画市场
        };
    myChart.clear();
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}