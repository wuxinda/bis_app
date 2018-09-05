$(document).ready(function() {
	$('#startDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});
	$('#endDate').datepicker({format: 'yyyy-mm-dd', autoclose: true});
	
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
		
				var logId = $(this).attr("logId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/logUserAction/delete',
			data : 'logId=' + logId,
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
					property : 'logId',
					label : '日志id',
					sortable : false
				},
				{
					property : 'logType',
					label : '日志类型',
					sortable : false
				},
				{
					property : 'userid',
					label : '用户id',
					sortable : false
				},
				{
					property : 'url',
					label : '请求url',
					sortable : false
				},
				{
					property : 'name',
					label : '用户操作',
					sortable : false
				},
				/*{
					property : 'param',
					label : '请求参数',
					sortable : false
				},*/
				{
					property : 'ip',
					label : '用户ip',
					sortable : false
				},
				{
					property : 'logTime',
					label : '日志记录时间',
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
		item._query = '<a href="'+BASE_URL + '/logUserAction/detail?logId=' + item.logId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/logUserAction/edit?logId=' + item.logId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" logId="' + item.logId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strLogType = '未知';
		if (item.logType == 1) {
			strLogType = '后台日志';
		} else if (item.logType == 2) {
			strLogType = '手机端日志';
		} else if (item.logType == 3) {
			strLogType = 'web端日志';
		}
        item.logType = strLogType;
		item.logTime = dateConverter(item.logTime, PATTERN_ENUM.datetime);
	});
}