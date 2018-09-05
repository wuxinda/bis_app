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
		
				var storeId = $(this).attr("storeId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/wmsStore/delete',
			data : 'storeId=' + storeId,
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
					label: '查看详情'
				},
				/*{
					property : 'storeId',
					label : '主键ID',
					sortable : false
				},*/
				{
					property : 'code',
					label : '库房代码',
					sortable : false
				},
				{
					property : 'name',
					label : '库房名称',
					sortable : false
				},
				{
					property : 'buildingNo',
					label : '所属楼号',
					sortable : false
				},
				{
					property : 'floorNo',
					label : '所属层数',
					sortable : false
				},
				{
					property : 'sortOrder',
					label : '序号',
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
				/*{
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
				},*/
				{
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/wmsStore/detail?storeId=' + item.storeId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/wmsStore/edit?storeId=' + item.storeId
		+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="storeId_' + item.storeId + '" storeId="' + item.storeId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;'
				+'<a href="' + BASE_URL + '/wmsUser/setWmsUser?storeId=' + item.storeId
		        + '" class="operating-edit" title="分配人员"><i class="fa fa-gear">分配人员</i></a>&nbsp;&nbsp;';


		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '停用';
		} else if (item.status == 1) {
			strStatus = '在用		';
		}
		if(item.modifier==null)item.modifier="";
        item.status = strStatus;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}