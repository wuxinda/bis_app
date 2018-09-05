$(document).ready(function() {
	
		
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
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
	 $('#userIds').multiselect({  
         // 自定义参数，按自己需求定义  
         nonSelectedText : '--未分配--',   
         maxHeight : 350,
         includeSelectAllOption : true, 
         filterPlaceholder:'搜索',
         enableFiltering: true,  
         selectAllJustVisible: true, 
         numberDisplayed : 5 
     });  
});

/**
 * 表单提交处理
 */
function form_submit() {
	var wmsUserId = $("input[name=wmsUserId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (wmsUserId == '' || wmsUserId == 0) {
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
		alert('更新成功!');

		// 判断是否返回列表管理
		if (back_listing == true) {
			form_cancel();
		} else {
			window.location.href = BASE_URL + "/wmsStore/index";
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
		alert('更新成功!');
		form_cancel();
	} else {
		alert(data.msg);
	}
	$(".input-submit").removeAttr('disabled');
}