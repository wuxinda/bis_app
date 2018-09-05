var data={page:1}
var totalCount = 0;
var currentPage = 1;
var totalPage =1; 
$(document).ready(function() {
	archivesParticulars()
	loadUserName()
	loadTime()
	loadDevice()
	$("#userName").change(function(){  
		archivesParticulars()
	});  
	$("#device").change(function(){  
		archivesParticulars()
	}); 
});
function archivesParticulars(){
	var userName=$("#userName").children('option:selected').val();//这就是selected的值
	var device=$("#device").children('option:selected').val();
	Ajax.custom({
		url : config.searchRfidInout,
		data : {
			userName:userName,
			device:device
		},//传递参数
		type : 'GET'
	}, function(data) {
		if (data.data.list != null) {
			$(".device_operate_list").html(template.render("inout-data-list-tmpl", {
				list : data.data.list
			}));
			totalCount = data.data.count;
			if (totalCount % 9 == 0) {
				totalPage = totalCount / 9;
			} else {
				totalPage = parseInt(totalCount / 9) + 1;
			}
			$("#totlePageCount").html(
					"共计" + totalPage + "页&nbsp " + totalCount + "条数据");
			$("#nowpage").val(currentPage);
		}
	});
}
//加载用户名
function loadUserName(){
	$.ajax({
	     type:'POST',
	     url:config.getAdminUserName,
	     dataType:'json',
	     async:false,
	     success:function(json) {
		var data=json.data;
		var	 optionstring="<option>选择操作人</option>";
		for(var i=0;i<data.length;i++){
       		optionstring = optionstring+"<option value=\"" + data[i].applyUser + "\" >" + data[i].applyUser + "</option>";
       	 }
		$("#userName").append(optionstring);
        	}
	});
}
//加载设备列表
function loadDevice(){
	$.ajax({
	     type:'POST',
	     url:config.getDevice,
	     dataType:'json',
	     async:false,
	     success:function(json) {
		var data=json.data;
		var	 optionstring="<option>选择设备</option>";
		for(var i=0;i<data.length;i++){
       		optionstring = optionstring+"<option value=\"" + data[i].deviceId + "\" >" + data[i].name + "</option>";
       	 }
			$("#device").append(optionstring);
        	}
	});
}
//加载操作日期
function loadTime(){
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
		$("#time").append(optionstring);
        	}
	});
}
//用户名
function changeUserName(){
	var userName=$("#userName").children('option:selected').val();//这就是selected的值
	var device=$("#device").children('option:selected').val();
}
//改变设备
function changeDevice(){
	userName=$("#userName").children('option:selected').val();//这就是selected的值
	device=$("#device").children('option:selected').val();
}
//上一页
function prePageAction() {
	var curpage = parseInt($("#nowpage").val());
	if (isNaN(curpage)) {
		alert("请输入数字");
		return;
	}
	if (curpage <= 1) {
		return;
	}
	if (curpage > totalPage) {
		// alert("超过总页数");
		return;
	}
	$("#nowpage").val(curpage - 1);
	currentPage = curpage - 1;
	archivesParticulars(currentPage);
}

// 下一页
function nextPageAction() {
	var curpage = parseInt($("#nowpage").val());
	if (isNaN(curpage)) {
		alert("请输入数字");
		return;
	}
	if (curpage >= totalPage || curpage < 0) {
		// alert("超过总页数或页数为负");
		return;
	}
	$("#nowpage").val(curpage + 1);
	currentPage = curpage + 1;
	archivesParticulars(currentPage);
}

// 首页
function sy() {
	if (currentPage == 1) {
		return;
	} else {
		currentPage = 1;
		$("#nowpage").val(1);
		archivesParticulars(1);
	}
}
// 尾页
function wy() {
	if (currentPage == totalPage) {
		return;
	} else {
		currentPage = totalPage;
		$("#nowpage").val(totalPage);
		archivesParticulars(totalPage);
	}
}