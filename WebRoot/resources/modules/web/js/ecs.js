$(document).ready(function() {
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
	initNy();
});

var pieData = new Array();// 分项能耗
var columnNow = new Array();// 查询年月
var columnHb = new Array();// 同年上一月环比
var storeName = new Array();// 库房名称


$("article .energy nav a:eq(0)").click(function() {
	initNy();
});

$("article .energy nav a:eq(1)").click(function() {
	initYear();
});
function showchange(el){
	if(el==1){
		initNhInfo($("#SearchNy").val());
	}
	if(el==2){
		initYearInfo($("#SearchYear").val());
	}
}
// 初始化年月
function initNy() {
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var ss = y + "-" + m;
	$("#SearchNy").val(ss);
	initNhInfo($("#SearchNy").val());
}

// 初始化年
function initYear() {
	var date = new Date();
	var y = date.getFullYear();
	var ss = y;
	$("#SearchYear").val(ss);
	initYearInfo($("#SearchYear").val());
}

function initChart() {
	$('#jqChartPie').jqChart(
			{
				title : {
					text : '各分项能耗量饼状图'
				},
				legend : {
					location : 'right'
				},
				animation : {
					duration : 1
				},

				noDataMessage : {
					text : '查询无数据',
					font : '20px sans-serif'
				},

				tooltips : { // 提示数据
					disabled : false, // 是否不引用
					type : 'normal', // 类型 正常或共享
					background : 'white', // 背景颜色
					borderColor : 'auto', // 边框颜色
				},
				series : [ {
					type : 'pie',
					fillStyles : [ '#418CF0', '#FCB441', '#E0400A', '#056492',
							'#BFBFBF', '#1A3B69', '#FFE382' ],
					labels : {
						stringFormat : '%.1f%%',
						valueType : 'percentage',
						font : '15px sans-serif',
						fillStyle : 'red'
					},

					explodedRadius : 10,
					explodedSlices : [ 5 ],
					data : pieData,
					labelsPosition : 'outside', // inside, outside
					labelsAlign : 'circle', // circle, column
					labelsExtend : 20,
					leaderLineWidth : 1,
					leaderLineStrokeStyle : 'red'

				} ]
			});
}

// 年月柱状图
function initYmColumn() {
	$('#jqChartColumn').jqChart({
		title : {
			text : '库房能耗量柱状图'
		},
		legend : {
			location : 'top'
		},
		animation : {
			duration : 1
		},
		noDataMessage : {
			text : '查询无数据',
			font : '20px sans-serif'
		},

		axes : [ {
			type : 'category',
			location : 'bottom',
			categories : storeName
		}, {
			location : 'left',
			minimum : 100,
			interval : 100
		} ],
		series : [ {
			type : 'column',
			title : "实际能耗",
			data : columnNow
		}, {
			type : 'column',
			title : "目标能耗",
			data : columnHb
		}]
	});
}


// 默认查询本年月数据
function initNhInfo(ny) {
	$(".energy ul p").remove();
	//var ny = $("#SearchNy").val();
	var words = ny.split("-");
	Ajax.custom({
		url : config.getTotalData,
		data : {
			"searchYear" : words[0],
			"searchMonth" : words[1]
		},// 传递参数
		type : 'GET'
	}, function(data) {
		var a;
		var mb;
		var b;
		var c;
		var hbl
		var tb;
		var mbwcl;
		if (data.data[0] != null || data.data[0] != undefined) {
			a = data.data[0][1];
			mb = data.data[1][1];
			mbwcl = (a / mb * 100).toFixed(2);
			if(a=="null"||a==undefined){
				a="无数据";
				mbwcl="无数据";
			}
			if(mb=="null"||mb==undefined){
				mb="无数据";
				mbwcl="无数据";
			}
			$(".energy ul li:eq(0)").append(
					"<p onclick='go2ecs()'>" + words[1] + "月库房实际耗电量<em>" + a
							+ " <span>kWh</span> </em></p>");
			$(".energy ul li:eq(1)").append(
					"<p onclick='go2ecs()'>" + words[1] + "月库房目标耗电量 <em>" + mb
							+ " <span>kWh</span> </em></p>");
			$(".energy ul li:eq(2)").append(
					"<p onclick='go2ecs()'>目标完成<em>" + mbwcl
							+ " <span>%</span> </em></p> ");
		} else {
			$(".energy ul li").append("<p>查询无数据</p> ");
			return;
		}

	});
	pieData.length = 0;// 分项能耗
	Ajax.custom({
		url : config.getTypeEcs,
		data : {
			"searchYear" : words[0],
			"searchMonth" : words[1]
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if (data.data != null || data.data != undefined) {
			for (var i = 0; i < data.data.length; i++) {
				pieData[i] = new Array();
				pieData[i][0] = data.data[i].deviceName;
				if(pieData[i][0]==null||pieData[i][0]==""){
					pieData[i][0] ="其他";	
				}
				pieData[i][1] = data.data[i].actualEc;

			}
		}
		initChart();
	});
	// 柱状图
	columnNow.length = 0;
	columnHb.length = 0;
	storeName.length = 0;
	Ajax.custom({
		url : config.getStoreEcs,
		data : {
			"searchYear" : words[0],
			"searchMonth" : words[1]
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if(data.data!=null&&data.data!=undefined){
			for(var i = 0; i < data.data.length; i++){
				storeName[i] = data.data[i].storeName;
				if(storeName[i]==null||storeName[i]==""){
					storeName[i] ="其他";	
				}
				columnNow[i] = data.data[i].actualEc;
				columnHb[i] = data.data[i].targetEc;
			}
		}
		initYmColumn();
		
	});
};
//默认查询本年数据
function initYearInfo(ny) {
	$(".energy ul p").remove();
	//var ny = $("#SearchNy").val();
	var words = ny;
	Ajax.custom({
		url : config.getTotalData,
		data : {
			"searchYear" : words,
		},// 传递参数
		type : 'GET'
	}, function(data) {
		var a;
		var mb;
		var b;
		var c;
		var hbl
		var tb;
		var mbwcl;
		if (data.data[0] != null || data.data[0] != undefined) {
			a = data.data[0][1];
			mb = data.data[1][1];
			mbwcl = (a / mb * 100).toFixed(2);
			if(a=="null"||a==undefined){
				a="无数据";
				mbwcl="无数据";
			}
			if(mb=="null"||mb==undefined){
				mb="无数据";
				mbwcl="无数据";
			}
			$(".energy ul li:eq(0)").append(
					"<p onclick='go2ecs()'>" + words + "年库房实际耗电量<em>" + a
							+ " <span>kWh</span> </em></p>");
			$(".energy ul li:eq(1)").append(
					"<p onclick='go2ecs()'>" + words + "年库房目标耗电量 <em>" + mb
							+ " <span>kWh</span> </em></p>");
			$(".energy ul li:eq(2)").append(
					"<p onclick='go2ecs()'>目标完成<em>" + mbwcl
							+ " <span>%</span> </em></p> ");
		} else {
			$(".energy ul li").append("<p>查询无数据</p> ");
			return;
		}

	});
	pieData.length = 0;// 分项能耗
	Ajax.custom({
		url : config.getTypeEcs,
		data : {
			"searchYear" : words,
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if (data.data != null || data.data != undefined) {
			for (var i = 0; i < data.data.length; i++) {
				pieData[i] = new Array();
				pieData[i][0] = data.data[i].deviceName;
				if(pieData[i][0]==null||pieData[i][0]==""){
					pieData[i][0] ="其他";	
				}
				pieData[i][1] = data.data[i].actualEc;

			}
		}
		initChart();
	});
	// 柱状图
	columnNow.length = 0;
	columnHb.length = 0;
	storeName.length = 0;
	Ajax.custom({
		url : config.getStoreEcs,
		data : {
			"searchYear" : words,
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if(data.data!=null&&data.data!=undefined){
			for(var i = 0; i < data.data.length; i++){
				storeName[i] = data.data[i].storeName;
				if(storeName[i]==null||storeName[i]==""){
					storeName[i] ="其他";	
				}
				columnNow[i] = data.data[i].actualEc;
				columnHb[i] = data.data[i].targetEc;
			}
		}
		initYmColumn();
		
	});
};
