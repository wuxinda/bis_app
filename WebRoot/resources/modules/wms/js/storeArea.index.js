$(document).ready(function() {
	/**
	 * 获取库房列表，匹配页面展示库房名称
	 */
	$.ajax({
		url:BASE_URL + '/wmsStore/getWmsStore',
		dataType:'JSON',
		type:'POST',
		complete:function(response){
			wmsStore = response.responseJSON.data;
		}
	});
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
		
				var stroreAreaId = $(this).attr("stroreAreaId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/wmsStoreArea/delete',
			data : 'stroreAreaId=' + stroreAreaId,
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
var wmsStore;
function columnsDefined() {
	return [
				{
					property: '_query',
					label: '查看详情'
				}/*,
				{
					property : 'stroreAreaId',
					label : '主键ID',
					sortable : false
				}*/,
				{
					property : 'storeId',
					label : '所属库房',
					sortable : false
				},
				{
					property : 'code',
						label : '库区代码',
					sortable : false
				},
				{
					property : 'name',
					label : '库区名称',
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
		item._query = '<a href="'+BASE_URL + '/wmsStoreArea/detail?stroreAreaId=' + item.stroreAreaId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/wmsStoreArea/edit?stroreAreaId=' + item.stroreAreaId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="stroreAreaId_' + item.stroreAreaId + '" stroreAreaId="' + item.stroreAreaId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '停用';
		} else if (item.status == 1) {
			strStatus = '在用		';
		}
		$.each(wmsStore,function(index, store){
			if(store.storeId==item.storeId)item.storeId=store.name;
		});
		if(item.modifier==null)item.modifier="";
        item.status = strStatus;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}