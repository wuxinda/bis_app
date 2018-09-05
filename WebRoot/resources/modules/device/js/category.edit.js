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
		var value = $(".form-control[name!='remark']");
		for(var i =0;i < value.length;i++){//遍历打到dom对象
			if($(value[i]).attr("name")=="sortOrder"){
				continue;
			}
			if($.trim(value[i].value)==""||value[i].value==null){
				alert($(value[i]).parent().parent().children('.control-label').text()+"不能为空");
				return false;
			}
		}		
		if($(".form-control").attr("ajax-val")=="fail")
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
	
	var value = $(".form_control[name!='remark']");
	for(var i =0;i < value.length;i++){//遍历打到dom对象
		if($(value[i]).attr("name")=="sortOrder"){	
			continue;
		}
		if($.trim(value[i].value)==""||value[i].value==null){
			alert($(value[i]).parent().parent().children('.control-label').text()+"不能为空")
			return false;
			}
	}
	
	var categoryId = $("input[name=categoryId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (categoryId == '' || categoryId == 0) {
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
	window.location.href = BASE_URL + "/deviceCategory/index";
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
			window.location.href = BASE_URL + "/deviceCategory/add";
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

//分类名称
$("#name").change(function(){
	    var categoryName = $(this).val();    
		if($.trim(categoryName)==""||$.trim(categoryName)==null){
			$(".form-control").attr("ajax-val","fail");
			show_validate_msg(this,"error","分类名称不能为空");
		}else{
			$(".form-control").attr("ajax-val","success");
			show_validate_msg(this,"success","");
		}
	});

$("#statu").change(function(){
	 var status = $("#statuc option:selected").val();
	 if($.trim(status)==""||$.trim(status)==null){
		 $(".form-control").attr("ajax-val","fail");
		 show_validate_msg(this,"error","状态必须选择");
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