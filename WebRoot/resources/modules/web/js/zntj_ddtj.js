var posList = [
	    'left', 'right', 'top', 'bottom',
	    'inside',
	    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
	    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
	];
$(document).ready(function() {
	 
	archiveTypeCount();
	storeTypeCount();
});
//盘点统计
function archiveTypeCount() {
var myChart = echarts.init(document.getElementById("archiveTypeCount")),
	configParameters = {
	    
	};
	config = {
	    rotate: 90,
	    align: 'left',
	    verticalAlign: 'middle',
	    position: 'insideBottom',
	    distance: 15,
	    onChange: function () {
	        var labelOption = {
	            normal: {
	                rotate: app.config.rotate,
	                align: app.config.align,
	                verticalAlign: app.config.verticalAlign,
	                position: app.config.position,
	                distance: app.config.distance
	            }
	        };
	        myChart.setOption({
	            series: [{
	                label: labelOption
	            }, {
	                label: labelOption
	            }, {
	                label: labelOption
	            }, {
	                label: labelOption
	            }]
	        });
	    }
	};
	var labelOption = {
	    normal: {
	        show: false,
	        
	        rich: {
	            name: {
	                textBorderColor: '#fff'
	            }
	        }
	    }
	};

	option = {
	    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    title : {
			show : true,
			text : '档案分类调档统计图',
			left:'center',
			textStyle : {
				color : '#00ffff',
				fontStyle : '',
				fontSize : 18,
				fontFamily : 'PingFangSC-Light',
				

			}
		},
	    legend: {
	    	textStyle : {
				color : "white",// 图例文字颜色
			},
	        data: ['档案借阅', '档案归还', '档案盘点'],
	        orient:'vertical',
	        left:'right',
	        top:150
	       
	    },
	    toolbox: {
	        show: false,
	        orient: 'vertical',
	        left: 'right',
	        top: 'center',
	        feature: {
	            mark: {show: true},
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    calculable: true,
	    xAxis: [
	        {
	            type: 'category',
	            axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine : {
    				lineStyle : {
    					color : '#1ec9fc',
    				}
    			},
	            axisTick: {show: false},
	            splitLine:{show: false},//去除网格线
	            splitArea : {show : false},//保留网格区域
	            data: ['文书档案', '会计档案', '声像档案', '科技档案']
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine : {
    				lineStyle : {
    					color : '#1ec9fc',
    				}
    			},
	            splitLine:{show: false},//去除网格线
	            splitArea : {show : false}//保留网格区域
	        }
	    ],
	    series: [
	        {
	            name: '档案借阅',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            itemStyle : {
							normal : {
								borderColor : "#5377fe",
								borderWidth : 2,
								opacity : 0.8,
								color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
									offset : 0,
									color : 'rgba(83, 119, 254, 0.4)'
								}, {
									offset : 0.5,
									color : 'rgba(83, 119, 254, 0.4)'
								}, {
									offset : 1,
									color : 'rgba(83, 119, 254, 0.4)'
								} ])
							}
						},
	            data: [320, 332, 301, 334]
	        },
	        {
	            name: '档案归还',
	            type: 'bar',
	            label: labelOption,
	            itemStyle : {
							normal : {
								borderColor : "#5377fe",
								borderWidth : 2,
								opacity : 0.8,
								color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
									offset : 0,
									color : 'rgba(191, 140, 255, 0.4)'
								}, {
									offset : 0.5,
									color : 'rgba(191, 140, 255, 0.4)'
								}, {
									offset : 1,
									color : 'rgba(191, 140, 255, 0.4)'
								} ])
							}
						},
	            data: [220, 182, 191, 234]
	        },
	        {
	            name: '档案盘点',
	            type: 'bar',
	            label: labelOption,
	            itemStyle : {
							normal : {
								borderColor : "#5377fe",
								borderWidth : 2,
								opacity : 0.8,
								color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
									offset : 0,
									color : 'rgba(63, 230, 254, 0.4)'
								}, {
									offset : 0.5,
									color : 'rgba(63, 230, 254, 0.4)'
								}, {
									offset : 1,
									color : 'rgba(63, 230, 254, 0.4)'
								} ])
							}
						},
	            data: [150, 232, 201, 154]
	        }
	    ]
	};
	myChart.clear();
	// myChart.showLoading();
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}
// 盘点用户统计
function storeTypeCount() {
	
	var myChart = echarts.init(document.getElementById("storeTypeCount")),
	configParameters = {
	};
	config = {
	    rotate: 90,
	    align: 'left',
	    verticalAlign: 'middle',
	    position: 'insideBottom',
	    distance: 15,
	    onChange: function () {
	        var labelOption = {
	            normal: {
	                rotate: app.config.rotate,
	                align: app.config.align,
	                verticalAlign: app.config.verticalAlign,
	                position: app.config.position,
	                distance: app.config.distance
	            }
	        };
	        myChart.setOption({
	            series: [{
	                label: labelOption
	            }, {
	                label: labelOption
	            }, {
	                label: labelOption
	            }, {
	                label: labelOption
	            }]
	        });
	    }
	};


	var labelOption = {
	    normal: {
	        show: false,
	        
	        rich: {
	            name: {
	                textBorderColor: '#fff'
	            }
	        }
	    }
	};

	option = {
	    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    title : {
			show : true,
			left:'center',
			text : '档案分类调档统计图',
			textStyle : {
				color : '#00ffff',
				fontStyle : '',
				fontSize : 18,
				fontFamily : 'PingFangSC-Light',

			}
		},
	    legend: {
	    	textStyle : {
				color : "white",// 图例文字颜色
			},
	        data: ['档案借阅', '档案归还', '档案盘点'],
	        orient:'vertical',
	        left:'right',
	        top:150,
	    },
	    toolbox: {
	        show: false,
	        orient: 'vertical',
	        left: 'right',
	        top: 'center',
	        feature: {
	            mark: {show: true},
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    calculable: true,
	    xAxis: [
	        {
	            type: 'category',
	            axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine : {
    				lineStyle : {
    					color : '#1ec9fc',
    				}
    			},
	            axisTick: {show: false},
	            splitLine:{show: false},//去除网格线
	            splitArea : {show : false},//保留网格区域
	            color:"white",
	            data: ['文书档案', '会计档案', '声像档案', '科技档案']
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine : {
    				lineStyle : {
    					color : '#1ec9fc',
    				}
    			},
	            splitLine:{show: false},//去除网格线
	            color:"white",
	            splitArea : {show : false}//保留网格区域
	        }
	    ],
	    series: [
	        {
	            name: '档案借阅',
	            type: 'bar',
	            barGap: 0,
	            label: labelOption,
	            itemStyle : {
							normal : {
								borderColor : "#5377fe",
								borderWidth : 2,
								opacity : 0.8,
								color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
									offset : 0,
									color : 'rgba(83, 119, 254, 0.4)'
								}, {
									offset : 0.5,
									color : 'rgba(83, 119, 254, 0.4)'
								}, {
									offset : 1,
									color : 'rgba(83, 119, 254, 0.4)'
								} ])
							}
						},
	            data: [320, 332, 301, 334]
	        },
	        {
	            name: '档案归还',
	            type: 'bar',
	            label: labelOption,
	            itemStyle : {
							normal : {
								borderColor : "#5377fe",
								borderWidth : 2,
								opacity : 0.8,
								color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
									offset : 0,
									color : 'rgba(191, 140, 255, 0.4)'
								}, {
									offset : 0.5,
									color : 'rgba(191, 140, 255, 0.4)'
								}, {
									offset : 1,
									color : 'rgba(191, 140, 255, 0.4)'
								} ])
							}
						},
	            data: [220, 182, 191, 234]
	        },
	        {
	            name: '档案盘点',
	            type: 'bar',
	            label: labelOption,
	            itemStyle : {
							normal : {
								borderColor : "#5377fe",
								borderWidth : 2,
								opacity : 0.8,
								color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
									offset : 0,
									color : 'rgba(63, 230, 254, 0.4)'
								}, {
									offset : 0.5,
									color : 'rgba(63, 230, 254, 0.4)'
								}, {
									offset : 1,
									color : 'rgba(63, 230, 254, 0.4)'
								} ])
							}
						},
	            data: [150, 232, 201, 154]
	        }
	    ]
	};
	myChart.clear();
	// myChart.showLoading();
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}