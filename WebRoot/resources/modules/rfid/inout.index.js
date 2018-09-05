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
		
				var rfidInoutId = $(this).attr("rfidInoutId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/rfidInout/delete',
			data : 'rfidInoutId=' + rfidInoutId,
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
					property : 'rfidInoutId',
					label : '主键ID',
					sortable : false
				},
				{
					property : 'archiveId',
					label : '档案id',
					sortable : false
				},
				{
					property : 'archiveno',
					label : '档案号',
					sortable : false
				},
				{
					property : 'tittle',
					label : '档案标题',
					sortable : false
				},
				{
					property : 'type',
					label : '出入类型',
					sortable : false
				},
				{
					property : 'status',
					label : '出入状态',
					sortable : false
				},
				{
					property : 'userId',
					label : '用户id',
					sortable : false
				},
				{
					property : 'username',
					label : '出入人员',
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
		item._query = '<a href="'+BASE_URL + '/rfidInout/detail?rfidInoutId=' + item.rfidInoutId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/rfidInout/edit?rfidInoutId=' + item.rfidInoutId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="rfidInoutId_' + item.rfidInoutId + '" rfidInoutId="' + item.rfidInoutId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strType = '未知';
		if (item.type == 0) {
			strType = '出库';
		} else if (item.type == 1) {
			strType = '入库';
		}
        item.type = strType;

		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '正常';
		} else if (item.status == 1) {
			strStatus = '异常';
		}
        item.status = strStatus;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}