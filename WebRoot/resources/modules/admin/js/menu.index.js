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
	 * 下拉选择触发处理
	 */
	$("select[name=menuId],select[name=grade]").change(function(){
		$('#content_listing').datagrid('reload');
   	return false;
	});
	

	/**
	 * 单个删除
	 */
	$('body').delegate('.operate-delete', 'click', function() {
		var del = confirm('确定要删除吗？');
		if (!del) {
			return false;
		}
		
				var menuId = $(this).attr("menuId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/adminMenu/delete',
			data : 'menuId=' + menuId,
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
					property : 'menuId',
					label : '菜单id',
					sortable : false
				},
				{
					property : 'name',
					label : '菜单名称',
					sortable : false
				},
				{
					property : 'pid',
					label : '父级菜单id',
					sortable : false
				},
				{
					property : 'grade',
					label : '菜单级别',
					sortable : false
				},
				{
					property : 'url',
					label : '链接',
					sortable : false
				},
				{
					property : 'icon',
					label : 'icon图标',
					sortable : false
				},
				{
					property : 'iconBg',
					label : 'icon背景',
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
				/*{
					property : 'creator',
					label : '创建人',
					sortable : false
				},
				{
					property : 'ctime',
					label : '创建时间',
					sortable : false
				},*/
				/*{
					property : 'modifier',
					label : '修改人',
					sortable : false
				},
				{
					property : 'mtime',
					label : '修改时间',
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
		item._query = '<a href="'+BASE_URL + '/adminMenu/detail?menuId=' + item.menuId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/adminMenu/edit?menuId=' + item.menuId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="menuId_' + item.menuId + '" menuId="' + item.menuId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';
		
		var strGrade = '未知';
		if (item.grade == 1) {
			strGrade = '一级菜单';
		} else if (item.grade == 2) {
			strGrade = '二级菜单';
		}
        item.grade = strGrade;

		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '不可用';
		} else if (item.status == 1) {
			strStatus = '可用';
		}
        item.status = strStatus;
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
		
		item.icon = '<i class="fa icon '+item.icon+'"></i>';
		item.iconBg = '<label class="label '+item.iconBg+' col-sm-5">&nbsp;</label>';
		
	});
}