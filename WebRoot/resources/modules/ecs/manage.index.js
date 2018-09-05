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
		
				var ecsManageId = $(this).attr("ecsManageId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/ecsManage/delete',
			data : 'ecsManageId=' + ecsManageId,
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
					property : 'ecsManageId',
					label : '主键ID',
					sortable : false
				},
				{
					property : 'cYear',
					label : '年份',
					sortable : false
				},
				{
					property : 'cMonth',
					label : '月份',
					sortable : false
				},
				{
					property : 'cDay',
					label : '日',
					sortable : false
				},
				{
					property : 'storeId',
					label : '库房id',
					sortable : false
				},
				{
					property : 'deviceTypeId',
					label : '设备类型id',
					sortable : false
				},
				{
					property : 'deviceId',
					label : '设备id',
					sortable : false
				},
				{
					property : 'actualEc',
					label : '实际能耗',
					sortable : false
				},
				{
					property : 'targetEc',
					label : '目标能耗',
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
					label : '最后一次更新时间',
					sortable : false
				},
				{
					property : 'remark',
					label : '备注',
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
		item._query = '<a href="'+BASE_URL + '/ecsManage/detail?ecsManageId=' + item.ecsManageId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/ecsManage/edit?ecsManageId=' + item.ecsManageId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="ecsManageId_' + item.ecsManageId + '" ecsManageId="' + item.ecsManageId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}