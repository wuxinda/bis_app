var type=1;
var time;
var storeId
$(document).ready(function() {
	loadStore();
	initNyr()
	$("#day").click(function(){
		type=1;
		initDay();
	})
	$("#mon").click(function(){
		type=2;
		 initMonth();
	})
	$("#year").click(function(){
		type=3;
		initYear();
	})
	$("#device").change(function(){ 
		changeStore();
    });
});
//加载温湿度
function showDayInfo(ss){
	time=ss;
	Ajax.custom({
		url : config.getHumByTime,
		data : {
			type:type,
			time:time,
			store:storeId
		},//传递参数
		type : 'POST'
	}, function(data) {
		if (data.data != null) {
			$("#humitureTable").html(template.render("humitureTable-data-list-tmpl", {
				storeName:$("#device").children('option:selected').html(),
				list : data.data
			}));
		}
	});
}
//改变库房得到库区
function changeStore(){
	storeId=$("#device").children('option:selected').val();//这就是selected的值
	showDayInfo(time)
}
//加载库房
function loadStore(){
	$.ajax({
	     type:'POST',
	     url:config.getWmsStore,
	     dataType:'json',
	     async:false,
	     success:function(json) {
		var data=json.data;
		var	 optionstring="<option>选择库房</option>";
		for(var i=0;i<data.length;i++){
       		optionstring = optionstring+"<option value=\"" + data[i].storeId + "\" >" + data[i].name + "</option>";
       	 }
		$("#device").append(optionstring);
        	}
	});
}

function initDay(){
	 	var date = new Date();
		var y = date.getFullYear();
		var m = date.getMonth() + 1;	
		var d=date.getDate();
		var ss =y+"-"+m+"-"+d;
		$("#nyr").val(ss);
		time=ss;
		showDayInfo()
}

function initMonth(){
		var date = new Date();
		var y = date.getFullYear();
		var m = date.getMonth() + 1;	
		var ss =y+"-"+m;
		$("#ny").val(ss);
		time=ss;
		showDayInfo()
}

function initYear(){
	 	var date = new Date();
		var y = date.getFullYear();
		var ss =y;
		$("#n").val(ss);
		time=ss;
		showDayInfo()
}
function initNyr(){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;	
	var d=date.getDate();
	var ss =y+"-"+m+"-"+d;
	$("#nyr").val(ss);
	time=ss;
	showDayInfo();
};
//导出方法
function toexcel(){
	location.href=config.getHumiturexlsReport+'.do?time='+time+''
}