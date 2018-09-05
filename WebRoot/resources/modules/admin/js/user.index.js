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
		
				var userId = $(this).attr("userId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/adminUser/delete',
			data : 'userId=' + userId,
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
					property : 'userId',
					label : '用户id',
					sortable : false
				},
				{
					property : 'userName',
					label : '用户名',
					sortable : false
				},
				/*{
					property : 'password',
					label : '密码',
					sortable : false
				},
				{
					property : 'salt',
					label : '混淆码',
					sortable : false
				},*/
				{
					property : 'phone',
					label : '联系电话',
					sortable : false
				},
				/*{
					property : 'email',
					label : '邮箱',
					sortable : false
				},*/
				{
					property : 'fullname',
					label : '名称',
					sortable : false
				},
				/*{
					property : 'nickname',
					label : '昵称',
					sortable : false
				},*/
				/*{
					property : 'enName',
					label : '英文名全称',
					sortable : false
				},
				{
					property : 'initial',
					label : '英文名首字母',
					sortable : false
				},*/
			/*	{
					property : 'sex',
					label : '性别',
					sortable : false
				},*/
				/*{
					property : 'status',
					label : '状态',
					sortable : false
				},*/
				{
					property : 'isOnline',
					label : '是否在线',
					sortable : false
				},
				{
					property : 'loginTime',
					label : '最后登录时间',
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
					label : '修改时间',
					sortable : false
				},*/
				{
					property : 'userType',
					label : '用户类型',
					sortable : false
				},
				{
					property : 'userrole',
					label : '用户角色',
					sortable : false
				},
				/*{
					property : 'merchantId',
					label : '商户id',
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
		item._query = '<a href="'+BASE_URL + '/adminUser/detail?userId=' + item.userId + '"  class="modal-detail bmpms-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/adminUser/edit?userId=' + item.userId
				+ '" class="operating-edit hide bmpms-edit" title="编辑"><i class="fa fa-pencil bmpms-edit"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete hide bmpms-delete" userId="' + item.userId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strSex = '未知';
		if (item.sex == 0) {
			strSex = '未知';
		} else if (item.sex == 1) {
			strSex = '男';
		} else if (item.sex == 2) {
			strSex = '女';
		}
        item.sex = strSex;

		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '未审核';
		} else if (item.status == 1) {
			strStatus = '审核通过';
		} else if (item.status == 2) {
			strStatus = '审核不通过';
		}
        item.status = strStatus;

		var strIsOnline = '未知';
		if (item.isOnline == 0) {
			strIsOnline = '不在线';
		} else if (item.isOnline == 1) {
			strIsOnline = '在线';
		}
        item.isOnline = strIsOnline;
		item.loginTime = dateConverter(item.loginTime, PATTERN_ENUM.datetime);
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);

		var strUserType = '未知';
		if (item.userType == 0) {
			strUserType = '后台用户';
		} else if (item.userType == 1) {
			strUserType = '领导者端用户';
		} else if(item.userType == 2){
			strUserType = '操作者端用户';
		}
        item.userType = strUserType;
        if (item.email == null)item.email ="";
        if (item.userrole == null)item.userrole ="";
	});
}