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
		
				var pvalueId = $(this).attr("pvalueId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/deviceStoreLinkpropertyValue/delete',
			data : 'pvalueId=' + pvalueId,
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
					property : 'pvalueId',
					label : '主键ID',
					sortable : false
				},
				{
					property : 'deviceId',
					label : '设备id',
					sortable : false
				},
				{
					property : 'linkpropertyId',
					label : '设备连接属性id',
					sortable : false
				},
				{
					property : 'name',
					label : '属性值名称',
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
		item._query = '<a href="'+BASE_URL + '/deviceStoreLinkpropertyValue/detail?pvalueId=' + item.pvalueId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/deviceStoreLinkpropertyValue/edit?pvalueId=' + item.pvalueId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="pvalueId_' + item.pvalueId + '" pvalueId="' + item.pvalueId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

	});
}