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
		if($(".input-submit").attr("ajax-va")=="fail"){
		return false;
	}
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
});

/**
 * 表单提交处理
 */
function form_submit() {
	if(!checkIsNull()){
		return false;
		}
	var storeId = $("input[name=storeId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (storeId == '' || storeId == 0) {
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
	window.location.href = BASE_URL + "/wmsStore/index";
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
			window.location.href = BASE_URL + "/wmsStore/add";
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
	for(var i =0;i < value.length;i++){//遍历打到dom对象
		if($(value[i]).attr('name')=='code'||$(value[i]).attr('name')=='sortOrder'){
			continue;
		}
		if($.trim(value[i].value)==""||value[i].value==null){
			alert($(value[i]).parent().parent().children('.control-label').text()+"不能为空");
			return false;
			}
	}
	return true;
}
//库房名称
$("#name").change(function(){
	var storeName = $("#name").val()
	if($.trim(storeName)==""||$.trim(storeName)==null){
		 $(".input-submit").attr("ajax-va","fail");
		 show_validate_msg("#name","error","库房名称不能为空");
	}else{
		$(".input-submit").attr("ajax-va","success");
	    show_validate_msg("#name","success","");
	}
});

//楼号
$("#buildingNo").change(function(){
	var buildingNO = $("#buildingNo").val()
	if($.trim(buildingNO)==""||$.trim(buildingNO)==null){
		 $(".input-submit").attr("ajax-va","fail");
		 show_validate_msg("#buildingNo","error","所属楼号不能为空");
	}else{
		$(".input-submit").attr("ajax-va","success");
	    show_validate_msg("#buildingNo","success","");
	}
});
//层数
$("#floorNo").change(function(){
	var floorNO = $("#floorNo").val()
	if($.trim(floorNO)==""||$.trim(floorNO)==null){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#floorNo","error","所属层数不能为空");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#floorNo","success","");
	}
});

//状态
$("#status").change(function(){
	var status = $("#status option:selected").val()
	if(status == ""||status == null){
		$(".input-submit").attr("ajax-va","fail");
		show_validate_msg("#status","error","请选择状态");
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#status","success","");
	}
});

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



