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
		
				var sceneId = $(this).attr("sceneId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/sceneManage/delete',
			data : 'sceneId=' + sceneId,
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
	
	/**
	 * 执行场景程序
	 */
	$('body').delegate('.operating-edit.execute', 'click', function() {
	   var sceneId = $(this).attr("sceneId");		
		/* 执行 */
		$.ajax({
			type : 'get',
			url : BASE_URL + '/sceneManage/executeScene',
			data : 'sceneId=' + sceneId,
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				alert(data.msg);
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
					property : 'sceneId',
					label : '主键ID',
					sortable : false
				},
				{
					property : 'name',
					label : '场景名称',
					sortable : false
				},
				{
					property : 'stroreName',
					label : '所属库房',
					sortable : false
				},
				{
					property : 'type',
					label : '场景类型',
					sortable : false
				},
				{
					property : 'status',
					label : '状态',
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
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/sceneManage/detail?sceneId=' + item.sceneId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/sceneManage/edit?sceneId=' + item.sceneId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="sceneId_' + item.sceneId + '" sceneId="' + item.sceneId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;'
		        +'<a href="' + BASE_URL + '/sceneManage/deviceLinkageJump?sceneId=' + item.sceneId
		        + '" class="operating-edit" title="添加设备联动"><i class="fa fa-gear"></i></a>&nbsp;&nbsp;'
		        +'<a href="javascript:;" class="operating-edit execute" sceneId='+item.sceneId+' title="执行场景模式"><i class="fa fa-caret-square-o-right"></i></a>';


		var strType = '未知';
		if (item.type == 0) {
			strType = '系统默认';
		} else if (item.type == 1) {
			strType = '用户自定义';
		}
        item.type = strType;

		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '停用';
		} else if (item.status == 1) {
			strStatus = '在用';
		}
        item.status = strStatus;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}