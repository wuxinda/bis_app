var starOpenChanal = 1;// 预览窗口数量
var openWinNum = 1;// 几宫格
var totalCount = 0;
var currentPage = 1;
var totalPage =1; 
var search =getUrlParam("key");
$(document).ready(function() {
	if(search!=null){
		keyWord=$(".datj_right_sarch_text").val(search);
		archivesParticulars(1)
		currentPage = 1;
	}
	$(".datj_right_sarch_submit").click(function(){
		archivesParticulars(1)
		currentPage = 1;
	})
	$(".leftsidebar ul li:nth-child(1)").addClass("datanavclickstyle");
	chang1()

});
function getUrlParam(name) {  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象  
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数  
    if (r != null) return unescape(r[2]); return null; // 返回参数值  
}  
function chang1(){
	$("#index").attr("class","no_select")
	$("#znfx_dahx").attr("class","no_select")
	$("#zntj_datj").attr("class","no_select")
	$("#smartlog1").attr("class","no_select")
	$("#ywcz_dacx").attr("class","select")
	$("#deviceop1").attr("class","no_select")
}
function closeVideo(stroreId,stroteAreaId,storeColumn,storeLr){
		$(".ChangeVedio").css({
	 		"display":"none",
	 	});
		$("#closeVideo").css({
	 		"display":"none",
	 	});
	openIms(stroreId,stroteAreaId,storeColumn,0,storeLr);
}
function openvideo(stroreId,stroteAreaId,storeColumn,storeLr){
	 	$(".ChangeVedio").css({
	 		"display":"block",
	 	});
	 	$("#closeVideo").css({
	 		"display":"block",
	 	});
	 	clickLogin();
	 	cilckVideo123();
	openIms(stroreId,stroteAreaId,storeColumn,1,storeLr);
}
function openIms(stroreId,stroteAreaId,storeColumn,type,storeLr){
	Ajax.custom({
		url : config.selectDeviceForArchive,
		data : {
			stroreId:stroreId,
			stroteAreaId:stroteAreaId,
			storeColumn:storeColumn
		},//传递参数
		type : 'post'
	}, function(data) {
		if(type=0){
			stop(data.data.deviceId);
			closeshelve(data.data.deviceId);
		}else{
			if(storeLr=='r'){
				openleft(data.data.deviceId)
			}else if(storeLr=='l'){
				openright(data.data.deviceId)
			}
		}
	})
}
function particulars(){
	if($(".archives_particulars_single").css("display")=="block"){
		$(".archives_particulars_single").css({
	 		"display":"none"
	 	});
	}else{
	 	$(".archives_particulars_single").css({
	 		"display":"block"
	 		
	 	});
	} 	
}
function change(obj) {
	var arr = $(".leftsidebar ul li").removeClass("datanavclickstyle");
	$(obj).addClass("datanavclickstyle");
}
function archivesParticulars(i){
	var keyWord=$(".datj_right_sarch_text").val();
	Ajax.custom({
		url : config.searchArchives,
		data : {
			data:keyWord,
			page:i
		},//传递参数
		type : 'post'
	}, function(data) {
		console.log(data.data.resultTime);
		if (data.data.list != null) {
			$(".datj_right_top_table").html(template.render("archives-data-list-tmpl", {
				list : data.data.list.data
			}));
			totalCount = data.data.list.count;
			if (totalCount % 3 == 0) {
				totalPage = totalCount / 3;
			} else {
				totalPage = parseInt(totalCount / 3) + 1;
			}
			$("#totlePageCount").html(
					"共计" + totalPage + "页&nbsp " + totalCount + "条数据");
			$("#nowpage").val(currentPage);
			$("#resultCount").html(totalCount);
			$("#resultTime").html(data.data.resultTime);
			
		}
	});
}
function cilckVideo123(){
	starOpenChanal = 1;
	openWinNum = 1;
	changeWndNum(openWinNum);
	plStartRealPlay(starOpenChanal);
}
function closePage(){
			$(".archives_particulars_single").css({
		 		"display":"none",
		 	});
}
function change(obj) {
	var arr = $(".leftsidebar ul li").removeClass("datanavclickstyle");
	$(obj).addClass("datanavclickstyle");
}
//回车跳转页面
function enterJump(){
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
	$("#nowpage").val(curpage);
	currentPage = curpage;
	archivesParticulars(curpage)
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
//合拢密集架
function closeshelve(deviceId){
	if(deviceId== 0){
		alert('请选择你要合拢的密集架!');
		return;
	}
	if (confirm("确认合拢吗？")) {
		 Ajax.custom({
				url : config.operateDevice,
				data : {
					"userId":1,// 用户id
			        "deviceId":deviceId,// 设备id
			        "categoryId":5,// 设备类型
			        "actionType":7,// 操作类型
			        "isSelect":0// 全选表示
				},// 传递参数
				type : 'GET'
			}, function(data) {
				
			})
	}
}

//左打开
function openleft(deviceId){
	if(deviceId == 0){
		alert('请选择你要打开的密集架!');
		return;
	}
	 Ajax.custom({
			url : config.operateDevice,
			data : {
				"userId":1,// 用户id
		        "deviceId":deviceId,// 设备id
		        "categoryId":5,// 设备类型
		        "actionType":4,// 操作类型
		        "isSelect":0// 全选表示
			},// 传递参数
			type : 'GET'
		}, function(data) {
			
		})

}

//右打开
function openright(deviceId){
	if(deviceId == 0){
		alert('请选择你要打开的密集架!');
		return;
	}
	Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":deviceId,// 设备id
	        "categoryId":5,// 设备类型
	        "actionType":5,// 操作类型
	        "isSelect":0// 全选表示
		},// 传递参数
		type : 'GET'
	}, function(data) {
		
	})
}

//停止
function stop(deviceId){
	if(deviceId == 0){
		alert('请选择你要停止的密集架!');
		return;
	}
	Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":deviceId,// 设备id
	        "categoryId":5,// 设备类型
	        "actionType":3,// 操作类型
	        "isSelect":0// 全选表示
		},// 传递参数
		type : 'GET'
	}, function(data) {
		
	})
}
