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
		
				var messageId = $(this).attr("messageId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/messageInfo/delete',
			data : 'messageId=' + messageId,
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
					property : 'messageId',
					label : '主键id',
					sortable : false
				},*/
				{
					property : 'type',
					label : '消息类型',
					sortable : false
				},
				{
					property : 'title',
					label : '标题',
					sortable : false
				},
				{
					property : 'content',
					label : '内容',
					sortable : false
				},
				{
					property : 'isPush',
					label : '是否推送',
					sortable : false
				},
				{
					property : 'isDel',
					label : '删除标示',
					sortable : false
				}/*,
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
				}*/,
				{
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/messageInfo/detail?messageId=' + item.messageId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/messageInfo/edit?messageId=' + item.messageId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="messageId_' + item.messageId + '" messageId="' + item.messageId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strType = '未知';
		if (item.type == 1) {
			strType = '系统消息';
		} else if (item.type == 2) {
			strType = '报警消息';
		} else if (item.type == 3) {
			strType = '审批消息';
		}
        item.type = strType;
     
		var strIsDel = '未知';
		if (item.isDel == 0) {
			strIsDel = '否';
		} else if (item.isDel == 1) {
			strIsDel = '是		';
		}
        item.isDel = strIsDel;
        // 是否推送 0.否  1.是
        var isPush = '未知';
        if (item.isPush == 0) {
        	isPush = '否';
        } else if (item.isPush == 1) {
        	isPush = '是		';
        }
        item.isPush = isPush;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}