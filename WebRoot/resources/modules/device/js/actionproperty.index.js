$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');

	/**
	 * 刷新或搜索
	 */
	$('body').delegate('.action-refresh, #action_search', 'click', function() {
		$('#content_listing').datagrid('reload');
	});

	/**
	 * 关键字搜索 - 支持回车
	 */
	$("input[name=key]").on('keypress', function(event) {
		if (event.which == '13') {
			$('#content_listing').datagrid('reload');
			return false;
		}
	});

	/**
	 * 单个删除
	 */
	$('body').delegate('.operate-delete', 'click', function() {
		var del = confirm('确定要删除吗？');
		if (!del) {
			return false;
		}
		
				var actionpropertyId = $(this).attr("actionpropertyId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/deviceActionproperty/delete',
			data : 'actionpropertyId=' + actionpropertyId,
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				if (data.status == 0) {
					$('#content_listing').datagrid('reload');
				} else {
					alert(data.msg);
				}
				return false;
			}
		});
	});
});

function columnsDefined() {
	return [
				{
					property: '_query',
					label: ''
				},
				{
					property : 'actionpropertyId',
					label : '属性ID',
					sortable : false
				},
				{
					property : 'name',
					label : '属性名称',
					sortable : false
				},
				{
					property : 'remark',
					label : '备注',
					sortable : false
				},
				{
					property : 'sortOrder',
					label : '排序号',
					sortable : false
				},
				{
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/deviceActionproperty/detail?actionpropertyId=' + item.actionpropertyId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/deviceActionproperty/edit?actionpropertyId=' + item.actionpropertyId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="actionpropertyId_' + item.actionpropertyId + '" actionpropertyId="' + item.actionpropertyId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

	});
}