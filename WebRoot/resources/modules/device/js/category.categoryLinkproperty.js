
$(document).ready(function() {
	/**
	 * 加载属性及属性分组列表
	 */
	refreshListingProperty();
	
	/**
	 * 刷新搜索
	 */
	$(".action-refresh,#action_search").on('click', function() {
		$('#content_listing').datagrid('reload');
		return false;
	});
	
	
	

	/**
	 * 关键字搜索 - 支持回车
	 */
	$('input[name=key]').on('keypress', function(event) {
		if (event.which == '13') {
			$('#content_listing').datagrid("reload");
			return false;
		}
	});

	/**
	 * 重载已选属性列表的拖拽事件
	 */
	$('#selected_property').find('ul').sortable('refresh');

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
	});

	/**
	 * 选择/移除一个关联属性
	 */
	$("#content_listing>tbody, #property_group_listing>tbody").delegate('.select-single', 'change', function() {
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

	/**
	 * 移除一个已选择的关联属性
	 */
	$("#selected_property").delegate('.fa-remove-brand', 'click', function() {
	remove_property($(this).parent().parent());
	});

	/**
	 * 提交按钮处理
	 */
	$(".input-submit").click(function() {
		var submit_id = $(this).attr("id");
		switch (submit_id) {
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
 * 将属性池中已被选中的备选属性置为已选中状态 - 用于编辑及列表翻页的情况
 */
function reset_property_listing() {
	$('#selected_property').find('ul>li').each(function() {
		var property_id = $(this).attr('property_id');
//		if ($(this).attr('data-type') == 'p') {
//			$('#select_single_p_' + property_id).prop('checked', true);
//		} else {
//			$('#select_single_g_' + property_id).prop('checked', true);
//		}
		$('#select_single_p_' + property_id).prop('checked', true);
	});
}

/**
 * 选择一个关联属性
 */
function select_property(obj) {
	var template_selected_property = '<li class="b-b m-t-none-reset" id="{#property_id_str#}" property_id="{#property_id#}" data-type="{#type#}" draggable="true">'
			+ '<a href="javascript:;">' + '<i title="{#title#}" class="fa fa-times pull-right m-t-xs fa-remove-brand"></i>'
			+ '<i class="fa fa-fw fa-ellipsis-v"></i><font class="property-name">{#property_name#}</font>' + '</a></li>';

	var obj_tr = obj.parent().parent();
	var data_type = obj.attr('data-type');
	/**/
	var property_id = obj.val();
	var property_name = obj_tr.find('td').eq(2).text();

	var tsp = template_selected_property.replace('{#property_id#}', property_id).replace('{#type#}', data_type).replace('{#property_name#}', property_name);
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
}

/**
 * 取消处理
 */
function form_cancel() {
	// history.go(-1);
	window.location.href = BASE_URL + "/deviceCategory/index";
}

/**
 * 表单提交处理
 */
function form_submit() {
	var saveCallBack = form_save_edited;
	/* 处理属性 */
	var propertyIds = '';
	var sp = new Array();
	$('#selected_property').find('ul>li').each(function(k) {
		var data_type = $(this).attr('data-type');
		var property_id = $(this).attr('property_id');
	    sp[k] = property_id;
	});
	if (sp.length > 0) {
		propertyIds = sp.join(',');
	}
	$('input[name=linkpropertyIds]').val(propertyIds);

	$(".input-submit").attr('disabled', true);
	
	var options = {
		dataType : 'json',
		timeout : 60000,
		success : saveCallBack
	};
	
	$("#edit_form").ajaxSubmit(options);
	alert("保存成功");
    window.location.href = BASE_URL + "/deviceCategory/index";
	return false;
}

/**
 * 编辑成功，返回处理
 */
function form_save_edited(data, textStatus) {
	if (data.status === 0) {
		alert("编辑成功！");
//		notice('edit_notice', img_done + ' 编辑成功!', true, 5000);
		form_cancel();
	} else {
		alert(data.msg);
//		notice('edit_notice', img_delete + " " + data.msg, true, 5000);
	}
	$(".input-submit").removeAttr('disabled');
}


/**
 * datagrid 加载属性列表
 */
function refreshListingProperty() {
	/* fuelux datagrid */
	var DataGridDataSource = function(options) {
		this._formatter = options.formatter;
		this._columns = options.columns;
		this._delay = options.delay;
	};

	DataGridDataSource.prototype = {
		columns : function() {
			return this._columns;
		},
		data : function(options, callback) {
			var url = BASE_URL + '/deviceLinkproperty/page';
			var self = this;

			setTimeout(function() {

				var data = $.extend(true, [], self._data);

				$.ajax(url, {
					data : {
						rstype : "json",
						pageIndex : options.pageIndex + 1,
						pageSize : options.pageSize,
						key : $("input[name='key']").val(),
						status : 1
					},
					dataType : 'json',
					async : true,
					type : 'POST'
				}).done(function(response) {
					var data = response.data;
					if (!data) {
						return false;
					}

					var count = response.count;// 设置data.total
					// PAGING
					var startIndex = options.pageIndex * options.pageSize;
					var endIndex = startIndex + options.pageSize;
					var end = (endIndex > count) ? count : endIndex;
					var pages = Math.ceil(count / options.pageSize);
					var page = options.pageIndex + 1;
					var start = startIndex + 1;

					if (self._formatter)
						self._formatter(data);

					callback({
						data : data,
						start : start,
						end : end,
						count : count,
						pages : pages,
						page : page
					});

					// 将属性池中已被选中的备选属性置为已选中状态 - 用于编辑的情况
					reset_property_listing();
					bmChangeHeight();
				}).fail(function(e) {

				});
			}, self._delay);
		}
	};

	$('#content_listing').datagrid(
			{
				dataSource : new DataGridDataSource({
					// Column definitions for Datagrid
					columns : [ {
						property : 'checkbox',
						label : ''
					},  {
						property : 'linkpropertyId',
						label : 'ID',
						sortable : false
					},{
						property : 'name',
						label : '连接属性名称',
						sortable : false
					},{
						property : 'sortOrder',
						label : '排序号',
						sortable : false
					},{
						property : 'remark',
						label : '备注',
						sortable : false
					} ],
					formatter : function(items) {
						$.each(items, function(index, item) {
							item.checkbox = '<input type="checkbox" name="post[]" id="select_single_p_' + item.linkpropertyId + '" class="select-single" data-type="p" value="'
									+ item.linkpropertyId + '">';
						});
					}
				}),
				loadingHTML : '<span><img src="' + STATIC_URL + '/images/loading.gif"><i class="fa fa-info-sign text-muted" "></i>正在加载……</span>',
				itemsText : '项',
				itemText : '项',
				dataOptions : {
					pageIndex : 0,
					pageSize : 15
				}
			});
}