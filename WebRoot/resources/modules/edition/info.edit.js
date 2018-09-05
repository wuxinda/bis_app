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
		var submit_type = $(this).attr("data_submit_type");
		
		var value = $(".form-control[name!='remark']");
		/*alert(121)*/
		for(var i =0;i < value.length;i++){//遍历打到dom对象	
				if($(value[i]).attr("name")=="isPublish"){					
					break;
				}
				if($(value[i]).val()==""){
					if($(value[i]).attr("name")=="editionUrl"){
						if($("input[name='editionId']").val()!=null&&$("input[name='editionId']").val()!=""&&$("input[name='editionId']").val()!=undefined){
							//alert($("#editionId").val());
							continue;
						}
					}
					alert($(value[i]).parent().parent().children('.control-label').text()+"不能为空")
					return false;
				}					
		}
		
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
	var editionId = $("input[name=editionId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (editionId == '' || editionId == 0) {
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
	window.location.href = BASE_URL + "/editionInfo/index";
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
			window.location.href = BASE_URL + "/editionInfo/add";
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

//不同服务版本类型
$("#editionType").change(function(){
	 var status = $("#editionType option:selected").val();
	 if(status==0){
		 var optionHtml = "<option value=''>请选择</option><option value='001'>iPhone手机</option>";
	     $('#sunType').html(optionHtml);
	 }else if(status==1){
		 var optionHtml = "<option value=''>请选择</option><option value='001'>安卓手机</option><option value='002'>固定列</option><option value='003'>移动列</option>";
	     $('#sunType').html(optionHtml);
	 }else if(status==2){
		 var optionHtml = "<option value=''>请选择</option><option value='001'>iPad</option>";
	     $('#sunType').html(optionHtml);
	 }
	 if($.trim(status)==""||$.trim(status)==null){
		 $(".form-control").attr("ajax-val","fail");
		 show_validate_msg(this,"error","版本类型必须选择!");
	 }else{
		 $(".form-control").attr("ajax-val","success");
		 show_validate_msg(this,"success","");
	 }
	 
});



//版本号
$("#editionNumber").change(function(){
	var number = $(this).val();
	if($.trim(number)==""||$.trim(number)==null){
		$(".form-control").attr("ajax-val","fail");
		show_validate_msg(this,"error","版本号必须填写!");
	}else{
		$(".form-control").attr("ajax-val","success");
		show_validate_msg(this,"success","");
	}
});
//版本名称
$("#editionName").change(function(){
	var name = $(this).val();
	if($.trim(name)==""||$.trim(name)==null){
		$(".form-control").attr("ajax-val","fail");
		show_validate_msg(this,"error","版本名称必须填写!");
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