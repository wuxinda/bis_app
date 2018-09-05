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
		
				var categoryBrandId = $(this).attr("categoryBrandId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/deviceCategoryBrand/delete',
			data : 'categoryBrandId=' + categoryBrandId,
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
					property : 'categoryBrandId',
					label : '主键ID',
					sortable : false
				},
				{
					property : 'categoryId',
					label : '设备分类id',
					sortable : false
				},
				{
					property : 'brandId',
					label : '设备品牌id',
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
		item._query = '<a href="'+BASE_URL + '/deviceCategoryBrand/detail?categoryBrandId=' + item.categoryBrandId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/deviceCategoryBrand/edit?categoryBrandId=' + item.categoryBrandId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="categoryBrandId_' + item.categoryBrandId + '" categoryBrandId="' + item.categoryBrandId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

	});
}