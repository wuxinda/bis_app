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
	// 获取库房温湿度数据
	Ajax.custom({
		url : config.getNewHumiture,
		data : {

		},// 传递参数
		type : 'GET'
	}, function(data) {
		if (data.data != null) {
			$("#newHumiture").html(template.render("humiture-data", {
				humiture : data.data
			}));
		}
	});
	initDay();

});
var defaulttime = 24;
var xlxtime;
$(" article .temp .rt_top a:eq(0)").click(function(){ 
	defaulttime=24;
	 initDay();
});

$(" article .temp .rt_top a:eq(1)").click(function(){ 
	defaulttime=31;
	 initMonth();	
});

$(" article .temp .rt_top a:eq(2)").click(function(){ 
	defaulttime=12;
	 initYear();		
});
function initDay(){
 	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;	
	var d=date.getDate();
	var ss =y+"-"+m+"-"+d;
	$("#nyr").val(ss);
	showDayInfo(ss);
}

function initMonth(){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;	
	var ss =y+"-"+m;
	$("#ny").val(ss);
	showDayInfo(ss);
}

function initYear(){
 	var date = new Date();
	var y = date.getFullYear();
	var ss =y;
	$("#n").val(ss);
	showDayInfo(ss);
}
//显示日报表信息
function showDayInfo(ss){
	xlxtime=ss;
		// 获取温湿度报表
		Ajax.custom({
			url : config.getHumitureReport,
			data : {
				"time":ss,
			},// 传递参数
			type : 'GET'
		}, function(data) {
			if (data.data != null) {
				$("#humitureTable").html(template.render("humiture-table", {
					HumitureData : data.data,Defaulttime:defaulttime
				}));
			}
		});
}
//导出方法
function toexcel(){
	location.href=config.getHumiturexlsReport+'.do?time='+xlxtime+''
}