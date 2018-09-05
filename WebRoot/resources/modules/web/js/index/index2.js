var starOpenChanal = 4;// 自动预览窗口数量
var openWinNum = 2;// 默认显示窗口数
var nhData = new Array();// 能耗数据
var urldata=""
//arr arr4模拟测试用
var arr1 = new Array();	
var arr2 = new Array();	
var arr3 = new Array();	
var humlist = new Array();
var humlistData = new Array();
var temlistData = new Array();
//echart初始化
var myChart = echarts.init(document.getElementById('humiture'));
var background = {
	type : 'linearGradient',
	x0 : 0,
	y0 : 0,
	x1 : 0,
	y1 : 1,
	colorStops : [ {
		offset : 0,
		color : '#d2e6c9'
	}, {
		offset : 1,
		color : 'white'
	} ]
};
$(document).ready(function() {
	$(".datj_right_sarch_submit").click(function(){
		archivesParticulars2(1)
		currentPage = 1;
	})
	getinty();
	archivesParticulars()
	chang1()
	// 初始化实时监控不显示返回实时监控
	$(".sp .back_carm").hide();
	// 加载温湿度数据
	gethcs();
	setInterval('gethcs()',10000);
	// 加载最新报警数据
	getalam();
	setInterval('getalam()',10000);
	// 加载最新档案操作数据
	getamsact();
	setInterval('getamsact()',10000);
	//加载待处理任务列表
	getPendingMission();
	setInterval('getPendingMission()',10000);

});
/*$(window).resize(function () {
    window.location.reload();
});*/
function chang1(){
	$("#index").attr("class","select")
	$("#znfx_dahx").attr("class","no_select")
	$("#zntj_datj").attr("class","no_select")
	$("#smartlog1").attr("class","no_select")
	$("#ywcz_dacx").attr("class","no_select")
	$("#deviceop1").attr("class","no_select")
}
//查看更多
function webJump(){
	var key=$(".datj_right_sarch_text").val();
	window.location.href=config.ywcz_dacx+"?key="+key
}
//快速搜索档案
function archivesParticulars2(i) {
	var keyWord=$(".datj_right_sarch_text").val();
	Ajax.custom({
		url : config.indexFindArchives,
		data : {
			keyWord:keyWord,
			pagesize:3,
			pageIndex:i
		},//传递参数
		type : 'post'
	}, function(data) {
		$("#searchArchive").html(template.render("archives-data-list-tmpl", {
			list : data.data.data,
			cou  : data.data.count
		}));
	});
	
}
// 加载温湿度数据
function gethcs() {
	// 加载温湿度数据
	Ajax.custom({
		url : config.getNewHumiture,
		data : {

		},// 传递参数
		type : 'GET'
	}, function(data) {
		moni();
		var hum = new Array();
		var tem = new Array();
		var categoryData = [];
		var errorData1 = new Array();
		var errorData2 = new Array();
		var barData = [];
		var color1=new Array();
		var color2=new Array();
		
		/*for (var i = 0; i < data.data.length; i++) {
			var humlist = new Array();
			var temlist = new Array();
			humlist[0] = data.data[i].storeName
			humlist[1] = data.data[i].avghum
			temlist[0] = data.data[i].storeName
			temlist[1] = data.data[i].avgtem
			hum[i] = humlist
			tem[i] = temlist
		}*/
		
		for (var i = 0; i < data.data.length; i++) {
		    categoryData.push('category' + i);
			humlist[i] =  data.data[i].storeName;
			humlistData[i] = data.data[i].avghum;
			temlistData[i] = data.data[i].avgtem;
			if(temlistData[i]>26||temlistData[i]<23){
				color1[i]="#a30bc7";
			}else{
				color1[i]="#712bf0";
			}
			if(humlistData[i]>55||humlistData[i]<50){
				color2[i]="#1198d0";
			}else{
				color2[i]="#7907cd";
			}
		    errorData1.push([
		    	data.data[i].storeName,
		        echarts.number.round(23),
		        echarts.number.round(26)
		    ])
		    errorData2.push([
		    	data.data[i].storeName,
		        echarts.number.round(50),
		        echarts.number.round(55)
		    ])
		}
		function renderItem1(params, api) {
		    var xValue = api.value(0);
		    var highPoint = api.coord([xValue, api.value(1)]);
		    var lowPoint = api.coord([xValue, api.value(2)]);
		    var halfWidth = api.size([1, 0])[0] * 0.1;
		    var style = api.style({
		        stroke: api.visual('color'),
		        fill: null
		    });

		    return {
		        type: 'group',
		        children: [{
		            type: 'line',
		            shape: {
		                x1: highPoint[0] - halfWidth-(halfWidth*2.2), y1: highPoint[1],
		                x2: highPoint[0] + halfWidth-(halfWidth*2.2), y2: highPoint[1]
		            },
		            style: style
		        }, {
		            type: 'line',
		            shape: {
		                x1: lowPoint[0] - halfWidth-(halfWidth*2.2), y1: lowPoint[1],
		                x2: lowPoint[0] + halfWidth-(halfWidth*2.2), y2: lowPoint[1]
		            },
		            style: style
		        }]
		    };
		}
		function renderItem2(params, api) {
		    var xValue = api.value(0);
		    var highPoint = api.coord([xValue, api.value(1)]);
		    var lowPoint = api.coord([xValue, api.value(2)]);
		    var halfWidth = api.size([1, 0])[0] * 0.1;
		    var style = api.style({
		        stroke: api.visual('color'),
		        fill: null
		    });

		    return {
		        type: 'group',
		        children: [{
		            type: 'line',
		            shape: {
		                x1: highPoint[0] - halfWidth+(halfWidth*2.2), y1: highPoint[1],
		                x2: highPoint[0] + halfWidth+(halfWidth*2.2), y2: highPoint[1]
		            },
		            style: style
		        }, {
		            type: 'line',
		            shape: {
		                x1: lowPoint[0] - halfWidth+(halfWidth*2.2), y1: lowPoint[1],
		                x2: lowPoint[0] + halfWidth+(halfWidth*2.2), y2: lowPoint[1]
		            },
		            style: style
		        }]
		    };
		}
		// 指定图表的配置项和数据
		var labelOptionHum = {
			    normal: {
			        show: true,
			        position: "top",			        
			        formatter: '{c}%RH',
			        fontSize: 16, 
			        color:"#00ffff"
			    }
			};
		var labelOptionTem = {
			    normal: {
			        show: true,
			        position: "top",			        
			        formatter: '{c}°C',
			        fontSize: 16,
			        color:"#00ffff"

			    }
			};
		var option = {
			    
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {  title: { text: '档案类型',font: '15px sans-serif' },  //图例标题 
					location : 'top',
					font : '10px sans-serif',
					border: {   //边框的样式 
		                strokeStyle: 'black', // 边框颜色 
		                lineWidth: 0, // 边框线粗细 
		                cornerRadius: 10, //边角弧度半径 
		                padding: 4 // 内容距离边框距离 
		            },
		            textFillStyle:'white'
			    },
			    
			    toolbox: {
			        show : false,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['custom', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    dataZoom: [
	                {
	                    type: 'inside',
	                    	start: 0,
	                    	end: 8/humlist.length*100,	
	                }
	            ],
			    xAxis : 
			        {
			            data : humlist
			            
			        }
			    ,			    
			    yAxis : {}
			    ,axisLabel : {
					color : "#ffffff"
				},
			    series : [
			        {
			            name:'温度',
			            itemStyle: {
				        	  normal: {
				        		  color:function (params){
				                        var colorList = color1;
				                        return colorList[params.dataIndex]}
				              }
				        },
			            type:'bar',
			            position: 'top',
			            label: labelOptionTem,
			            data:temlistData, 
			        },
			        {
				        type: 'custom',
				        name: '温度上下限',
				        itemStyle: {
				        	  normal: {
				        		  color:'#ffffff',  
				                  borderWidth: 1.5
				              }
				        },
				        renderItem: renderItem1,
				        data:errorData1,
				        z: 100
				    },
			        {
			            name:'湿度',
			            itemStyle:{  
		                     normal:{  
		                       color:function (params){
			                        var colorList = color2;
			                        return colorList[params.dataIndex]},  
		                       }  
		                },
			            type:'bar',
			            label: labelOptionHum,
			            data:humlistData/*arr3*/,
			        },
				    {
				        type: 'custom',
				        name: '湿度上下限',
				        itemStyle: {
				        	  normal: {
				        		  color:'#ffffff',  
				                  borderWidth: 1.5
				              }
				        },
				        renderItem: renderItem2,
				        data:errorData2,
				        z: 100
				    },
			    ]
			};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		
	});
}

function renderItem(params, api) {
    var xValue = api.value(0);
    var highPoint = api.coord([xValue, api.value(1)]);
    var lowPoint = api.coord([xValue, api.value(2)]);
    var halfWidth = api.size([1, 0])[0] * 0.1;
    var style = api.style({
        stroke: api.visual('color'),
        fill: null
    });

    return {
        type: 'group',
        children: [{
            type: 'line',
            shape: {
                x1: 20, y1: 10,
                x2: 20, y2:20
            },
            style: style
        }, {
            type: 'line',
            shape: {
            	 x1: 20, y1: 30,
                 x2: 20, y2:40
            },
            style: style
        }, {
            type: 'line',
            shape: {
            	 x1: 20, y1: 20,
                 x2: 20, y2:25
            },
            style: style
        }]
    };
}
// 加载最新报警数据
function getalam() {
	// 加载最新报警数据
	Ajax.custom({
				url : config.getNewAlarmList,
				data : {

				},// 传递参数
				type : 'GET'
			},
					function(data) {
						for (var i = 0; i < data.data.data.length; i++) {
							/** 格式化时间 day：年月日 dayHms：时分秒* */
							// 判断当前报警是否是当天
//							if (new Date(data.data.data[i].ctime)
//									.format("yyyy-MM-dd") == new Date()
//									.format("yyyy-MM-dd")) {
//								data.data.data[i].day = "今天";
//							} else {
								data.data.data[i].day = new Date(
										data.data.data[i].ctime)
										.format("yyyy-MM-dd");
//							}
							data.data.data[i].dayHms = new Date(
									data.data.data[i].ctime).format("hh:mm:ss")
						}
						$("#alarm").html(
								template.render("alarm-data-list-tmpl", {
									list : data.data.data
								}));
					});
}
// 加载最新档案操作数据
function getamsact() {
	Ajax.custom({
		url : config.getAmsArchiveAuditCount,
		data : {
		},//传递参数
		type : 'GET'
	}, function(data) {
		$("#count_inout_today").html(data.data.cou);
	});
}
//加载待处理任务列表
function getPendingMission() {
	// 加载最新档案操作数据
	Ajax
	.custom({
		url : config.getNewAmsActLogList,
		data : {
			
		},// 传递参数
		type : 'GET'
	},
	function(data) {
		for (var i = 0; i < data.data.data.length; i++) {
				data.data.data[i].day = new Date(
						data.data.data[i].ctime)
				.format("yyyy-MM-dd hh:mm:ss");
//			}
			data.data.data[i].playStime = new Date(
					data.data.data[i].ctime - 30000)
			.format("yyyy-MM-dd hh:mm:ss");
			data.data.data[i].playEtime = new Date()
			.format("yyyy-MM-dd hh:mm:ss");
		}
		$("#archive").html(
				template.render("archive-data-list-tmpl", {
					AmsActLogList : data.data.data
				}));
	});
}
// 加载盘点数据
function getinty() {
	 $.ajax({
	        type:'POST',
	        url:config.getSumCuoDangByEnname,
	        data:{
	        },
	        dataType:'json',
	        async:false,
	        success:function(data){
	        	$("#count_inty_today").html(data.data)
	}})
}
function archivesParticulars(){
	Ajax.custom({
		url : config.getAuditAmsList,
		data : {
			inout:1,
			status:0,
			pageIndex : 1
		},//传递参数
		type : 'GET'
	}, function(data) {
		for (var i = 0; i < data.data.data.length; i++) {
			data.data.data[i].day = new Date(
					data.data.data[i].ctime)
			.format("yyyy-MM-dd hh:mm:ss");
//		}
		data.data.data[i].playStime = new Date(
				data.data.data[i].ctime - 30000)
		.format("yyyy-MM-dd hh:mm:ss");
		data.data.data[i].playEtime = new Date()
		.format("yyyy-MM-dd hh:mm:ss");
	}
	$("#auditIn").html(
			template.render("auditIn-data-list-tmpl", {
				AmsActLogList : data.data.data
			}));
		});
}
function webjumpservice(i){
	location.href=config.rfidpd+'?storeId='+urldata[i].strore_id+'&storeName='+urldata[i].storeName+'&time='+urldata[i].vartime
}

function moni(){
	for(var i =0;i<20;i++){
		arr1[i] = "库房"+i;
		arr2[i] = "30";
		arr3[i] = "40";
	}
}
