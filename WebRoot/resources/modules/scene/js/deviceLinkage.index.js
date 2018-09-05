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
		
				var linkageId = $(this).attr("linkageId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/sceneDeviceLinkage/delete',
			data : 'linkageId=' + linkageId,
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
					property : 'linkageId',
					label : '主键ID',
					sortable : false
				},
				{
					property : 'deviceId',
					label : '设备id',
					sortable : false
				},
				{
					property : 'sceneId',
					label : '场景id',
					sortable : false
				},
				{
					property : 'actionpropertyId',
					label : '设备操作属性id',
					sortable : false
				},
				{
					property : 'execSec',
					label : '执行间隔',
					sortable : false
				},
				{
					property : 'sortOrder',
					label : '',
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
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/sceneDeviceLinkage/detail?linkageId=' + item.linkageId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/sceneDeviceLinkage/edit?linkageId=' + item.linkageId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="linkageId_' + item.linkageId + '" linkageId="' + item.linkageId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}