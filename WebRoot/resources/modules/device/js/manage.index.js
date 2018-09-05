var storeAreaList = null;
$(document).ready(function() {
	//获取库区列表
	$.ajax({
		url:BASE_URL + '/wmsStoreArea/getStoreAreaByStoreId',
		dataType:'JSON',
		data:{ },
		type:'POST',
		async:false,
		complete:function(response){
			storeAreaList = response.responseJSON.data;
			
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
		
				var deviceId = $(this).attr("deviceId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/deviceManage/delete',
			data : 'deviceId=' + deviceId,
			dataType : 'json',
			timeout : 10000,
			async:false,
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
					property : 'deviceId',
					label : '设备ID',
					sortable : false
				},
				{
					property : 'categoryName',
					label : '设备分类',
					sortable : false
				},
				{
					property : 'brandName',
					label : '所属品牌',
					sortable : false
				},
				{
					property : 'storeName',
					label : '所属库房',
					sortable : false
				},
				{
					property : 'stroreAreaId',
					label : '所属库区',
					sortable : false
				},
				{
					property : 'name',
					label : '设备名称',
					sortable : false
				},
				{
					property : 'status',
					label : '状态',
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
					label : '最后一次更新时间',
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
		item._query = '<a href="'+BASE_URL + '/deviceManage/detail?deviceId=' + item.deviceId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/deviceManage/edit?deviceId=' + item.deviceId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="deviceId_' + item.deviceId + '" deviceId="' + item.deviceId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '停用';
		} else if (item.status == 1) {
			strStatus = '在用		';
		}
        item.status = strStatus;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
		
			
		      for(var i=0;i<storeAreaList.length;i++){
			    if(item.stroreAreaId==storeAreaList[i].stroreAreaId){
				    item.stroreAreaId=storeAreaList[i].name;
				    break;
			    }
		      }
		   
		if(item.stroreAreaId==null)item.stroreAreaId='';
	});
}