var type=0;
var totalCount = 0;
var currentPage = 1;
var totalPage =1; 
var storeId=0;   //选中库房编号
$(document).ready(function() {
	chang1()
	loadStore();
	//初始化库区
	loadStroreArea();
	//初始化档案类型
	selectAllAmsArchivesType()
	$(".btn_search").click(function(){
		archivesParticulars(1)
	})
		$(".archive_borrow").click(function(){
			type=0;
		archivesParticulars(1)
		currentPage = 1;
	})
		$(".archive_remand").click(function(){
			type=1;
		archivesParticulars(1)
		currentPage = 1;
	})
		$(".archive_inventory").click(function(){
			type=2;
		archivesParticulars(1)
		currentPage = 1;
	})
	$("#store").change(function(){  
		changeStore();
    });
	$("#stroreArea").change(function(){  
		changeStroreArea();
    });
});
function chang1(){
	$("#index").attr("class","no_select")
	$("#znfx_dahx").attr("class","no_select")
	$("#zntj_datj").attr("class","no_select")
	$("#smartlog1").attr("class","select")
	$("#ywcz_dacx").attr("class","no_select")
	$("#deviceop1").attr("class","no_select")
}
function selectAllAmsArchivesType(){
	//初始化档案类型
	$.ajax({
	     type:'POST',
	     url:config.selectAllAmsArchivesType,
	     dataType:'json',
	     async:false,
	     success:function(json) {
		var data=json.data;
		var	 optionstring="<option value=''>选择档案类型</option>";
		for(var i=0;i<data.length;i++){
       		optionstring = optionstring+"<option value=\"" + data[i].archivesTypeId + "\" >" + data[i].name + "</option>";
       	 }
		$("#archivesTypeselect").append(optionstring);
        	}
			
	});
}
//改变库房得到库区
function changeStore(){
    $("#lie").empty();
    stroreAreaId=0;  //选中库区编号
    deviceId=1;  //选中列
	storeId=$("#store").children('option:selected').val();//这就是selected的值
	//初始化库区
	loadStroreArea();
}
function loadStore(){
	//初始化库房
	$.ajax({
	     type:'POST',
	     url:config.getWmsStore,
	     dataType:'json',
	     async:false,
	     success:function(json) {
		var data=json.data;
		var	 optionstring="<option value=''>选择库房</option>";
		for(var i=0;i<data.length;i++){
       		optionstring = optionstring+"<option value=\"" + data[i].storeId + "\" >" + data[i].name + "</option>";
       	 }
		$("#store").append(optionstring);
		var	 SAoptionstring="<option value=''>选择库区</option>";
		$("#stroreArea").append(SAoptionstring);
        	}
			
	});
	
}//加载库区
function loadStroreArea(){
    //初始化库区
    if(isNaN(storeId) || storeId==0){
    	return;
    }
    $("#stroreArea").empty();
    $.ajax({
        type:'POST',
        url:config.getWmsStroreArea,
        data:{storeId:storeId},
        dataType:'json',
        async:false,
        success:function(json){
    		var data=json.data;
    		var	 optionstring="<option value=''>选择库区</option>";
    		for(var i=0;i<data.length;i++){
           		optionstring = optionstring+"<option value=\"" + data[i].stroreAreaId + "\" >" + data[i].name + "</option>";
           		
    		}
    		$("#stroreArea").append(optionstring);
            	}
     });
}
//加载数据
function archivesParticulars(i){
	var archiveName=$(".archive_name").val();
	var timeStart=$("#stnyr").val();
	var timeEnd=$("#ennyr").val();
	var archiveType=$("#archivesTypeselect").children('option:selected').val();
	var store=$("#store").children('option:selected').val();
	var storeArea=$("#stroreArea").children('option:selected').val(); 
	var keepyear=$(".archive_years").val();
	var security=$("#securityselect").children('option:selected').val(); 
	Ajax.custom({
		url : config.getRfidInoutList,
		data : {
			type:type,
			archiveName:archiveName,
			timeStart:timeStart,
			timeEnd:timeEnd,
			archiveType:archiveType,
			store:store,
			storeArea:storeArea,
			keepyear:keepyear,
			security:security,
			pageIndex : i
		},//传递参数
		type : 'GET'
	}, function(data) {
		if (data.data.data != null) {
			$(".archive_log_results").html(template.render("archive-data-list-tmpl", {
				list : data.data.data
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
//导出数据
//excel导出
function toexcel(){
	var archiveName=$(".archive_name").val();
	var timeStart=$("#stnyr").val();
	var timeEnd=$("#ennyr").val();
	var archiveType=$("#archivesTypeselect").children('option:selected').val();
	var store=$("#store").children('option:selected').val();
	var storeArea=$("#stroreArea").children('option:selected').val(); 
	var keepyear=$(".archive_years").val();
	var security=$("#securityselect").children('option:selected').val(); 
	window.location.href=config.getRfidtoexcel+'.do?archiveName='+archiveName+'&timeStart='+timeStart+'&timeEnd='+timeEnd+'&archiveType='+archiveType+'&store='+store+'&storeArea='+storeArea+'&keepyear='+keepyear+'&security='+security+'&type='+type
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