var filingmethod = null;
var keepyear = null
$(document).ready(function() {
	$.ajax({
		url:BASE_URL + '/amsArchivesFilingmethod/filingmethod',
		dataType:'JSON',
		data:"",
		type:'Get',
		async:false,
		complete:function(response){					
			filingmethod = response.responseJSON.data;
		}
	});
	
	$.ajax({
		url:BASE_URL + '/amsArchivesKeepyear/keepyear',
		dataType:'JSON',
		data:"",
		type:'Get',
		async:false,
		complete:function(response){					
			keepyear = response.responseJSON.data;
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
	 * 档案操作
	 */
	$('body').delegate('.operate-operation', 'click', function() {
		var tittle=$(this).attr("title");
		var del = confirm('确定要'+tittle+'吗？');
		if (!del) {
			return false;
		}
		
		var archivesId = $(this).attr("archivesId");
		
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/amsArchives/operate',
			data : 'archivesId=' + archivesId+'&tittle=' + tittle,
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
	
	/**
	 * 通过库房得到库区
	 */
	var storeId=null;
	$('select[name="stroreId"]').change(function(){
		storeId=$(this).val();
		if(storeId==null||storeId==""){
			var optionHtml = "<option value=''>全部</option>";
			$('#stroreAreaId').html(optionHtml);
			return;
		}
		$.ajax({
			url:BASE_URL + '/wmsStoreArea/getStoreAreaByStoreId',
			dataType:'JSON',
			data:{storeId:storeId},
			type:'POST',
			async:false,
			complete:function(response){
				var datas = response.responseJSON.data;
				var optionHtml = "<option value=''>全部</option>";
				$.each(datas,function(index, data){
					optionHtml +='<option value="'+data.stroreAreaId+'">'+data.name+'</option>';
				});
				$('#stroreAreaId').html(optionHtml);
			}
		});
	});
});

			
			

function columnsDefined() {
	return [
				{
					property: '_query',
					label: '查看详情'
				}/*,
				{
					property : 'archivesId',
					label : '主键ID',
					sortable : false
				}*/,
				{
					property : 'rfidnum',
					label : 'RFID号',
					sortable : false
				},
				{
					property : 'archivesTypeName',
					label : '档案类型',
					sortable : false
				},
				/*{
					property : 'filingmethod',
					label : '立卷方式',
					sortable : false
				},
				{
					property : 'roomnum',
					label : '室编档号',
					sortable : false
				},
				{
					property : 'archiveno',
					label : '馆编档号',
					sortable : false
				},*/
				{
					property : 'title',
					label : '正题名',
					sortable : false
				},
				{
					property : 'paratitle',
					label : '并列标题',
					sortable : false
				},
				{
					property : 'keepyear',
					label : '保管年限',
					sortable : false
				},
				{
					property : 'security',
					label : '密级',
					sortable : false
				},
				{
					property : 'storeplace',
					label : '存址',
					sortable : false
				},
				/*{
					property : 'subject',
					label : '主题词',
					sortable : false
				},
				{
					property : 'keyword',
					label : '关键词',
					sortable : false
				},*/
				{
					property : 'inflag',
					label : '在库状态',
					sortable : false
				}/*,
				{
					property : 'playbackstarttime',
					label : '视频回放开始时间',
					sortable : false
				},
				{
					property : 'inoutUserId',
					label : '临时记录出入库人',
					sortable : false
				}*/,
				{
					property : 'checkStatus',
					label : '盘点状态',
					sortable : false
				},
				{
					property : 'nowNumber',
					label : '现有数量',
					sortable : false
				},
				{
					property : 'totalNumber',
					label : '总数量',
					sortable : false
				}/*,
				{
					property : 'stroreId',
					label : '所属库房ID',
					sortable : false
				},
				{
					property : 'stroreAreaId',
					label : '所属库区ID',
					sortable : false
				},
				{
					property : 'storeColumn',
					label : '所属列',
					sortable : false
				},
				{
					property : 'storeSection',
					label : '所属节',
					sortable : false
				},
				{
					property : 'storeLayer',
					label : '所属层',
					sortable : false
				},
				{
					property : 'storeLr',
					label : '方位左右',
					sortable : false
				},
				{
					property : 'remark',
					label : '备注',
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
				}*/,
				{
					property : '_action',
					label : '操作',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/amsArchives/detail?archivesId=' + item.archivesId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/amsArchives/edit?archivesId=' + item.archivesId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				
				+ '<a href="javascript:;" class="operate-operation" id="archivesId_' + item.archivesId + '" archivesId="' + item.archivesId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;'
		if(item.checkStatus=='0'){
			item._action +='<a href="javascript:;" class="operate-operation" id="archivesId_' + item.archivesId + '" archivesId="' + item.archivesId
			+ '" title="入库"><i class="fa fa-sign-in"></i></a>&nbsp;&nbsp;'
		}	
		if(item.checkStatus=='5'){
			item._action +='<a href="javascript:;" class="operate-operation" id="archivesId_' + item.archivesId + '" archivesId="' + item.archivesId
				+ '" title="借阅"><i class="fa fa-eye"></i></a>&nbsp;&nbsp;'
		}	
		if(item.checkStatus=='4'){
			item._action +='<a href="javascript:;" class="operate-operation" id="archivesId_' + item.archivesId + '" archivesId="' + item.archivesId
			+ '" title="上架"><i class="fa fa-upload"></i></a>&nbsp;&nbsp;'
		}
		if(item.checkStatus=='6'){
			item._action +='<a href="javascript:;" class="operate-operation" id="archivesId_' + item.archivesId + '" archivesId="' + item.archivesId
			+ '" title="归还"><i class="fa fa-eye-slash"></i></a>&nbsp;&nbsp;';
		}
		/*// 立卷方式 0.文件级 1.案卷级
		if(item.filingmethod=='0')item.filingmethod='文件级';
		if(item.filingmethod=='1')item.filingmethod='案卷级';*/
		
		/**
		 * 档案的外键ID和外键所引用表的主键ID比较，相等将外键所对应的表的name字段值赋值给档案表的外键ID.
		 */		
		for(var i=0;i<filingmethod.length;i++){
		 if(filingmethod[i].archivesFilingmethodId==item.filingmethod)
			 item.filingmethod=filingmethod[i].name;
		}
		
		
		
		// 保管年限 0.永久 1.长期 2.短期 3.10年 4.30年
		/*if(item.keepyear=='0')item.keepyear='永久';
		if(item.keepyear=='1')item.keepyear='长期';
		if(item.keepyear=='2')item.keepyear='短期 ';
		if(item.keepyear=='3')item.keepyear='10年';
		if(item.keepyear=='4')item.keepyear='30年';*/
		
		
		for(var i=0;i<keepyear.length;i++){
			if(keepyear[i].amsArchivesKeepyear==item.keepyear)
					item.keepyear=keepyear[i].name;
			}
		
		
		
		
		
		// 密级 0.普通 1.秘密 2.机密 3.绝密
		if(item.security=='0')item.security='普通';
		if(item.security=='1')item.security='秘密';
		if(item.security=='2')item.security='机密 ';
		if(item.security=='3')item.security='绝密';
		// 在库状态 在库状态 0:在库 1.出库
		if(item.inflag=='0')item.inflag='在库';
		if(item.inflag=='1')item.inflag='出库';
		// 盘点状态 0.待入库 1.入库审批中 2.上架审批中3.借阅审批中4.待上架5.在库6.待归还
		if(item.checkStatus=='0')item.checkStatus='待入库';
		if(item.checkStatus=='1')item.checkStatus='入库审批中';
		if(item.checkStatus=='2')item.checkStatus='上架审批中';
		if(item.checkStatus=='3')item.checkStatus='借阅审批中';
		if(item.checkStatus=='4')item.checkStatus='待上架';
		if(item.checkStatus=='5')item.checkStatus='在库';
		if(item.checkStatus=='6')item.checkStatus='待归还';
		if(item.checkStatus=='7')item.checkStatus='出库';
		if(item.checkStatus=='8')item.checkStatus='待出库';
		if(item.checkStatus=='9')item.checkStatus='出库审批中';
		if(item.checkStatus=='10')item.checkStatus='任务操作中';
		item.playbackstarttime = dateConverter(item.playbackstarttime, PATTERN_ENUM.datetime);
		item.ctime = dateConverter(item.ctime, PATTERN_ENUM.datetime);
		item.mtime = dateConverter(item.mtime, PATTERN_ENUM.datetime);
	});
}