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
		
				var hcsId = $(this).attr("hcsId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/perceptionHcs/delete',
			data : 'hcsId=' + hcsId,
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
					property : 'hcsId',
					label : '主键id',
					sortable : false
				},
				{
					property : 'deviceId',
					label : '设备ID',
					sortable : false
				},
				{
					property : 'temperature',
					label : '温度',
					sortable : false
				},
				{
					property : 'humidity',
					label : '湿度',
					sortable : false
				},
				{
					property : 'airStatus',
					label : '空调状态',
					sortable : false
				},
				{
					property : 'dryingStatus',
					label : '除湿状态',
					sortable : false
				},
				{
					property : 'wettingStatus',
					label : '增湿状态',
					sortable : false
				},
				{
					property : 'ventilationStatus',
					label : '通风状态',
					sortable : false
				},
				{
					property : 'cleansingStatus',
					label : '净化状态',
					sortable : false
				},
				{
					property : 'warningStatus',
					label : '警告状态',
					sortable : false
				},
				{
					property : 'collectDate',
					label : '采集时间',
					sortable : false
				},
				{
					property : 'remark',
					label : '',
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
		item._query = '<a href="'+BASE_URL + '/perceptionHcs/detail?hcsId=' + item.hcsId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/perceptionHcs/edit?hcsId=' + item.hcsId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="hcsId_' + item.hcsId + '" hcsId="' + item.hcsId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

		item.collectDate = dateConverter(item.collectDate, PATTERN_ENUM.datetime);
	});
}