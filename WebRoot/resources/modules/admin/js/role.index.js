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
		
				var roleId = $(this).attr("roleId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/adminRole/delete',
			data : 'roleId=' + roleId,
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
					property : 'roleId',
					label : '角色id',
					sortable : false
				},
				{
					property : 'name',
					label : '角色名',
					sortable : false
				},
				{
					property : 'description',
					label : '描述',
					sortable : false
				},
				/*{
					property : 'status',
					label : '状态',
					sortable : false
				},
				{
					property : 'creator',
					label : '创建人',
					sortable : false
				},
				{
					property : 'ctime',
					label : '创建时间',
					sortable : false
				},
				{
					property : 'modifier',
					label : '修改人',
					sortable : false
				},
				{
					property : 'mtime',
					label : '修改时间',
					sortable : false
				},
				{
					property : 'userType',
					label : '用户类型',
					sortable : false
				},*/
				/*{
					property : 'merchantId',
					label : '商户id',
					sortable : false
				},*/
				{
					property : 'rolePermission',
					label : '设置权限',
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
		item._query = '<a href="'+BASE_URL + '/adminRole/detail?roleId=' + item.roleId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		
		item.rolePermission = '<a href="' + BASE_URL + '/adminRole/rolePermission?roleId=' + item.roleId + '" class="load-content" title="设置权限"><i class="fa fa-gear"></i></a>'; 
        
		item._action = '<a href="' + BASE_URL + '/adminRole/edit?roleId=' + item.roleId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" roleId="' + item.roleId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		/*var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '不可用';
		} else if (item.status == 1) {
			strStatus = '可用';
		}
        item.status = strStatus;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);*/

		var strUserType = '未知';
		if (item.userType == 0) {
			strUserType = '后台管理员用户';
		} else if (item.userType == 1) {
			strUserType = '领导端用户';
		} else if (item.userType == 1) {
		    strUserType = '操作者端用户';
	    }
        item.userType = strUserType;
        
        
	});
}