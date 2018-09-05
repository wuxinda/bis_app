$(document).ready(function() {
	
	/**
	 * 二级菜单选中
	 */
	$('form').on('change','#menuIdSelect,select[name="firstMenuId"]', function(){
		
		flushUrl();
		
	});
	
	/**
	 * 操作代码选中
	 */
	$('input[name="code"]').on('click',function(){
		
		flushUrl();
		
	});
	
	/**
	 * 通过父级菜单获得子级列表
	 */
	$('select[name="firstMenuId"]').change(function(){
		var menuId = $(this).val();
		if(menuId==0){
			var optionHtml = "<option value=0>请选择子集菜单</option>";
			$('#menuIdSelect').html(optionHtml);
			return;
		}
		$.ajax({
			url:BASE_URL + '/adminMenu/getAdminMenuByPid',
			dataType:'JSON',
			data:{pid:menuId},
			type:'POST',
			complete:function(response){
				var data = response.responseJSON.data;
				var optionHtml = "<option value=0>请选择子集菜单</option>";
				$.each(data,function(index, data){
					optionHtml +='<option data-link="'+data.url+'" value="'+data.menuId+'">'+data.name+'</option>';
				});
				$('#menuIdSelect').html(optionHtml);
			}
		});
	});
	
	
	/**
	 * 通过父级菜单获得子级列表
	 */
	$('select[name="codeSelect"]').change(function(){
		var menuId = $(this).val();
		if(menuId==0){
			return;
		}
		$.ajax({
			url:BASE_URL + '/adminMenu/getAdminMenuByPid',
			dataType:'JSON',
			data:{pid:menuId},
			type:'POST',
			complete:function(response){
				var data = response.responseJSON.data;
				var optionHtml = "<option value=0>请选择</option>";
				$.each(data,function(index, data){
					optionHtml +='<option data-link="'+data.url+'" value="'+data.menuId+'">'+data.name+'</option>';
				});
				$('#menuIdSelect').html(optionHtml);
			}
		});
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
});

/**
 * 刷新url 名称
 */
function flushUrl() {
	var str=" - ";
	var name="";
	var url="";
	
	if($("select[name='firstMenuId'] option:selected").val()>0
			&&$("select[name='menuId'] option:selected").val()>0){
			name=$("select[name='firstMenuId'] option:selected").text()
				+str+$("select[name='menuId'] option:selected").text();
			url = $('#menuIdSelect').find('option:checked').data('link');
	}
	
	if($('input[name="code"]:checked').length!=0){
		var code = $('input[name="code"]:checked').val();
		url = url==""?"":url.replace('index','') + code;
		name=name==""?"":name+str+$('input[name="code"]:checked').parent().text().replace(/(^\s*)|(\s*$)/g, "");
	}
	
	$('input[name="url"]').val(url);
	$('input[name="name"]').val(name);
}

/**
 * 表单提交处理
 */
function form_submit() {
	var permissionId = $("input[name=permissionId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (permissionId == '' || permissionId == 0) {
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
	window.location.href = BASE_URL + "/adminPermission/index";
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
			window.location.href = BASE_URL + "/adminPermission/add";
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