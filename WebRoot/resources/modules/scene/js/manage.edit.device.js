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
	
	/**
	 * 重载已选属性列表的拖拽事件
	 */
	$('#selected_property').find('ul').sortable('refresh');

	/**
	 * 移除一个已选择的关联属性
	 */
	$("#selected_property").delegate('.fa-remove-brand', 'click', function() {
	remove_property($(this).parent().parent());
	});	
});

//库房选择
function categoryName1(id,name) {
	$("#category1").html(name);
	$("#category1").attr("data",id);
	$.ajax({
		type : 'get',
		url : BASE_URL + '/deviceManage/getStoreDevCate',
		data : 'storeId=' + id,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$("#categoryName2").html("");
//				$("#addDevice").html("");
				$("#category2").html("请选择设备分类");
				$("#category3").html("请选择设备");
				$("#category4").html("请选择动作");
				$("#categoryName2").html("");
				$("#categoryName3").html("");
				$("#categoryName4").html("");
				$.each(data.data, function(index, item) {
					$("#categoryName2").append("<li onclick=\"categoryName2("+item.categoryId+",'"+item.categoryName+"')\">"+item.categoryName+"</li>");
				});
			} else {
			}
			return false;
		}
	});
}
//设备分类选择
function categoryName2(id,name) {
	$("#category2").html(name);
	$("#category2").attr("data",id);
	$.ajax({
			type : 'get',
			url : BASE_URL + '/deviceManage/getStoreDeviceList',
			data : {
				categoryId : id,
				storeId : $("#category1").attr("data")
				},
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				if (data.status == 0) {
				$("#categoryName3").html("");
//				$("#addDevice").html("");
				$("#category3").html("请选择设备");
				$("#category4").html("请选择动作");
				$("#categoryName3").html("");
				$("#categoryName4").html("");
				$.each(data.data, function(index, item) {
				$("#categoryName3").append("<li onclick=\"categoryName3("+item.deviceId+",'"+item.name+"')\">"+item.name+"</li>");
				});
				} else {
				}
				return false;
			}
		});
}
//设备选择
function categoryName3(id,name) {
	$("#category3").html(name);
	$("#category3").attr("data",id);
	$.ajax({
		type : 'get',
		url : BASE_URL + '/deviceCategoryActionproperty/getActionByCategoryId',
		data : {
			categoryId : $("#category2").attr("data")
		},
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
				$("#categoryName4").html("");
//				$("#addDevice").html("");
				$("#category4").html("请选择动作");
				$("#categoryName4").html("");
				$.each(data.data, function(index, item) {
				$("#categoryName4").append("<li>"+"<input type='checkbox' name='post[]' id='select_single_p_"+item.actionpropertyId+"' class='select-single' data-type='p' value='"+item.actionpropertyId+"'><span>"+item.name+"</span></li>");
				});
			} else {
			}
			/**
			 * 选择/移除一个关联属性
			 */
			$("#quyu .nav >li,#operate .nav >li").delegate('.select-single', 'change', function() {
				if ($(this).is(':checked')) {
					select_property($(this));
				} else {
					if ($(this).attr('data-type') == 'p') {
						remove_property($('li#li_property_' + $(this).val()));
					} else {
						remove_property($('li#li_property_group_' + $(this).val()));
					}
				}
			});
			return false;
		}
	});
}
//添加设备－－满足条件方能添加
function addDevice() {
	$("#addDevice").html("");
	//组装分类参数 格式为id,name
	//库房
	var category1 = $("#category1").attr("data")+","+$("#category1").html();
	//设备分类
	var category2 = $("#category2").attr("data")+","+$("#category2").html();
	//品牌
	var category3 = $("#category3").attr("data")+","+$("#category3").html();
	//库房，分类和品牌选择的情况下，可以添加
	if($("#category1").html()!='请选择库房' &&$("#category2").html()!='请选择设备分类' && $("#category3").html()!='请选择品牌'){
		$("#addDevice").html("<a href='"+BASE_URL+"/deviceManage/add?category1="+category1+"&category2="+category2+"&category3="+category3+"' class='btn btn-sm btn-default load-content'>添加</a>");
	}
}
/**
 * 选择一个关联属性
 */
function select_property(obj) {
	var template_selected_property = '<li class="b-b m-t-none-reset" id="{#property_id_str#}" property_id="{#property_id#}" data-type="{#type#}" device_id="{#device_id#}" draggable="true">'
			+ '<a href="javascript:;">' + '<i title="{#title#}" class="fa fa-times pull-right m-t-xs fa-remove-brand"></i>'
			+ '<i class="fa fa-fw fa-ellipsis-v"></i><font class="property-name">{#property_name#}</font>' + '</a></li>';

	var obj_tr = obj.parent().parent();
	var data_type = obj.attr('data-type');
	/*选择的动作id*/
	var property_id = obj.val();
	//动作名称
	var category4Name  = "";
	//选择的设备id
	var deviceId = $("#category3").attr("data");
	$(":checked").each(function(index, item) {
		category4Name+=$(item).parent().find("span").text()+",";
		});
	$("#category4").html(category4Name.substr(0,category4Name.length-1));
	$("#category4").attr("data",property_id);
	//已选设备联动名称为库房＋设备＋操作
	//库房
	var category1 = $("#category1").html();
	//设备
	var category3 = $("#category3").html();
	//动作	
	var category4 = obj.parent().find("span").text();
	var property_name =category1+"-"+category3+"-"+category4;

	var tsp = template_selected_property.replace('{#property_id#}', property_id).replace('{#device_id#}', deviceId).replace('{#type#}', data_type).replace('{#property_name#}', property_name);
	if (data_type == 'p') {
		var tsp = tsp.replace('{#property_id_str#}', 'li_property_' + property_id).replace('{#title#}', '移除该属性');
	} else {
		var tsp = tsp.replace('{#property_id_str#}', 'li_property_group_' + property_id).replace('{#title#}', '移除该属性分组');
	}

	/* 将新选择属性加入到已选列表，并重载该列表的拖拽事件 */
	$('#selected_property').find('ul').append(tsp).sortable('refresh');
	
}
/**
 * 移除一个已选择的关联属性
 */
function remove_property(obj) {
		$('#select_single_p_' + obj.attr('property_id')).removeAttr('checked');
	     obj.remove();
	 	//动作名称
	 	var category4Name  = "";
	 	$(":checked").each(function(index, item) {
	 		category4Name+=$(item).parent().find("span").text()+",";;
	 		});
	 	$("#category4").html(category4Name.substr(0,category4Name.length-1));
}
/**
 * 表单提交处理
 */
function form_submit() {
	var sceneId = $("input[name=sceneId]").val();
	//提交之前需要对已选设备提交参数linkageActionIds进行赋值
	var linkageActionIds = ""
	$(".b-b.m-t-none-reset").each(function(index, item) {
		var deviceId = $(item).attr("device_id");
		linkageActionIds += deviceId + "." + $(item).attr("property_id") + ",";
	});
	//linkageActionIds进行赋值
	$("#linkageActionIds").val(linkageActionIds.substr(0,linkageActionIds.length-1));
	$(".input-submit").attr('disabled', true);

	var saveCallBack;
	if (sceneId == '' || sceneId == 0) {
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
	window.location.href = BASE_URL + "/sceneManage/index";
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
			window.location.href = BASE_URL + "/sceneManage/add";
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
