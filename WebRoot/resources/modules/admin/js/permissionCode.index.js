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
		
				var permissionCodeId = $(this).attr("permissionCodeId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/adminPermissionCode/delete',
			data : 'permissionCodeId=' + permissionCodeId,
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
					property : 'permissionCodeId',
					label : '权限代码id',
					sortable : false
				},
				{
					property : 'code',
					label : '权限代码',
					sortable : false
				},
				{
					property : 'name',
					label : '权限名称',
					sortable : false
				},
				{
					property : 'sortOrder',
					label : '序号',
					sortable : false
				},
				{
					property : 'status',
					label : '状态',
					sortable : false
				},
				{
					property : 'description',
					label : '描述',
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
		item._query = '<a href="'+BASE_URL + '/adminPermissionCode/detail?permissionCodeId=' + item.permissionCodeId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/adminPermissionCode/edit?permissionCodeId=' + item.permissionCodeId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" permissionCodeId="' + item.permissionCodeId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '不可用';
		} else if (item.status == 1) {
			strStatus = '可用';
		}
        item.status = strStatus;
	});
}