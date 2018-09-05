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
		
				var alarmTypeId = $(this).attr("alarmTypeId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/alarmType/delete',
			data : 'alarmTypeId=' + alarmTypeId,
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
				/*{
					property : 'alarmTypeId',
					label : '',
					sortable : false
				},*/
				{
					property : 'alarmTypeName',
					label : '报警名称',
					sortable : false
				},
				{
					property : 'alarmLevel',
					label : '报警级别',
					sortable : false
				},
				{
					property : 'sortOrder',
					label : '排序号',
					sortable : false
				},
				{
					property : 'remark',
					label : '备注',
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
					label : '最后修改时间',
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
		item._query = '<a href="'+BASE_URL + '/alarmType/detail?alarmTypeId=' + item.alarmTypeId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/alarmType/edit?alarmTypeId=' + item.alarmTypeId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="alarmTypeId_' + item.alarmTypeId + '" alarmTypeId="' + item.alarmTypeId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;'
                +'<a href="' + BASE_URL + '/alarmType/msgPushCfg?alarmTypeId=' + item.alarmTypeId
                + '" class="operating-edit" title="消息推送属性"><i class="fa fa-gear">推送配置</i></a>';

		var strAlarmLevel = '未知';
		if (item.alarmLevel == 0) {
			strAlarmLevel = '低';
		} else if (item.alarmLevel == 1) {
			strAlarmLevel = '中';
		} else if (item.alarmLevel == 2) {
			strAlarmLevel = '高';
		}
        item.alarmLevel = strAlarmLevel;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}