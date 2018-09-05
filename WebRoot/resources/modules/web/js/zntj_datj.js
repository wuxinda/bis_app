	var paddata=new Array();
	var namedata=new Array();
	var paddata1=new Array();
	var paddata2=new Array();
	var namedata1=new Array();
	
	var ddtj_dafl_out_num=new Array();
	var ddtj_dafl_in_num=new Array();
	var ddtj_dafl_check_num=new Array();
	var ddtj_dafl_name=new Array();
	var pageNums=new Array();
	var dafl_map = new Map();
	var dafl_map_num =0;
	var num=0
	
	
	var ddtj_kffl_out_num=new Array();
	var ddtj_kffl_in_num=new Array();
	var ddtj_kffl_check_num=new Array();
	var ddtj_kffl_name=new Array();
	var kffl_pageNums=new Array();
	var kffl_map = new Map();
	var kffl_map_num =0;
	var kffl_num=0
	
	var rfidResult=new Array();
	var rfidType=new Array();
	
	var starttime;
	var endtime;
$(document).ready(function() {
	redata();
	chang1();
	});
function chang1(){
	$("#index").attr("class","no_select")
	$("#znfx_dahx").attr("class","no_select")
	$("#zntj_datj").attr("class","select")
	$("#smartlog1").attr("class","no_select")
	$("#ywcz_dacx").attr("class","no_select")
	$("#deviceop1").attr("class","no_select")
}
function chengStartTime(time){
	starttime=time;
	redata();
	ddtj_dafl();
	ddtj_kffl();
}
function chengEndTime(time){
	
	
	endtime=time;
	redata();
	ddtj_dafl();
	ddtj_kffl();
}
function redata(){
	Ajax.custom({
		url : config.selectStrore,
		data : {
			starttime:starttime,
			endtime:endtime,
		},// 传递参数
		type : 'GET'
	}, function(data) {
		paddata=new Array();
		namedata=new Array();
		for(var i=0;i<data.data.length;i++){
			paddata[i]=data.data[i].cou;
			namedata[i]=data.data[i].name;
		}
		padauser();
	})
	Ajax.custom({
		url : config.selectInflag,
		data : {
			starttime:starttime,
			endtime:endtime,
			type:1
		},// 传递参数
		type : 'GET'
	}, function(data) {
		paddata1=new Array();
		namedata1=new Array();
		for(var i=0;i<data.data.length;i++){
			paddata1[i]=data.data[i].cou;
			namedata1[i]=data.data[i].name;
		}
		Ajax.custom({
			url : config.selectInflag,
			data : {
				starttime:starttime,
				endtime:endtime,
				type:0
			},// 传递参数
			type : 'GET'
		}, function(data) {
			paddata2=new Array();
			for(var i=0;i<data.data.length;i++){
				paddata2[i]=data.data[i].cou;
			}

			pdsata();
		})
	});
	
	Ajax.custom({
		url : config.selectAmsArchivesByCategory,
		data : {
			starttime:starttime,
			endtime:endtime
		},// 传递参数
		type : 'GET'
	}, function(data) {
		ddtj_dafl_out_num=new Array();
		ddtj_dafl_in_num=new Array();
		ddtj_dafl_check_num=new Array();
		ddtj_dafl_name=new Array();
		num=0;
		dafl_map_num=0;
		for(var key in data.data){ 
			dafl_map=data.data[key];
			dafl_map_num++;
			for(var i=0;i<dafl_map.length;i++){
				ddtj_dafl_out_num[num]=dafl_map[i].out_nums;
				ddtj_dafl_in_num[num]=dafl_map[i].in_nums;
				ddtj_dafl_check_num[num]=dafl_map[i].check_nums;
				ddtj_dafl_name[num]=dafl_map[i].name;
				num++;
			}
			
		}
		ddtj_dafl();
	});
	
	Ajax.custom({
		url : config.selectAmsArchivesByStoreId,
		data : {
			starttime:starttime,
			endtime:endtime
		},// 传递参数
		type : 'GET'
	}, function(data) {
		ddtj_kffl_out_num=new Array();
		ddtj_kffl_in_num=new Array();
		ddtj_kffl_check_num=new Array();
		ddtj_kffl_name=new Array();
		kffl_num=0;
		kffl_map_num=0;
		for(var key in data.data){ 
			kffl_map=data.data[key];
			kffl_map_num++;
			for(var i=0;i<kffl_map.length;i++){
				ddtj_kffl_out_num[kffl_num]=kffl_map[i].out_nums;
				ddtj_kffl_in_num[kffl_num]=kffl_map[i].in_nums;
				ddtj_kffl_check_num[kffl_num]=kffl_map[i].check_nums;
				ddtj_kffl_name[kffl_num]=kffl_map[i].name;
				kffl_num++;
			}
			
		}
		ddtj_kffl();
	});
	
	Ajax.custom({
		url : config.selectRfidInfo,
		data : {
			starttime:starttime,
			endtime:endtime
		},// 传递参数
		type : 'GET'
	}, function(data) {
		for(var i=0;i<data.data.length;i++){
			var list5=new Array(0,0);
			list5[0]=data.data[i].name
			list5[1]=data.data[i].count
			rfidResult[i]=list5
		}
		rfidPie();
	});
	
	Ajax.custom({
		url : config.selectRfidResult,
		data : {
			starttime:starttime,
			endtime:endtime
		},// 传递参数
		type : 'GET'
	}, function(data) {
		for(var i=0;i<data.data.length;i++){
			var list5=new Array(0,0);
			list5[0]=data.data[i].name;
			list5[1]=data.data[i].count;
			rfidType[i]=list5
		}
		rfidPie();
		
	});
	getdata();
}
//获取数据
function getdata(){

	var background = {
            type: 'linearGradient',
            x0: 0,
            y0: 0,
            x1: 0,
            y1: 1,
            colorStops: [{ offset: 0, color: '#d2e6c9' },
                         { offset: 1, color: 'white' }]
        };
   
	//获取馆藏量统计数据
	Ajax.custom({
		url : config.getAmsSatas,
		data : {
			
		},//传递参数
		type : 'GET'
	}, function(data) {
		var inflag = new Array();var security = new Array();var archivesType = new Array();var keepyear = new Array();var wmsStore = new Array();
		if(data.data != null){
			if(data.data.inflag!=null){//在库状态
					var list=new Array(0,0);
					list[0]="在库"
						if(data.data.inflag.atstore!=null)list[1]=data.data.inflag.atstore
					inflag[0]=list
					var list1=new Array(0,0);
					list1[0]="离库"
						if(data.data.inflag.outstore!=null)list1[1]=data.data.inflag.outstore
					inflag[1]=list1
			}		
			if(data.data.security!=null){//保密等级
				var list2=new Array(0,0);
				list2[0]="普通"
					if(data.data.security.common!=null)list2[1]=data.data.security.common
				security[0]=list2
				var list3=new Array(0,0);
				list3[0]="秘密"
				if(data.data.security.cecret!=null)list3[1]=data.data.security.cecret
				security[1]=list3
				var list4=new Array(0,0);
				list4[0]="机密"
					if(list4[1]=data.data.security.confidential!=null)list4[1]=data.data.security.confidential
				security[2]=list4
				var list4=new Array(0,0);
				list4[0]="绝密"
					if(data.data.security.supersecret!=null)list4[1]=data.data.security.supersecret
				security[3]=list4
			}		
			if(data.data.archivesType.length>0){//档案类型
				for(var i = 0;i<data.data.archivesType.length;i++){
					var list5=new Array(0,0);
					list5[0]=data.data.archivesType[i].typeName
					list5[1]=data.data.archivesType[i].count
					archivesType[i]=list5
				}

			}		
			if(data.data.wmsStore.length>0){//库房占比
				for(var i = 0;i<data.data.wmsStore.length;i++){
					var lista=new Array(0,0);
					lista[0]=data.data.wmsStore[i].storeName
					lista[1]=data.data.wmsStore[i].count
					wmsStore[i]=lista
				}
				
			}		
			if(data.data.keepyear!=null){//保管年限
				var list6=new Array(0,0);
				list6[0]="永久"
					if(data.data.keepyear.permanent!=null)list6[1]=data.data.keepyear.permanent
				keepyear[0]=list6
				var list7=new Array(0,0);
				list7[0]="长期"
					if(data.data.keepyear.longTime!=null)list7[1]=data.data.keepyear.longTime
				keepyear[1]=list7
				var list8=new Array(0,0);
				list8[0]="短期"
					if(data.data.keepyear.shortTime!=null)list8[1]=data.data.keepyear.shortTime
				keepyear[2]=list8
				var list9=new Array(0,0);
				list9[0]="10年"
					if(data.data.keepyear.tenYears!=null)list9[1]=data.data.keepyear.tenYears
				keepyear[3]=list9
				var list0=new Array(0,0);
				list0[0]="30年"
					if(data.data.keepyear.thirtyYears!=null)list0[1]=data.data.keepyear.thirtyYears
				keepyear[4]=list0
			}		
		}
		//组卷方式
		$('#archivesType').jqChart({
			noDataMessage: {
                text: '无数据',
                font: '20px sans-serif'
            },
            title: { 
            	text: '档案类型图' ,
            	fillStyle: '#00ffff',  // 字体颜色 
            	 margin: 8, // 文字与图表距离 
            },/*图标上方标题*/
            legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
            	location: 'bottom',
            	font: '15px sans-serif',
            	textFillStyle: 'white',
            	visible : true,//是否显示 
            	border: {   //边框的样式 
	                strokeStyle: 'black', // 边框颜色 
	                lineWidth: 0, // 边框线粗细 
	                cornerRadius: 10, //边角弧度半径 
	                padding: 4 // 内容距离边框距离 
	            },
            },
            	animation: { duration: 1 },/*图片动画*/
                border: { strokeStyle: '#6ba851' },
                background: undefined,
                shadows: {
                    enabled: false
                },
            border: {   //边框的样式 
                strokeStyle: 'black', // 边框颜色 
                lineWidth: 0, // 边框线粗细 
                cornerRadius: 10, //边角弧度半径 
                padding: 4 // 内容距离边框距离 
            },
            series: [{ type: 'pie',
            	 fillStyles: ['#7a00ff', '#097cff', '#1ac7d5', '#c00ec2', '#0020ff'],
	                   labels: {
	                            stringFormat: '%.1f%%',
	                            valueType: 'percentage',
	                            font: '18px sans-serif',
	                            fillStyle: 'white'
	                        },
	                    data: archivesType,
	                    labelsPosition: 'inside', // inside, outside 文字位置
	                    labelsAlign: 'circle', // circle, column 数字位置
	                    labelsExtend: 10,//线长度
	                    leaderLineWidth: 0,//线粗细
	                    leaderLineStrokeStyle: 'red' //线颜色
	                  }]
        });
		//密级
		$('#security').jqChart({
			noDataMessage: {
				text: '无数据',
				font: '20px sans-serif'
			},
			title: { 
            	text: '档案密级图' ,
            	fillStyle: '#00ffff',  // 字体颜色 
            	 margin: 8, // 文字与图表距离 
            },/*图标上方标题*/
			legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
				location: 'bottom',
				font: '15px sans-serif',
				textFillStyle: 'white',
				visible : true,//是否显示
				border: {   //边框的样式 
	                strokeStyle: 'black', // 边框颜色 
	                lineWidth: 0, // 边框线粗细 
	                cornerRadius: 10, //边角弧度半径 
	                padding: 4 // 内容距离边框距离 
	            },
			},
				animation: { duration: 1 },/*图片动画*/
				border: { strokeStyle: '#6ba851' },
				background: undefined,
				shadows: {
					enabled: false
				},
				border: {   //边框的样式 
	                strokeStyle: 'black', // 边框颜色 
	                lineWidth: 0, // 边框线粗细 
	                cornerRadius: 10, //边角弧度半径 
	                padding: 4 // 内容距离边框距离 
	            },
				series: [{ type: 'pie',
					fillStyles: ['#7a00ff', '#097cff', '#1ac7d5', '#c00ec2', '#0020ff'],
					labels: {
						stringFormat: '%.1f%%',
						valueType: 'percentage',
						font: '18px sans-serif',
						fillStyle: 'white'
					},
					data: security,
					labelsPosition: 'inside', // inside, outside 文字位置
					labelsAlign: 'circle', // circle, column 数字位置
					labelsExtend: 0,//线长度
					leaderLineWidth: 2,//线粗细
					leaderLineStrokeStyle: 'red' //线颜色
				}]
		});
		console.log(security);
		//保管年限
		$('#keepyear').jqChart({
			noDataMessage: {
				text: '无数据',
				font: '20px sans-serif'
			},
			title: { 
            	text: '档案年限图' ,
            	fillStyle: '#00ffff',  // 字体颜色 
            	 margin: 8, // 文字与图表距离 
            },/*图标上方标题*/
			legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
				location: 'bottom',
				font: '15px sans-serif',
				textFillStyle: 'white',
				visible : true,//是否显示
				border: {   //边框的样式 
	                strokeStyle: 'black', // 边框颜色 
	                lineWidth: 0, // 边框线粗细 
	                cornerRadius: 10, //边角弧度半径 
	                padding: 4 // 内容距离边框距离 
	            },
			},
				animation: { duration: 1 },/*图片动画*/
				border: { strokeStyle: '#6ba851' },
				background: undefined,
				shadows: {
					enabled: false
				},
				border: {   //边框的样式 
	                strokeStyle: 'black', // 边框颜色 
	                lineWidth: 0, // 边框线粗细 
	                cornerRadius: 10, //边角弧度半径 
	                padding: 4 // 内容距离边框距离 
	            },
				series: [{ type: 'pie',
					fillStyles: ['#7a00ff', '#097cff', '#1ac7d5', '#c00ec2', '#0020ff'],
					labels: {
						stringFormat: '%.1f%%',
						valueType: 'percentage',
						font: '18px sans-serif',
						fillStyle: 'white'
					},
					data: keepyear,
					labelsPosition: 'inside', // inside, outside 文字位置
					labelsAlign: 'circle', // circle, column 数字位置
					labelsExtend: 0,//线长度
					leaderLineWidth: 2,//线粗细
					leaderLineStrokeStyle: 'red' //线颜色
				}]
		});
	}); 
}

//盘点操作统计图
function rfidPie(){
	$('#rfid_all').jqChart({
		noDataMessage: {
            text: '无数据',
            font: '20px sans-serif'
        },
        title: { 
        	text: '盘点操作统计图' ,
        	fillStyle: '#00ffff',  // 字体颜色 
        	 margin: 8, // 文字与图表距离 
        },/*图标上方标题*/
        legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
        	location: 'bottom',
        	font: '15px sans-serif',
        	textFillStyle: 'white',
        	visible : true,//是否显示 
        	border: {   //边框的样式 
                strokeStyle: 'black', // 边框颜色 
                lineWidth: 0, // 边框线粗细 
                cornerRadius: 10, //边角弧度半径 
                padding: 4 // 内容距离边框距离 
            },
        },
        	animation: { duration: 1 },/*图片动画*/
            border: { strokeStyle: '#6ba851' },
            background: undefined,
            shadows: {
                enabled: false
            },
        border: {   //边框的样式 
            strokeStyle: 'black', // 边框颜色 
            lineWidth: 0, // 边框线粗细 
            cornerRadius: 10, //边角弧度半径 
            padding: 4 // 内容距离边框距离 
        },
        series: [{ type: 'pie',
        	 fillStyles: ['#c00ec2', '#0020ff'],
                   labels: {
                            stringFormat: '%.1f%%',
                            valueType: 'percentage',
                            font: '18px sans-serif',
                            fillStyle: 'white'
                        },
                    data: rfidResult,
                    labelsPosition: 'inside', // inside, outside 文字位置
                    labelsAlign: 'circle', // circle, column 数字位置
                    labelsExtend: 10,//线长度
                    leaderLineWidth: 0,//线粗细
                    leaderLineStrokeStyle: 'red' //线颜色
                  }]
    });
	
	$('#rfid_result').jqChart({
		noDataMessage: {
            text: '无数据',
            font: '20px sans-serif'
        },
        title: { 
        	text: '盘点结果统计图' ,
        	fillStyle: '#00ffff',  // 字体颜色 
        	 margin: 8, // 文字与图表距离 
        },/*图标上方标题*/
        legend: { /*title: { text: '档案类型',font: '15px sans-serif' },*//*图例标题*/
        	location: 'bottom',
        	font: '15px sans-serif',
        	textFillStyle: 'white',
        	visible : true,//是否显示 
        	border: {   //边框的样式 
                strokeStyle: 'black', // 边框颜色 
                lineWidth: 0, // 边框线粗细 
                cornerRadius: 10, //边角弧度半径 
                padding: 4 // 内容距离边框距离 
            },
        },
        	animation: { duration: 1 },/*图片动画*/
            border: { strokeStyle: '#6ba851' },
            background: undefined,
            shadows: {
                enabled: false
            },
        border: {   //边框的样式 
            strokeStyle: 'black', // 边框颜色 
            lineWidth: 0, // 边框线粗细 
            cornerRadius: 10, //边角弧度半径 
            padding: 4 // 内容距离边框距离 
        },
        series: [{ type: 'pie',
        	 fillStyles: ['#1ac7d5', '#c00ec2', '#0020ff'],
                   labels: {
                            stringFormat: '%.1f%%',
                            valueType: 'percentage',
                            font: '18px sans-serif',
                            fillStyle: 'white'
                        },
                    data: rfidType,
                    labelsPosition: 'inside', // inside, outside 文字位置
                    labelsAlign: 'circle', // circle, column 数字位置
                    labelsExtend: 10,//线长度
                    leaderLineWidth: 0,//线粗细
                    leaderLineStrokeStyle: 'red' //线颜色
                  }]
    });
}
//盘点统计
function pdsata() {
	var myChart = echarts.init(document.getElementById("pdsata")),

	// 指定图表的配置项和数据
	option = {
		color : [ "#5969fe", "#00f8fe", "#2b8dff", "#00f9ff", "#5377fe",
				"#00fec5", "#1ec9fc", "#00bb90",

		],
		title : {
			show : true,
			text : '档案状态图',
			textStyle : {
				color : '#00ffff',
				fontStyle : '',
				fontSize : 18,
				fontFamily : 'PingFangSC-Light',

			}
		},
		toolbox: {
	        show : false,
	        feature : {
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    dataZoom: [
            {
                type: 'inside',
                	start: 0,
                	end: 4/7*100,	
            }
        ],
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			data : [ '在架', '离架'],
			textStyle : {
				color : "#03faff",// 图例文字颜色
				fontSize : 15,
			},
		},
		grid : {
			top : '15%',
			left : '1%',
			right : '4%',
			bottom : '5%',
			containLabel : true
		},
		xAxis : {
			/*name:"盘点时间",*/
			/*nameGap:"1",*/
			type : 'category',
			data : namedata1,
			axisLine : {
				lineStyle : {
					color : '#00f8fe',
				}
			},
			axisTick : {
				show : false,
			}
		},
		yAxis : {
			/*name:"盘点结果",
			nameGap:"7",*/
			type : 'value',
			axisLine : {
				lineStyle : {
					color : '#00f8fe',

				}
			},
			splitLine : {
				show : false,
			},
		},
		 axisLabel:{
   	      color:"#ffffff"
		 },
		series : [
				{
					name : '在架',
					type : 'bar',
					stack : '总量',
					barWidth:40,
					label : {
						normal : {
							show : true,
							position : 'inside'
						}
					},
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
					data : paddata1
				},
				
				{
					name : '离架',
					type : 'bar',
					stack : '总量',
					barWidth:40,
					label : {
						normal : {
							show : true,
							position : 'inside'
						}
					},
					itemStyle : {
						normal : {
							borderColor : "#bf8cff",
							borderWidth : 2,
							opacity : 0.8,
							color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
								offset : 0,
								color : 'rgba(191, 140, 255, 0.4)'
							}, {
								offset : 0.5,
								color : 'rgba(83, 119, 254, 0.4)'
							}, {
								offset : 1,
								color : 'rgba(191, 140, 255, 0.4)'
							} ])
						}
					},
					data : paddata2
				} ]
	};
	myChart.clear();
	// myChart.showLoading();
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}
//档案分类调档统计图
function ddtj_dafl() {
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('ddtj_dafl'));
    pageNums=new Array();

    for(var i=0;i<dafl_map_num;i++){
    	pageNums[i]=i;
    }
    // 指定图表的配置项和数据
         var option = {
                timeline:{
                    data:pageNums,
                    label : {
                        formatter : function(s) { return "第"+(s+1)+"页"; }
                        },
                    autoPlay : false,
                    playInterval : 1000,
                            tooltip:{formatter : function(s) {
                            	return "第"+(s.value+1)+"页"; 
                            	}}
                },
                options:[
                    {
                        title : {
                            'text':'档案分类调档统计图',
                            'textStyle' : {
                				color : 'rgb(30, 201, 252)',
                				fontStyle : '',
                				fontSize : 18,
                				fontFamily : 'PingFangSC-Light',

                			}
                        },
                        tooltip : {'trigger':'axis'},
                        legend : {
                            //x:'right',
                            'data':['档案借阅','档案归还','档案盘点'],
                            textStyle: {
                                color: '#fft'
                            }
		                        
                        },
                        calculable : true,
                        grid : {
                        	'x': 40,
                            'x2': 100,
                        	'y2':150
                        	},
                        xAxis : [{
                            'type':'category',
                            //'axisLabel':{'interval':0},
                            'data':ddtj_dafl_name.slice(0,5),
                            'axisLabel':{
                                interval:0,
                              rotate:45,
                              margin:2,
                              textStyle:{
                                  color:"rgb(30, 201, 252)"
                              }
                          }
                        }],
                        yAxis : [
                            {
                                'type':'value',
                                'name':'调档次数',
                                'max':50,
                                'axisLine' : {
                    				lineStyle : {
                    					color : 'rgb(30, 201, 252)',

                    				}
                    			}
                            }
                        ],
                        series : [
                            {
                                'name':'档案借阅','type':'bar','barGap': 0,
                                'data': ddtj_dafl_out_num.slice(0,5)
                            },
                            {
                                'name':'档案归还','type':'bar','barGap': 0,
                                'data': ddtj_dafl_in_num.slice(0,5)
                            },{
                                'name':'档案盘点','type':'bar','barGap': 0,
                                'data': ddtj_dafl_check_num.slice(0,5)
                            } ]
                    },
                    
                    {
                        series : [
                            {'data':  ddtj_dafl_out_num.slice(5,10)},
                            {'data': ddtj_dafl_in_num.slice(5,10)},
                            {'data': ddtj_dafl_check_num.slice(5,10)}
                        ],
                      xAxis:[{'data':ddtj_dafl_name.slice(5,10)}]
                    },
                    {
                        series : [
                            {'data':  ddtj_dafl_out_num.slice(10,15)},
                            {'data': ddtj_dafl_in_num.slice(10,15)},
                            {'data': ddtj_dafl_check_num.slice(10,15)}
                        ],
                      xAxis:[{'data':ddtj_dafl_name.slice(10,15)}]
                    },
                    {
                        series : [
                            {'data':  ddtj_dafl_out_num.slice(15,20)},
                            {'data': ddtj_dafl_in_num.slice(15,20)},
                            {'data': ddtj_dafl_check_num.slice(15,20)}
                        ],
                      xAxis:[{'data':ddtj_dafl_name.slice(15,20)}]
                    }
                ]
            };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

//库房分类调档统计图
function ddtj_kffl() {
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('ddtj_kffl'));
    kffl_pageNums=new Array();

    for(var i=0;i<kffl_map_num;i++){
    	kffl_pageNums[i]=i;
    }
    // 指定图表的配置项和数据
         var option = {
                timeline:{
                    data:kffl_pageNums,
                    label : {
                        formatter : function(s) { return "第"+(s+1)+"页"; }
                        },
                    autoPlay : false,
                    playInterval : 1000,
                            tooltip:{formatter : function(s) {
                            	return "第"+(s.value+1)+"页"; 
                            	}}
                },
                options:[
                    {
                        title : {
                            'text':'库房分类调档统计图',
                            'textStyle' : {
                				color : 'rgb(30, 201, 252)',
                				fontStyle : '',
                				fontSize : 18,
                				fontFamily : 'PingFangSC-Light',

                			}
                        },
                        tooltip : {'trigger':'axis'},
                        legend : {
                            //x:'right',
                            'data':['档案借阅','档案归还','档案盘点'],
                            textStyle: {
                                color: '#fft'
                            }
		                        
                        },
                        calculable : true,
                        grid : {
                        	'x': 40,
                            'x2': 100,
                        	'y2':150
                        	},
                        xAxis : [{
                            'type':'category',
                            //'axisLabel':{'interval':0},
                            'data':ddtj_kffl_name.slice(0,5),
                            'axisLabel':{
                                interval:0,
                              rotate:45,
                              margin:2,
                              textStyle:{
                                  color:"rgb(30, 201, 252)"
                              }
                          }
                        }],
                        yAxis : [
                            {
                                'type':'value',
                                'name':'调档次数',
                                'max':50,
                                'axisLine' : {
                    				lineStyle : {
                    					color : 'rgb(30, 201, 252)',

                    				}
                    			}
                            }
                        ],
                        series : [
                            {
                                'name':'档案借阅','type':'bar','barGap': 0,
                                'data': ddtj_kffl_out_num.slice(0,5)
                            },
                            {
                                'name':'档案归还','type':'bar','barGap': 0,
                                'data': ddtj_kffl_in_num.slice(0,5)
                            },{
                                'name':'档案盘点','type':'bar','barGap': 0,
                                'data': ddtj_kffl_check_num.slice(0,5)
                            } ]
                    },
                    
                    {
                        series : [
                            {'data':  ddtj_kffl_out_num.slice(5,10)},
                            {'data': ddtj_kffl_in_num.slice(5,10)},
                            {'data': ddtj_kffl_check_num.slice(5,10)}
                        ],
                      xAxis:[{'data':ddtj_kffl_name.slice(5,10)}]
                    },
                    {
                        series : [
                            {'data':  ddtj_kffl_out_num.slice(10,15)},
                            {'data': ddtj_kffl_in_num.slice(10,15)},
                            {'data': ddtj_kffl_check_num.slice(10,15)}
                        ],
                      xAxis:[{'data':ddtj_kffl_name.slice(10,15)}]
                    },
                    {
                        series : [
                            {'data':  ddtj_kffl_out_num.slice(15,20)},
                            {'data': ddtj_kffl_in_num.slice(15,20)},
                            {'data': ddtj_kffl_check_num.slice(15,20)}
                        ],
                      xAxis:[{'data':ddtj_kffl_name.slice(15,20)}]
                    }
                ]
            };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

// 盘点用户统计
function padauser() {
	
	var myChart = echarts.init(document.getElementById("padauser")),

	// 指定图表的配置项和数据
	option = {
		color : [ "#5969fe", "#00f8fe", "#2b8dff", "#00f9ff", "#5377fe",
			"#00fec5", "#1ec9fc", "#00bb90",

	],
	title : {
		show : true,
		text : '档案位置分布图',
		textStyle : {
			color : '#00ffff',
			fontStyle : '',
			fontSize : 18,
			fontFamily : 'PingFangSC-Light',

		}
	},
	dataZoom: [
        {
            type: 'inside',
            	start: 0,
            	end: 1000,	
        }
    ],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : {
			type : 'category',
			data : namedata,
			axisLine : {
				lineStyle : {
					color : '#00f8fe',
				}
			},
			axisTick : {
				show : false,
			}
		},
		yAxis : {
			type : 'value',
			boundaryGap : [ 0, 0.01 ],
			axisLine : {
				lineStyle : {
					color : '#00f8fe',

				}
			},
			splitLine : {
				show : false,
			},
		},
		axisLabel : {
			color : "#ffffff"
		},
	    series : [
	        {
	            name:'档案数量',
	            type:'bar',
	            barWidth: 30,
	            data:paddata,
	            label:{
	                normal:{
	                    show: true,
	                    position:"top",
	                    color:"#19918b",
	                    fontSize:16,
	                    lineHeight:10,
	                },
	            },
	            itemStyle : {
					normal : {
						borderColor : "#5377fe",
						borderWidth : 2,
						opacity : 0.8,
						color : new echarts.graphic.LinearGradient(0, 1, 0, 0, [ {
							offset : 0,
							color : '#000000'
						}, {
							offset : 0.5,
							color : 'rgba(83, 119, 254, 0.4)'
						}, {
							offset : 1,
							color : 'rgba(83, 119, 254, 0.4)'
						} ])
					}
				},
	        }
	    ]
	};
	myChart.clear();
	// myChart.showLoading();
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}