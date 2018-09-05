$(document).ready(function() {
	/*获取url传递参数并解析 解析规则id,name */ 
	//库房
	var category1 = $.getQueryValue('category1');
	if(null!=category1){
	$("#storeId").val(category1.split(",")[0]);
	$("#storeName").val(category1.split(",")[1]);
	}
	//设备类型
	var category2 = $.getQueryValue('category2');
	if(null!=category2){
	$("#categoryId").val(category2.split(",")[0]);
	$("#categoryName").val(category2.split(",")[1]);
	}
	//品牌
	var category3 = $.getQueryValue('category3');
	if(null!=category3){
	$("#brandId").val(category3.split(",")[0]);
	$("#brandName").val(category3.split(",")[1]);
	}
	//库区
	var category4 = $.getQueryValue('category4');
	if(null!=category4){
		$("#stroreAreaId").val(category4.split(",")[0]);
	}
	/**
	 * 切换模板编辑位置
	 */
	$('.nav-map').click(function() {
		/* 模板导航的样式处理 */
		$('.nav-map').removeClass('active');
		$(this).addClass('active');

		/* 切换编辑区域 */
		$('.edit-map').hide();
		var nav_id = $(this).attr('id');
		var edit_id = nav_id.replace('nav', 'edit');
		$('#' + edit_id).show();

		if ($('#' + edit_id).attr('id') == 'edit_related') {
			$('#' + edit_id).css({
				'display' : 'table'
			});
		}
	});

	
	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		if($(".form-control").attr("ajax-val")=="fail"){
			return fail;
		}	
		var value = $(".form-control[name!='remark']");
		/*alert(121)*/
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
	var deviceId = $("input[name=deviceId]").val();

	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (deviceId == '' || deviceId == 0) {
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
	window.location.href = BASE_URL + "/deviceManage/index";
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
			window.location.href = BASE_URL + "/deviceManage/add";
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
//所属库区
$("#stroreAreaId").change(function(){
	var storeAreaId = $("#storeAreaId option:selected").val();
	if($.trim(storeAreaId)==""||$.trim(storeAreaId)==null){
		$(".form-control").attr("ajax-val","fail"); 
		show_validate_msg(this,"error","库区必须选择");
	}else{
		$(".form-control").attr("ajax-val","success");
		show_validate_msg(this,"success","");
	}
})

/*$("status").change(function(){
	 var status = $(this).val()
	 if($.trim(status)==""||$.trim(status)==null){
		 $(".form-control").attr("ajax-val","fail"); 
		 show_validate_msg(this,"error","状态必须选择")
	 }
});
*/
//设备名称
$("#name").change(function(){
	var name = $(this).val();
	if($.trim(name)==""||$.trim(name)==null){
		$(".form-control").attr("ajax-val","fail");
		show_validate_msg(this,"error","设备名称必须填写");
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