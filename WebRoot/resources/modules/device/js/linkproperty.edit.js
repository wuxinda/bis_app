$(document).ready(function() {
	
		
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		
		if($(".form-control").attr("ajax-val")=="fail"){
			return false;
		}	
		var value = $(".form-control[name!='remark']");
		for(var i =0;i < value.length;i++){//遍历打到dom对象
			if($(value[i]).attr("name")=="name"){
				if($(value[i]).val()==""){
					alert($(value[i]).parent().parent().children('.control-label').text()+"不能为空")
					return false;
				}
			}	
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
	var linkpropertyId = $("input[name=linkpropertyId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (linkpropertyId == '' || linkpropertyId == 0) {
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
	window.location.href = BASE_URL + "/deviceLinkproperty/index";
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
			window.location.href = BASE_URL + "/deviceLinkproperty/add";
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

//品牌名称
$("#name").change(function(){
	var name = $(this).val();
	if($.trim(name)==""||$.trim(name)==null){
		$(".form-control").attr("ajax-val","fail");
		show_validate_msg(this,"error","属性名称必须填写");
	}else{
		$(".form-control").attr("ajax-val","success");
		show_validate_msg(this,"success","");
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