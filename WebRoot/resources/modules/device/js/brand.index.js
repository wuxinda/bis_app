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
		
				var brandId = $(this).attr("brandId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/deviceBrand/delete',
			data : 'brandId=' + brandId,
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
					property : 'brandId',
					label : '品牌ID',
					sortable : false
				},
				{
					property : 'name',
					label : '品牌名称',
					sortable : false
				},
				/*{
					property : 'pic',
					label : 'log图',
					sortable : false
				},*/
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
		item._query = '<a href="'+BASE_URL + '/deviceBrand/detail?brandId=' + item.brandId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/deviceBrand/edit?brandId=' + item.brandId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="brandId_' + item.brandId + '" brandId="' + item.brandId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;'
		        +'<a href="' + BASE_URL + '/deviceBrand/bindCategoryJump?brandId=' + item.brandId
		        + '" class="operating-edit" title="绑定分类"><i class="fa fa-gear"></i></a>';

		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}