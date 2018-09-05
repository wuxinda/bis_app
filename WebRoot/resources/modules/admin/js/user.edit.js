$(document).ready(function() {
	/*	
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
	
	datas.push({"merchantId":$('#merchantId').val(),"merchantName":$('#merchantName').val()});
});

function setValue(){
	if(datas.length>0){
		$.each(datas, function(index, data) {
			$('#merchantId').val(data.merchantId);
			$('#merchantName').val(data.merchantName);
		});
	}else {
		$('#merchantId').val("");
		$('#merchantName').val("");
	}
	closeModal();
}

function closeModal(){
	$('#ajaxModal').modal('hide');
}

/**
 * 表单提交处理
 */
function form_submit() {
	var userId = $("input[name=userId]").val();
	notice('edit_notice', img_loading_small, false);
	if(!userId){//新增
		if (!$("input[name=userName]").val() || !$("input[name=password]").val() || !$("input[name=repassword]").val() ) {
			notice('edit_notice', img_delete + ' 请填写完所有带红色星号的内容', true, 5000);
			return false;
		}
		if($("input[name=repassword]").val()){
			if($("input[name=password]").val()!=$("input[name=repassword]").val()){
				notice('edit_notice', img_delete + ' 两次输入的密码不一致！', true, 5000);
				return false;
			}
		}
	}else{//编辑
		if ($("input[name=password]").val() && ! confirm('本次更新将连同密码一并更新，确定要继续吗？')) {
			notice('edit_notice', '', false);
			return false;
		}
	}
	
	
	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (userId == '' || userId == 0) {
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
	window.location.href = BASE_URL + "/adminUser/index";
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
			window.location.href = BASE_URL + "/adminUser/add";
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