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
		
				var categoryId = $(this).attr("categoryId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/deviceCategory/delete',
			data : 'categoryId=' + categoryId,
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
					property : 'categoryId',
					label : 'ID',
					sortable : false
				},
				{
					property : 'name',
					label : '分类名称',
					sortable : false
				},
				{
					property : 'status',
					label : '状态',
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
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/deviceCategory/detail?categoryId=' + item.categoryId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/deviceCategory/edit?categoryId=' + item.categoryId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="categoryId_' + item.categoryId + '" categoryId="' + item.categoryId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;'
                +'<a href="' + BASE_URL + '/deviceCategory/categoryLinkpropertyJump?categoryId=' + item.categoryId
                + '" class="operating-edit" title="连接属性"><i class="fa fa-gear">连接配置</i></a>&nbsp;&nbsp;'
                +'<a href="' + BASE_URL + '/deviceCategory/categoryActionpropertyJump?categoryId=' + item.categoryId
                + '" class="operating-edit" title="操作属性"><i class="fa fa-gear">操作配置</i></a>';
		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '停用';
		} else if (item.status == 1) {
			strStatus = '在用';
		}
        item.status = strStatus;
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