var InAddress=new Array(7);//用于存放存址 ----》房-区-密集架-序列号-面层格
$(document).ready(function() {
		$(".datetimepicker-input").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		language : 'zh-CN'
	}).on('changeDate', function(ev){
	$('.datetimepicker-input').datetimepicker('hide');
	});
	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		if($(".input-submit").attr("ajax-va")=="fail")
			 return false;
		var submit_type = $(this).attr("data_submit_type");
		switch (submit_type) {
		case 'submit_cancel':
			form_cancel();
			break;
		case 'submit_save_back':
			back_listing = true;
			form_submit();
			break;
		case 'submit_save_continue':
			back_listing = false;
			form_submit();
			break;
		}
	});
	/**
	 * 通过库房得到库区
	 */
	var storeId=null;
	$('select[name="stroreId"]').change(function(){
		storeId=$(this).val();
		if(storeId==null||storeId==""){
			var optionHtml = "<option value=''>请选择库区</option>";
			$('#stroreAreaId').html(optionHtml);
			return;
		}
		$.ajax({
			url:BASE_URL + '/wmsStoreArea/getStoreAreaByStoreId',
			dataType:'JSON',
			data:{storeId:storeId},
			type:'POST',
			complete:function(response){
				var datas = response.responseJSON.data;
				var optionHtml = "<option value=''>请选择库区</option>";
				$.each(datas,function(index, data){
					optionHtml +='<option value="'+data.stroreAreaId+'">'+data.name+'</option>';
				});
				$('#stroreAreaId').html(optionHtml);
				var optionHtmls = "<option value=''>请选择密集架</option>";
				$('#deviceId').html(optionHtmls);
			}
		});
	}) 
	/**
	 * 通过库房库区得到密集架
	 */
	$('select[name="stroreAreaId"]').change(function(){
		var stroreAreaId=$(this).val();
		if(stroreAreaId==null||stroreAreaId==""||storeId==null||storeId==""){
			var optionHtml = "<option value=''>请选择密集架</option>";
			$('#deviceId').html(optionHtml);
			return;
		}
		$.ajax({
			url:BASE_URL + '/deviceManage/getStoreDeviceListProperty',
			dataType:'JSON',
			data:{storeId:storeId,stroreAreaId:stroreAreaId},
			type:'POST',
			complete:function(response){
				var datas = response.responseJSON.data;
				var optionHtml = "<option value=''>请选择密集架</option>";
				$.each(datas,function(index, data){
					optionHtml +='<option value="'+data.deviceId+'">'+data.name+'</option>';
				});
				$('#deviceId').html(optionHtml);
			}
		});
	});
	
});

/**
 * 表单提交处理
 */
function form_submit() {
	var archivesId = $("input[name=archivesId]").val();
	if(!checkIsNull()){
		return false;
		}
	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (archivesId == '' || archivesId == 0) {
		saveCallBack = form_save_added;
	} else {
		saveCallBack = form_save_edited;
	}
	
	var options = {
		dataType : 'json',
		timeout : 60000,
		success : saveCallBack
	};
	$("#edit_form").ajaxSubmit(options);
	return false;
}

/**
 * 取消处理
 */
function form_cancel() {
	window.location.href = BASE_URL + "/amsArchives/index";
}

/**
 * 添加成功，返回处理
 */
function form_save_added(data, textStatus) {
	if (data.status === 0) {
		alert('添加成功!');

		// 判断是否返回列表管理
		if (back_listing == true) {
			form_cancel();
		} else {
			window.location.href = BASE_URL + "/amsArchives/add";
		}
	} else {
		alert(data.msg);
	}
	$(".input-submit").removeAttr('disabled');
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
	if (data.status === 0) {
		alert('编辑成功!');
		form_cancel();
	} else {
		alert(data.msg);
	}
	$(".input-submit").removeAttr('disabled');
}
/**
 * 添加或修改输入框为空时加判断
 */
function checkIsNull(){
	var value = $(".form-control[name!='remark']");
	for(var i =1;i < value.length;i++){//遍历打到dom对象
		if(i==0||i==3||i==6||i==10){
			continue;
		}
		if($.trim(value[i].value)==""||value[i].value==null){
			alert($(value[i]).parent().parent().children('.control-label').text()+"不能为空")
			return false;
			}
	}
	return true;
}



//档案类型
$("#archiveType").change(function(){
	var archiveType = $("#archiveType option:selected").val()
	if(archiveType == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#archiveType","error","请选择类型");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#filingmethod","success","");
	}
});
//立卷方式
$("#filingmethod").change(function(){
	var archiveType = $("#filingmethod option:selected").val()
	if(archiveType == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#filingmethod","error","请选择立卷方式");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#filingmethod","success","");
	}
	
});


//档案号
$("#archiveno").change(function(){
	var archiveNO=$(this).val();
	if($.trim(archiveNO)==""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#archiveno","error","请输入档案号");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#archiveno","success","");
	}
});

//正题名
$("#title").change(function(){
	var archiveType = $("#title").val()
	if(archiveType == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#title","error","正题名不能为空");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#title","success","");
	}
});
//在库状态
$("#inflag").change(function(){
	var inflag = $("#inflag").val()
	if($.trim(inflag) == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#inflag","error","在库状态不能为空");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#inflag","success","");
	}
});
//盘点状态
$("#checkStatus").change(function(){
	var checkStatus = $("#checkStatus").val()
	if($.trim(checkStatus) == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#checkStatus","error","盘点状态不能为空");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#checkStatus","success","");
	}
});
//keepyear保管年限
$("#keepyear").change(function(){
	var keepyear = $("#keepyear option:selected").val()
	if(keepyear == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#keepyear","error","保管年限必须选择");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#keepyear","success","");
	}	
});

//密级
$("#security").change(function(){
	var securityID = $("#security option:selected").val()
	if(securityID == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#security","error","密级必须选择");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#security","success","");
	}	
});
//所属库区
$("#stroreAreaId").change(function(){
	var stroeID = $("#stroreAreaId option:selected").val()
	if(stroeID == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#stroreAreaId","error","所属库房必须选择");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#stroreAreaId","success","");
	}	
});
//所属库房
$("#storeID").change(function(){
	var stroeID = $("#storeID option:selected").val()
	if(stroeID == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#storeID","error","所属库区必须选择");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#storeID","success","");
	}	
});
//所属密集架
$("#deviceId").change(function(){
	var deviceID = $("#deviceId option:selected").val()
	if(deviceID == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#deviceId","error","所属密集架必须选择");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#deviceId","success","");
	}	
});
//方位左右
$("#storeLr").change(function(){
	var storeLR = $("#storeLr option:selected").val()
	if(storeLR == ""){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#storeLr","error","方位左右必须选择");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#storeLr","success","");
	}	
});
//密集架列号
$("#storeColumn").change(function(){
	var storeColumn = $("#storeColumn").val()
	if(storeColumn!=null){		
		var regName = /(^[0-9]{2}$)/;
		if(!regName.test(storeColumn)){
			$(".input-submit").attr("ajax-va","fail");
		    show_validate_msg("#storeColumn","error","密集架列号可以为空或者只许2位数字");
		}else{
			$(".input-submit").attr("ajax-va","success");
			show_validate_msg("#storeColumn","success","");
		}
        
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#storeColumn","success","");
	}
	if($.trim(storeColumn)==""){	
	  $(".input-submit").attr("ajax-va","success");
	  show_validate_msg("#storeColumn","success","");
	}
	
});
//所属节
$("#storeSection").change(function(){
	var storeSection = $("#storeSection").val()
	if(storeSection!=null){		
		var regName = /(^[0-9]{2}$)/;
		if(!regName.test(storeSection)){
			$(".input-submit").attr("ajax-va","fail");
			show_validate_msg("#storeSection","error","所属节可以为空或者只许2位数字");
		}else{
			$(".input-submit").attr("ajax-va","success");
			show_validate_msg("#storeSection","success","");
		}
		
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#storeSection","success","");
	}
	if($.trim(storeSection)==""){
		$(".input-submit").attr("ajax-va","success");
	    show_validate_msg("#storeSection","success","");
	}
});
//所属号
$("#storeLayer").change(function(){
	var storeLayer = $("#storeLayer").val()
	if(storeLayer!=null){		
		var regName = /(^[0-9]{2}$)/;
		if(!regName.test(storeLayer)){
			$(".input-submit").attr("ajax-va","fail");
			show_validate_msg("#storeLayer","error","所属号可以为空或者只许2位数字");
		}else{
			$(".input-submit").attr("ajax-va","success");
			show_validate_msg("#storeLayer","success","");
		}
	}else{
		 $(".input-submit").attr("ajax-va","success");
		  show_validate_msg("#storeLayer","success","");
	}
	if($.trim(storeLayer)==""){
		$(".input-submit").attr("ajax-va","success");
	    show_validate_msg("#storeLayer","success","");
	}
});
//

function show_validate_msg(ele,status,msg){
	//清除当前元素的校验状态
	$(ele).parent().removeClass("has-success has-error");
	$(ele).next("span").text("");
	if("success"==status){
		$(ele).parent().addClass("has-success");
		$(ele).next("span").text(msg);
	}else if("error" == status){
		$(ele).parent().addClass("has-error");
		$(ele).next("span").text(msg);
	}
}