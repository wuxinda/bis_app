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
});

/**
 * 表单提交处理
 */
function form_submit() {
	if(!checkIsNull())return false;
	var archivesTypeId = $("input[name=archivesTypeId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (archivesTypeId == '' || archivesTypeId == 0) {
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
	window.location.href = BASE_URL + "/amsArchivesType/index";
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
			window.location.href = BASE_URL + "/amsArchivesType/add";
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
		if($.trim(value[i].value)==""||value[i].value==null){
			alert($(value[i]).parent().parent().children('.control-label').text()+"不能为空")
			return false;
			}
	}
	return true;
}
/**
 * 对名称和排序号判断时对保存操作按钮添加一个属性，如果名称排序号判断成功则保存操作可以使用否则不可以使用
 */
//添加的档案名称进行判断
$("#name").change(function(){	
	   var typeName = $("#name").val();
		if($.trim(typeName)==""){			
			show_validate_msg("#name", "error", "类型不能为空");
			$(".input-submit").attr("ajax-va","fail");
			return false;
		}	
		$(".input-submit").attr("ajax-va","success");
});
//添加的排序号进行判断
$("#sortOrder").change(function(){
	var sortOrder = $("#sortOrder").val();
	var regName = /(^[0-9]+$)/;
	if(!regName.test(sortOrder)){		
		show_validate_msg("#sortOrder", "error", "排序号必须为数字！");
		$(".input-submit").attr("ajax-va","fail");
		return false;
	}else{
		$(".input-submit").attr("ajax-va","success");
		show_validate_msg("#sortOrder", "success", "");
	};
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