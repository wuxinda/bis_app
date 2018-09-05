var userList = null;
var categoryList = null;
var deviceList = null;
var storeList = null;
var storeAreaList = null;
$(document).ready(function() {
					// 获取用户列表
					$.ajax({
						url : BASE_URL + '/adminUser/getUserList',
						dataType : 'JSON',
						data : {},
						type : 'POST',
						async: false,
						complete : function(response) {
							userList = response.responseJSON.data;
						}
					});

					// 获取设备类型列表
					$.ajax({
						url : BASE_URL + '/deviceCategory/getcategoryList',
						dataType : 'JSON',
						data : {},
						type : 'POST',
						async: false,
						complete : function(response) {
							categoryList = response.responseJSON.data;

						}
					});

					// 获取设备列表
					$.ajax({
						url : BASE_URL + '/deviceManage/getStoreDeviceList',
						dataType : 'JSON',
						data : {},
						type : 'GET',
						async: false,
						complete : function(response) {
							deviceList = response.responseJSON.data;

						}
					});

					// 获取库房列表
					$.ajax({
						url : BASE_URL + '/wmsStore/getWmsStore',
						dataType : 'JSON',
						data : {},
						type : 'POST',
						async: false,
						complete : function(response) {
							storeList = response.responseJSON.data;

						}
					});

					// 获取库区列表
					$.ajax({
						url : BASE_URL + '/wmsStoreArea/getStoreAreaByStoreId',
						dataType : 'JSON',
						data : {},
						type : 'POST',
						async: false,
						complete : function(response) {
							storeAreaList = response.responseJSON.data;

						}
					});

					loadDataGridContent(columnsDefined(), 'formatData');

					/**
					 * 刷新或搜索
					 */
					$('body').delegate('.action-refresh, #action_search',
							'click', function() {
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

						var alarmId = $(this).attr("alarmId");

						/* 执行 */
						$.ajax({
							type : 'post',
							url : BASE_URL + '/alarmManage/delete',
							data : 'alarmId=' + alarmId,
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
					/**
					 * 通过库房得到库区
					 */
					var storeId = null;
					$('select[name="storeId"]').change(
									function() {
										storeId = $(this).val();
										if (storeId == null || storeId == "") {
											var optionHtml = "<option value=''>全部</option>";
											$('#stroreAreaId').html(optionHtml);
											return;
										}
										$.ajax({
													url : BASE_URL
															+ '/wmsStoreArea/getStoreAreaByStoreId',
													dataType : 'JSON',
													data : {
														storeId : storeId
													},
													type : 'POST',
													complete : function(
															response) {
														var datas = response.responseJSON.data;
														var optionHtml = "<option value=''>全部</option>";
														$.each(datas,function(index,data) {
																			optionHtml += '<option value="'
																					+ data.stroreAreaId
																					+ '">'
																					+ data.name
																					+ '</option>';
																		});
														$('#stroreAreaId').html(optionHtml);
													}
												});
									});
				});

function columnsDefined() {
	return [ {
		property : '_query',
		label : ''
	},
	/*
	 * { property : 'alarmId', label : '主键ID', sortable : false },
	 */
	{
		property : 'categoryId',
		label : '设备分类',
		sortable : false
	}, 
//	{
//		property : 'deviceId',
//		label : '设备',
//		sortable : false
//	},
	{
		property : 'storeId',
		label : '所属库房',
		sortable : false
	}, {
		property : 'stroreAreaId',
		label : '所属库区',
		sortable : false
	}, {
		property : 'deviceName',
		label : '设备名称',
		sortable : false
	},
	/*
	 * { property : 'sortOrder', label : '序号', sortable : false },
	 */
	{
		property : 'level',
		label : '报警级别',
		sortable : false
	}, {
		property : 'status',
		label : '状态',
		sortable : false
	}, {
		property : 'remark',
		label : '报警原因',
		sortable : false
	},
	/*
	 * { property : 'creator', label : '创建人', sortable : false },
	 */
	{
		property : 'ctime',
		label : '报警时间',
		sortable : false
	}, {
		property : 'modifier',
		label : '处理人',
		sortable : false
	}, {
		property : 'mtime',
		label : '通知/处理时间',
		sortable : false
	}, {
		property : '_action',
		label : '操作',
		sortable : false
	} ];
}

function formatData(items) {// list<map>
	$.each(items,function(index, item) {
						item._query = '<a href="'
								+ BASE_URL
								+ '/alarmManage/detail?alarmId='
								+ item.alarmId
								+ '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
						item._action = '<a href="'
								+ BASE_URL
								+ '/alarmManage/edit?alarmId='
								+ item.alarmId
								+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
								+ '<a href="javascript:;" class="operate-delete" id="alarmId_'
								+ item.alarmId
								+ '" alarmId="'
								+ item.alarmId
								+ '" title="删除"><i class="fa fa-trash-o"></i></a>';

						var strStatus = '未知';
						if (item.status == 0) {
							strStatus = '未处理';
						} else if (item.status == 1) {
							strStatus = '已处理';
						}
						if(item.modifier==null)item.modifier="";
						item.status = strStatus;
						// 报警级别 0.低 1.中 2.高
						var level = '未知';
						if (item.level == 0) {
							level = '低';
						} else if (item.level == 1) {
							level = '中';
						} else if (item.level == 2) {
							level = '高';
						}
						item.level = level;
						item.ctime = dateConverter(item.ctime,
								PATTERN_ENUM.datetime);
						item.mtime = dateConverter(item.mtime,
								PATTERN_ENUM.datetime);

					
							
						for (var i = 0; i < userList.length; i++) {
							if (item.modifier == userList[i].userId) {
								item.modifier = userList[i].fullname;
								break;
							}
						}
						for (var i = 0; i < deviceList.length; i++) {
							if (item.deviceId == deviceList[i].deviceId) {
								item.deviceId = deviceList[i].name;	
								break;
							}
						}
						for (var i = 0; i < categoryList.length; i++) {
							if (item.categoryId == categoryList[i].categoryId) {
								item.categoryId = categoryList[i].name;	
								break;
							}
						}
						for (var i = 0; i < storeList.length; i++) {
							if (item.storeId == storeList[i].storeId) {
								item.storeId = storeList[i].name;	
								break;
							}
						}
						for (var i = 0; i < storeAreaList.length; i++) {
							if (item.stroreAreaId == storeAreaList[i].stroreAreaId) {
								item.stroreAreaId = storeAreaList[i].name;		
								break;
							}
						}
        });
}