var userList = null;
var amsList = null;
$(document).ready(function() {
//获取用户列表
	
	$.ajax({
		url:BASE_URL + '/adminUser/getUserList',
		dataType:'JSON',
		data:{ },
		type:'POST',
		async:false,
		complete:function(response){
			userList = response.responseJSON.data;
			
		}
	});
	//获取档案列表
	
	$.ajax({
		url:BASE_URL + '/amsArchives/getAmsList',
		dataType:'JSON',
		data:{ },
		type:'POST',
		async:false,
		complete:function(response){
			amsList = response.responseJSON.data;
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
		
				var actlogId = $(this).attr("actlogId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/amsArchivesActlog/delete',
			data : 'actlogId=' + actlogId,
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
	
	//获取档案列表
	
	$.ajax({
		url:BASE_URL + '/amsArchives/getAmsList',
		dataType:'JSON',
		data:{ },
		type:'POST',
		async:false,
		complete:function(response){
			asmList = response.responseJSON.data;
		}
	});
	
});

function columnsDefined() {
	return [
				{
					property: '_query',
					label: '查看详情'
				},
				/*{
					property : 'actlogId',
					label : '主键id',
					sortable : false
				},*/
				{
					property : 'archivesId',
					label : '档案标题',
					sortable : false
				},
				{
					property : 'userId',
					label : '操作人',
					sortable : false
				},
				{
					property : 'type',
					label : '操作类型',
					sortable : false
				},
				{
					property : 'status',
					label : '审核状态',
					sortable : false
				},
				/*{
					property : 'creator',
					label : '创建人',
					sortable : false
				},*/
				{
					property : 'ctime',
					label : '操作时间',
					sortable : false
				},
				/*{
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
		item._query = '<a href="'+BASE_URL + '/amsArchivesActlog/detail?actlogId=' + item.actlogId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/amsArchivesActlog/edit?actlogId=' + item.actlogId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="actlogId_' + item.actlogId + '" actlogId="' + item.actlogId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>';


		var strType = '未知';
		if (item.type == 0) {
			strType = '入库';
		} else if (item.type == 1) {
			strType = '借阅';
		} else if (item.type == 2) {
			strType = '上架';
		} else if (item.type == 3) {
			strType = '归还		';
		}else if (item.type == 4) {
			strType = '出库		';
		}
        item.type = strType;

		var strStatus = '未知';
		if (item.status == 0) {
			strStatus = '待审批';
		} else if (item.status == 1) {
			strStatus = '审核通过';
		} else if (item.status == 2) {
			strStatus = '审核拒绝		';
		}
        item.status = strStatus;
        if(item.modifier==null)item.modifier="";
     
        	
              for(var i=0;i<userList.length;i++){
        	     if(item.userId==userList[i].userId){
        		   item.userId=userList[i].fullname;
        		   break;
        	      }
              }
              for(var i=0;i<amsList.length;i++){
        	     if(item.archivesId==amsList[i].archivesId){
        		   item.archivesId=amsList[i].title;  
        		   break;
        	      }
              }
                  
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
		
	});
}