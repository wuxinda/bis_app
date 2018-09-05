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
		
				var id = $(this).attr("id");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/logUserImage/delete',
			data : 'id=' + id,
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
					property : 'id',
					label : '主键',
					sortable : false
				},
				{
					property : 'imageUrl',
					label : '图片地址',
					sortable : false
				},
				{
					property : 'logMsg',
					label : '日志信息',
					sortable : false
				},
				{
					property : 'time',
					label : '创建时间',
					sortable : false
				},
				{
					property : 'storeId',
					label : '库房id',
					sortable : false
				},
				{
					property : 'storeAreaId',
					label : '库区id',
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
		item._query = '<a href="'+BASE_URL + '/logUserImage/detail?id=' + item.id + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a onclick="image("' + IMG_URL+'/img'+item.imageUrl
				+ '")" class="operating-edit" title="编辑"><i class="fa fa-search-plus"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="id_' + item.id + '" id="' + item.id
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

		item.time = dateConverter(item.time, PATTERN_ENUM.datetime);
	});
}
function image(img){
	$("#tanchu_img").html("<img src="+img+"/>")
}