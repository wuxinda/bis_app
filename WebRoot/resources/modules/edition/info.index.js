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
		
		        var editionId = $(this).attr("editionId");
		        
		/* 执行 */
		$.ajax({
			type : 'post',
			url : BASE_URL + '/editionInfo/delete',
			data : 'editionId=' + editionId,
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
			
//	$('body').delegate('.operate-publish', 'click', function() {
//		var editionId = "";
//		var editionName = "";
//		var editionNumber = "";
//		var editionType = "";
//		 editionId = $(this).attr("editionId");
//		 editionName = $(this).attr("editionName");
//		 editionNumber = $(this).attr("editionNumber");
//		 editionType = $(this).attr("editionType");
//		 if(editionType == '0'){
//			 editionType = "应用平台：IOS";
//		 }else if(editionType == '1'){
//			 editionType = "应用平台：安卓";
//		 }else {
//			 editionType="应用平台：IPAD";
//		 }
//		var remark = "";
//		var isPublish = $(this).attr("isPublish");	
//		var str="";
//		if(isPublish=="1"){
//			isPublish=0;
//			str=str+"确定要发布吗？"
//		}else{
//			isPublish=1;
//			str=str+"确定要取消发布吗？"
//		}
//		//alert(editionId)
//		remark = remark+$(this).attr("remark");
//		var del = confirm(editionName+"\n"+editionNumber+"\n"+editionType+"\n"+remark+"\n\n"+str);
//		if (!del) {
//			return false;
//		}	
//		/* 执行 */
//		$.ajax({
//			type : 'post',
//			url : BASE_URL + '/editionInfo/publish',
//			data : {'editionId':editionId,'isPublish':isPublish},
//			dataType : 'json', 
//			timeout : 10000,
//			success : function(data) {
//				if (data.status == 0) {
//					alert(data.msg);
//					$('#content_listing').datagrid('reload');					
//				} else {
//					alert(data.msg);
//				}
//				return false;
//			}
//		});
//	});	
});

function columnsDefined() {
	return [
				{
					property : 'editionId',
					property: '_query',
					label: ''
				},
				
				
				{
					property : 'editionName',
					label : '应用名称',
					sortable : false
				},
				
				{
					property : 'editionType',
					label : '应用平台',
					sortable : false
				},
				{
					property : 'sunType',
					label : '应用分类',
					sortable : false
				},{
					property : 'editionNumber',
					label : '版本号',
					sortable : false
				},{
					property : 'editionUrl',
					label : '上传文件',
					sortable : false
				},
//				{
//					property : 'editionCreator',
//					label : '版本创建者',
//					sortable : false
//				},
//				{
//					property : 'editionCreatetime',
//					label : '版本创建时间',
//					sortable : false
//				},
//				{
//					property : 'editionUpdater',
//					label : '版本更新者',
//					sortable : false
//				},
//				{
//					property : 'editionUpdatetime',
//					label : '版本更新时间',
//					sortable : false
//				},
				{
					property : 'isPublish',
					label : '是否发布',
					sortable : false
				},
				{
					property : 'remark',
					label : '备注',
					sortable : false
				},
				{
					property : '_action',
					label : '操作',
					sortable : false
				},
				{
					property : '_action1',
					label : '发布',
					sortable : false
				},
				{
					property : '_action2',
					label : '二维码',
					sortable : false
				} 
			];
}

function formatData(items) {
	$.each(items, function(index, item) {
		item._query = '<a href="'+BASE_URL + '/editionInfo/detail?editionId=' + item.editionId + '"  class="modal-detail"><i class="fa fa-search-plus" title="查看详情"></i></a>';
		item._action = '<a href="' + BASE_URL + '/editionInfo/edit?editionId=' + item.editionId
				+ '" class="operating-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;'
				+ '<a href="javascript:;" class="operate-delete" id="editionId_' + item.editionId + '" editionId="' + item.editionId
				+ '" title="删除"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;'
//		item._action1 = '<a href="javascript:;" title="发布" class="operate-publish" editionId='+ item.editionId+' editionNumber=版本号：' + item.editionNumber +' editionType=' + item.editionType+
//		' editionName=应用名称：' + item.editionName +' remark=备注：' + item.remark +' isPublish=' + item.isPublish+ '><i class="fa fa-circle"></i></a>';
		/*	if(item.remark==null||item.remark==""||item.remark==undefined){
			item.remark=" ";
		}*/
		var str="发布";
		if(item.isPublish==0){
			str="取消"
		}
	item._action1 ='<button type="button" style="background:gray" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever1='+item.editionNumber+' data-whatever2='+item.editionType+ ' data-whatever3='+item.sunType+' data-whatever4='+item.editionName+' data-whatever7='+item.isPublish+' data-whatever6='+item.editionId+' data-whatever5='+item.remark+'>'+str+'</button>';
	item._action2 ='<button type="button" style="background:gray" class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-whatever6='+item.editionId+'>'+'二维码'+'</button>';
	

        var strisPublish = '未知';
        if (item.isPublish == 0) {
        	strisPublish = '是';
        }else if (item.isPublish == 1) {
        	strisPublish = '否';
        } 
        item.isPublish = strisPublish;
        var str = "";
        if(item.editionType==0){//ios
        	if(item.sunType==001){
        		str="iPhone手机";
            }
        }else if(item.editionType==1){//anzhuo
        	if(item.sunType==001){
        		str="安卓手机";
            }else if(item.sunType==002){
        		str="固定列";
            }else if(item.sunType==003){
        		str="移动列";
            }
        }else if(item.editionType==2){//pad
        	if(item.sunType==001){
        		str="iPad";
            }
        }
        item.sunType = str;
        var strEditionType = '未知';
		if (item.editionType == 0) {
			strEditionType = 'IOS';
		} else if (item.editionType == 1) {
			strEditionType = '安卓';
		} else if (item.editionType == 2) {
			strEditionType = 'IPAD';
		}
        item.editionType = strEditionType;
		item.editionCreatetime = dateConverter(item.editionCreatetime, PATTERN_ENUM.datetime);
		item.editionUpdatetime = dateConverter(item.editionUpdatetime, PATTERN_ENUM.datetime);
	});
}
//发布
$('#exampleModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  var editionNum = button.data('whatever1')//版本号 // Extract info from data-* attributes
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	   var editionName = button.data('whatever4')  //名称
	   var editionType = button.data('whatever3')  //分类
	   var applicationLevel=button.data('whatever2') //平台
	   var remark=button.data('whatever5')
	   var editionId = button.data('whatever6')
	   var isPublish = button.data('whatever7')
	  /* var editionID = $(button).attr("data-whatever6")*/
	   var modal = $(this)		   
      if(applicationLevel == '0'){//ios
      	if(editionType==001){
      		editionType="iPhone手机";
          }
      }else if(applicationLevel==1){//anzhuo
      	if(editionType==001){
      		editionType="安卓手机";
          }else if(editionType==002){
          	editionType="固定列";
          }else if(editionType==003){
          	editionType="移动列";
          }
      }else if(applicationLevel==2){//pad
      	if(editionType==001){
      		editionType="iPad";
          }
      }   
	   if(applicationLevel == '0'){
		   applicationLevel = "IOS";
		 }else if(applicationLevel == '1'){
			 applicationLevel = "安卓";
		 }else {
			 applicationLevel="IPAD";
		 }
	   var mod = "发布应用"
	   if(isPublish==0){
		   mod = "取消发布"
	   }
	  modal.find('.modal-title').text(mod)
	  modal.find('.modal-body input[id="application-name"]').val(editionName)
	  modal.find('.modal-body input[id="application-level"]').val(applicationLevel)
	  modal.find('.modal-body input[id="application-classify"]').val(editionType)
	  modal.find('.modal-body input[id="edition-number"]').val(editionNum)
	  modal.find('.modal-body textarea[id="remark"]').val(remark)	  
	  $("#submit").click(function(){
		  $.ajax({
				type : 'post',
				url : BASE_URL + '/editionInfo/publish',
				data : {'editionId':editionId,'isPublish':(isPublish==1?0:1)},
				dataType : 'json', 
				timeout : 10000,
				success : function(data) {
					if (data.status == 0) {
							alert(data.msg);
						modal.modal('hide')
						$('#content_listing').datagrid('reload');					
					}else {
						//alert(data.msg);
					}
					return false;
				}
			});
		  $("#submit").unbind();  
	  });
	})
	 //二维码生成js
	$('#myModal').on('show.bs.modal',function (event) {
		//二维码设置
		$("#qrcode").empty();
		  var qrcode = new QRCode('qrcode', {
		  text: 'your content',
		  width: 256,
		  height: 256,
		  colorDark : '#000000',
		  colorLight : '#ffffff',
		  correctLevel : QRCode.CorrectLevel.H
		});
	  //获取版本id
	   var modal = $(this)
	   var button = $(event.relatedTarget) // Button that triggered the modal
	   var editionId = button.data('whatever6')
	  //获取版本信息
	   $.ajax({
			type : 'get',
			url : BASE_URL + '/editionInfo/detailById',
			data : 'editionId=' + editionId,
			dataType : 'json', 
			timeout : 10000,
			success : function(data) {
				var edition=data.data
				qrcode.makeCode('{"editionId":'+edition.editionId+','
					+'"editionNumber":'+edition.editionNumber+','
					+'"editionName":'+edition.editionName+','
					+'"editionUrl":'+edition.editionUrl+','
					+'"editionType":'+edition.editionType+','
					+'"editionCreator":'+edition.editionCreator+','
					+'"editionCreatetime":'+edition.editionCreatetime+','
					+'"editionUpdater":'+edition.editionUpdater+','
					+'"editionUpdatetime":'+edition.editionUpdatetime+','
					+'"remark":'+edition.remark+','
					+'"isPublish":'+edition.isPublish+','
					+'"sunType":'+edition.sunType+'}')
								
			}
		});
	   //生成二维码
	   qrcode.makeCode("\r")
	  
	})