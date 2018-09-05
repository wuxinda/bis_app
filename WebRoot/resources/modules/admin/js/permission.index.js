$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
	/**
	 * 通过父级菜单获得子级列表
	 */
	$('select[name="firstMenuId"]').change(function(){
		var menuId = $(this).val();
		if(menuId==0){
			return;
		}
		$.ajax({
			url:BASE_URL + '/adminMenu/getAdminMenuByPid',
			dataType:'JSON',
			data:{pid:menuId},
			type:'POST',
			complete:function(response){
				var data = response.responseJSON.data;
				var optionHtml = "<option value=0>请选择&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>";
				$.each(data,function(index, data){
					optionHtml +='<option data-link="'+data.url+'" value="'+data.menuId+'">'+data.name+'</option>';
				});
				$('#secondMenuId').html(optionHtml);
			}
		});
	});
	
	
	
	
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
		
				var permissionId = $(this).attr("permissionId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/adminPermission/delete',
			data : 'permissionId=' + permissionId,
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
					property : 'permissionId',
					label : '权限id',
					sortable : false
				},
				{
					property : 'name',
					label : '权限名称',
					sortable : false
				},
				{
					property : 'code',
					label : '权限代码',
					sortable : false
				},
				{
					property : 'menuId',
					label : '二级菜单id',
					sortable : false
				},
				{
					property : 'url',
					label : '链接=二级菜单url',
					sortable : false
				},
				/*{
					property : 'sortOrder',
					label : '序号',
					sortable : false
				},*/
				{
					property : 'status',
					label : '状态',
					sortable : false
				},
				/*{
					property : 'userType',
					label : '用户类型',
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
		item._query = '<a href="'+BASE_URL + '/adminPermission/detail?permissionId=' + item.permissionId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/adminPermission/edit?permissionId=' + item.permissionId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" permissionId="' + item.permissionId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '不使用';
		} else if (item.status == 1) {
			strStatus = '使用';
		}
        item.status = strStatus;

		var strUserType = '未知';
		if (item.userType == 1) {
			strUserType = '平台';
		} else if (item.userType == 2) {
			strUserType = '商户';
		}
        item.userType = strUserType;
	});
}