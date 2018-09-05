$(document).ready(function() {
	
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
		case 'submit_save_submit':
			back_listing = true;
			form_submit();
			break;
		}
	});
});

/**
 * 取消处理
 */
function form_cancel() {
	
	window.location.href = document.referrer;
}

/**
 * 表单提交处理
 */
function form_submit() {
	notice('editNotice', img_loading_small, false);
	
	if ($("#oldpass").val() == ""){
		notice('edit_notice', img_delete + '原密码不能为空！', true, 3000);
		return false;
	} else if ($("#newpass").val() == "") {
		notice('edit_notice', img_delete + '新密码不能为空！', true, 3000);
		return false;
	} else if ($("#conformpass").val() == "") {
		notice('edit_notice', img_delete + '确认密码不能为空！', true, 3000);
		return false;
	} else if ($("#newpass").val() != $("#conformpass").val()) {
		notice('edit_notice', img_delete + '两次密码不一致！', true, 3000);
		return false;
	}
	var callback = call_back;
	var options = {
			dataType : 'json',
			timeout : 60000,
			success : callback
		};
		$("#edit_form").ajaxSubmit(options);
		return false;
}

function call_back (data, textStatus){
	if (data.status === 0) {
		alert('修改成功!');
		window.location.href = BASE_URL;
	} else {
		alert(data.msg);
	}
}