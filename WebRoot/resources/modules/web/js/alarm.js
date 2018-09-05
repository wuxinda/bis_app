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
	// 获取报警列表
	Ajax.custom({
		url : config.searchList,
		data : {
			"status":0,
			"pageIndex":1,
			"pageSize":30
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if(data.data!=null){
			for(var i=0;i<data.data.data.length;i++){
				data.data.data[i].ctime=new Date(data.data.data[i].ctime).format("yyyy-MM-dd hh:mm:ss");
			}
			$("#alarmList").html(template.render("alarm-data", {
				list : data.data.data
			}));
			//添加处理按钮时间
			 $("#alarmList tr td:nth-child(6)").click(function() {
				 handle($(this).parent("tr").find("td").eq(0).text())
			 });
		}
		
	});
	  
	initDay();
	$(" article .alarm a:eq(0)").click(function(){ 
		 initDay();
	});

	$(" article .alarm a:eq(1)").click(function(){ 
		 initMonth();	
	});

	$(" article .alarm  a:eq(2)").click(function(){ 
		 initYear();		
	});
});
function getAlarmSata(type,date){
		Ajax.custom({
			url : config.getAlarmSata,
			data : {
				"type":type,
				"date":date
			},// 传递参数
			type : 'GET'
		}, function(data) {
			var status = new Array();
			var type = new Array();
			if (data.data != null) {
				if (data.data.status != null) {
					for (var i = 0; i < data.data.status.length; i++) {
						var list1 = new Array(0, 0);
						list1[0] = data.data.status[i].name
						list1[1] = data.data.status[i].count
						status[i] = list1
					}
				}
				if (data.data.type != null) {
					for (var i = 0; i < data.data.type.length; i++) {
						var list1 = new Array(0, 0);
						list1[0] = data.data.type[i].alarmType
						list1[1] = data.data.type[i].count
						type[i] = list1
					}
				}
			}
			// 状态
			$('#status').jqChart({
				noDataMessage : {
					text : '无报警数据',
					font : '20px sans-serif'
				},
				/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
				legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
					location : 'right',
					font : '15px sans-serif',
				},
				animation : {
					duration : 1
				},/* 图片动画 */
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				shadows : {
					enabled : true
				},
				series : [ {
					type : 'pie',
					labels : {
						stringFormat : '%.1f%%',
						valueType : 'percentage',
						font : '18px sans-serif',
						fillStyle : 'black'
					},
					data : status,
					labelsPosition : 'outside', // inside, outside 文字位置
					labelsAlign : 'circle', // circle, column 数字位置
					labelsExtend : 10,// 线长度
					leaderLineWidth : 2,// 线粗细
					leaderLineStrokeStyle : 'red' // 线颜色
				} ]
			});
			// 类型
			$('#type').jqChart({
				noDataMessage : {
					text : '无报警数据',
					font : '20px sans-serif'
				},
				/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
				legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
					location : 'bottom',
					font : '10px sans-serif',
				},
				animation : {
					duration : 1
				},/* 图片动画 */
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				shadows : {
					enabled : true
				},
				series : [ {
					type : 'pie',
					labels : {
						stringFormat : '%.1f%%',
						valueType : 'percentage',
						font : '18px sans-serif',
						fillStyle : 'black'
					},
					data : type,
					labelsPosition : 'outside', // inside, outside 文字位置
					labelsAlign : 'circle', // circle, column 数字位置
					labelsExtend : 10,// 线长度
					leaderLineWidth : 2,// 线粗细
					leaderLineStrokeStyle : 'red' // 线颜色
				} ]
			});
			
		});
}
function initDay(type){
 	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;	
	var d=date.getDate();
	var d=date.getDate();
	 if (m >= 1 && m <= 9) {
        m = "0" + m;
    }
    if (d >= 0 && d <= 9) {
        d = "0" + d;
    }
	var ss =y+"-"+m+"-"+d;
	if(type!=null){
		ss=$("#nyr").val();
	}
	$("#nyr").val(ss);
	getAlarmSata(3,ss);
}

function initMonth(type){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	 if (m >= 1 && m <= 9) {
	        m = "0" + m;
	    }
	var ss =y+"-"+m;
	if(type!=null){
		ss=$("#ny").val();
	}
	$("#ny").val(ss);
	getAlarmSata(2,ss);
}

function initYear(type){
 	var date = new Date();
	var y = date.getFullYear();
	var ss =y;
	if(type!=null){
		ss=$("#n").val();
	}
	$("#n").val(ss);
	getAlarmSata(1,ss);
}

 // 处理报警
function handle(id){
	var statu = confirm("确认处理吗?");
    if(!statu){
        return false;
    }
    Ajax.custom({
		url : config.handleAlarm,
		data : {
			"alarmId":id,
			"userId":1
		},// 传递参数
		type : 'GET'
	}, function(data) {
		window.location.reload();
	});

} var background = {
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
	// 获取报警列表
	Ajax.custom({
		url : config.searchList,
		data : {
			"status":0,
			"pageIndex":1,
			"pageSize":30
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if(data.data!=null){
			for(var i=0;i<data.data.data.length;i++){
				data.data.data[i].ctime=new Date(data.data.data[i].ctime).format("yyyy-MM-dd hh:mm:ss");
			}
			$("#alarmList").html(template.render("alarm-data", {
				list : data.data.data
			}));
			//添加处理按钮时间
			 $("#alarmList tr td:nth-child(6)").click(function() {
				 handle($(this).parent("tr").find("td").eq(0).text())
			 });
		}
		
	});
	  
	initDay();
	$(" article .alarm a:eq(0)").click(function(){ 
		 initDay();
	});

	$(" article .alarm a:eq(1)").click(function(){ 
		 initMonth();	
	});

	$(" article .alarm  a:eq(2)").click(function(){ 
		 initYear();		
	});
});
function getAlarmSata(type,date){
		Ajax.custom({
			url : config.getAlarmSata,
			data : {
				"type":type,
				"date":date
			},// 传递参数
			type : 'GET'
		}, function(data) {
			var status = new Array();
			var type = new Array();
			if (data.data != null) {
				if (data.data.status != null) {
					for (var i = 0; i < data.data.status.length; i++) {
						var list1 = new Array(0, 0);
						list1[0] = data.data.status[i].name
						list1[1] = data.data.status[i].count
						status[i] = list1
					}
				}
				if (data.data.type != null) {
					for (var i = 0; i < data.data.type.length; i++) {
						var list1 = new Array(0, 0);
						list1[0] = data.data.type[i].alarmType
						list1[1] = data.data.type[i].count
						type[i] = list1
					}
				}
			}
			// 状态
			$('#status').jqChart({
				noDataMessage : {
					text : '无报警数据',
					font : '20px sans-serif'
				},
				/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
				legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
					location : 'right',
					font : '15px sans-serif',
				},
				animation : {
					duration : 1
				},/* 图片动画 */
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				shadows : {
					enabled : true
				},
				series : [ {
					type : 'pie',
					labels : {
						stringFormat : '%.1f%%',
						valueType : 'percentage',
						font : '18px sans-serif',
						fillStyle : 'black'
					},
					data : status,
					labelsPosition : 'outside', // inside, outside 文字位置
					labelsAlign : 'circle', // circle, column 数字位置
					labelsExtend : 10,// 线长度
					leaderLineWidth : 2,// 线粗细
					leaderLineStrokeStyle : 'red' // 线颜色
				} ]
			});
			// 类型
			$('#type').jqChart({
				noDataMessage : {
					text : '无报警数据',
					font : '20px sans-serif'
				},
				/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
				legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
					location : 'bottom',
					font : '10px sans-serif',
				},
				animation : {
					duration : 1
				},/* 图片动画 */
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				shadows : {
					enabled : true
				},
				series : [ {
					type : 'pie',
					labels : {
						stringFormat : '%.1f%%',
						valueType : 'percentage',
						font : '18px sans-serif',
						fillStyle : 'black'
					},
					data : type,
					labelsPosition : 'outside', // inside, outside 文字位置
					labelsAlign : 'circle', // circle, column 数字位置
					labelsExtend : 10,// 线长度
					leaderLineWidth : 2,// 线粗细
					leaderLineStrokeStyle : 'red' // 线颜色
				} ]
			});
			
		});
}
function initDay(type){
 	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;	
	var d=date.getDate();
	var d=date.getDate();
	 if (m >= 1 && m <= 9) {
        m = "0" + m;
    }
    if (d >= 0 && d <= 9) {
        d = "0" + d;
    }
	var ss =y+"-"+m+"-"+d;
	if(type!=null){
		ss=$("#nyr").val();
	}
	$("#nyr").val(ss);
	getAlarmSata(3,ss);
}

function initMonth(type){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	 if (m >= 1 && m <= 9) {
	        m = "0" + m;
	    }
	var ss =y+"-"+m;
	if(type!=null){
		ss=$("#ny").val();
	}
	$("#ny").val(ss);
	getAlarmSata(2,ss);
}

function initYear(type){
 	var date = new Date();
	var y = date.getFullYear();
	var ss =y;
	if(type!=null){
		ss=$("#n").val();
	}
	$("#n").val(ss);
	getAlarmSata(1,ss);
}

 // 处理报警
function handle(id){
	var statu = confirm("确认处理吗?");
    if(!statu){
        return false;
    }
    Ajax.custom({
		url : config.handleAlarm,
		data : {
			"alarmId":id,
			"userId":1
		},// 传递参数
		type : 'GET'
	}, function(data) {
		window.location.reload();
	});

} var background = {
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
	// 获取报警列表
	Ajax.custom({
		url : config.searchList,
		data : {
			"status":0,
			"pageIndex":1,
			"pageSize":30
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if(data.data!=null){
			for(var i=0;i<data.data.data.length;i++){
				data.data.data[i].ctime=new Date(data.data.data[i].ctime).format("yyyy-MM-dd hh:mm:ss");
			}
			$("#alarmList").html(template.render("alarm-data", {
				list : data.data.data
			}));
			//添加处理按钮时间
			 $("#alarmList tr td:nth-child(6)").click(function() {
				 handle($(this).parent("tr").find("td").eq(0).text())
			 });
		}
		
	});
	  
	initDay();
	$(" article .alarm a:eq(0)").click(function(){ 
		 initDay();
	});

	$(" article .alarm a:eq(1)").click(function(){ 
		 initMonth();	
	});

	$(" article .alarm  a:eq(2)").click(function(){ 
		 initYear();		
	});
});
function getAlarmSata(type,date){
		Ajax.custom({
			url : config.getAlarmSata,
			data : {
				"type":type,
				"date":date
			},// 传递参数
			type : 'GET'
		}, function(data) {
			var status = new Array();
			var type = new Array();
			if (data.data != null) {
				if (data.data.status != null) {
					for (var i = 0; i < data.data.status.length; i++) {
						var list1 = new Array(0, 0);
						list1[0] = data.data.status[i].name
						list1[1] = data.data.status[i].count
						status[i] = list1
					}
				}
				if (data.data.type != null) {
					for (var i = 0; i < data.data.type.length; i++) {
						var list1 = new Array(0, 0);
						list1[0] = data.data.type[i].alarmType
						list1[1] = data.data.type[i].count
						type[i] = list1
					}
				}
			}
			// 状态
			$('#status').jqChart({
				noDataMessage : {
					text : '无报警数据',
					font : '20px sans-serif'
				},
				/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
				legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
					location : 'right',
					font : '15px sans-serif',
				},
				animation : {
					duration : 1
				},/* 图片动画 */
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				shadows : {
					enabled : true
				},
				series : [ {
					type : 'pie',
					labels : {
						stringFormat : '%.1f%%',
						valueType : 'percentage',
						font : '18px sans-serif',
						fillStyle : 'black'
					},
					data : status,
					labelsPosition : 'outside', // inside, outside 文字位置
					labelsAlign : 'circle', // circle, column 数字位置
					labelsExtend : 10,// 线长度
					leaderLineWidth : 2,// 线粗细
					leaderLineStrokeStyle : 'red' // 线颜色
				} ]
			});
			// 类型
			$('#type').jqChart({
				noDataMessage : {
					text : '无报警数据',
					font : '20px sans-serif'
				},
				/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
				legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
					location : 'bottom',
					font : '10px sans-serif',
				},
				animation : {
					duration : 1
				},/* 图片动画 */
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				shadows : {
					enabled : true
				},
				series : [ {
					type : 'pie',
					labels : {
						stringFormat : '%.1f%%',
						valueType : 'percentage',
						font : '18px sans-serif',
						fillStyle : 'black'
					},
					data : type,
					labelsPosition : 'outside', // inside, outside 文字位置
					labelsAlign : 'circle', // circle, column 数字位置
					labelsExtend : 10,// 线长度
					leaderLineWidth : 2,// 线粗细
					leaderLineStrokeStyle : 'red' // 线颜色
				} ]
			});
			
		});
}
function initDay(type){
 	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;	
	var d=date.getDate();
	var d=date.getDate();
	 if (m >= 1 && m <= 9) {
        m = "0" + m;
    }
    if (d >= 0 && d <= 9) {
        d = "0" + d;
    }
	var ss =y+"-"+m+"-"+d;
	if(type!=null){
		ss=$("#nyr").val();
	}
	$("#nyr").val(ss);
	getAlarmSata(3,ss);
}

function initMonth(type){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	 if (m >= 1 && m <= 9) {
	        m = "0" + m;
	    }
	var ss =y+"-"+m;
	if(type!=null){
		ss=$("#ny").val();
	}
	$("#ny").val(ss);
	getAlarmSata(2,ss);
}

function initYear(type){
 	var date = new Date();
	var y = date.getFullYear();
	var ss =y;
	if(type!=null){
		ss=$("#n").val();
	}
	$("#n").val(ss);
	getAlarmSata(1,ss);
}

 // 处理报警
function handle(id){
	var statu = confirm("确认处理吗?");
    if(!statu){
        return false;
    }
    Ajax.custom({
		url : config.handleAlarm,
		data : {
			"alarmId":id,
			"userId":1
		},// 传递参数
		type : 'GET'
	}, function(data) {
		window.location.reload();
	});

} 