
var storeId=0;   //选中库房编号
var storeAreaId=0;  //选中库区编号
//选中列名字
//选择列
// var vmsChannel="";//视频通道
//var initVms = false;//进入时初始化视频
//var startLieId;//开始列
//var endLieId;//结束列
var hkvideo=0;
var starOpenChanal = 1;//自动预览窗口数量
var openWinNum=1;//默认显示窗口数
var chanal=null;//初始化视频
$(document).ready(function(){
	var name;
	var slie = -1;
	var options;
	//初始化样式
	initOpClass();
	$("#tz").addClass("cz-act");
	//初始化库房
	loadStore();
	//初始化库区
	loadStoreArea();
	//初始化密集列
	loadImsAndVms();
	//库房选择
	var myDate = new Date();
    var iYear = myDate.getFullYear();
	$("#store").change(function(){  
		changeStore();
	});  
	$("#storeArea").change(function(){  
		changeStoreArea();
	}); 
	
	$("#lie").change(function(){
	var name;
	//var slie = -1;
	var options;
	//获取当前选择项
	options = $("#lie option:selected");
	//当前选择项的名字
	name = options.text();
	//slie = $("#lie").val();
	selectLie(name);
	//视频初始化注册
    //openvideo();
	//预览视频
	yulan();
});
	//视频初始化注册
    openvideo();
	
});
//预览视频的方法
function yulan(){
	//通过密集架id查找视频通道号
    Ajax.custom({
		url : config.getVideoNo,
		data : {
			deviceId:$("#lie").val()
		},//传递参数
		type : 'POST'
	}, function(data) {
		if(data.data.length>0){
			if(data.data[0].name!=null){
				chanal = parseInt(data.data[0].name);      		
			}else{
				alert("视频通道号为空");
			}	
			}else{
				alert("密集架未绑定视频通道号");
			}
		startRealPlay(chanal);
	});
}
//视频设置后，初始化页面
/*function initPage(starttime, endtime) {
	//获取设备通道
	$(".sp .fp li:eq(1)").addClass("def");
	registerVideoServer();
	getDevNames();
	getDevChan();
	if (starttime == null) {
		 showVideo(); 
		showVideo1(indexNo,0);
		//presetpoint();	
		 if (firstReason==1) {
			     showVideoByChanel(1,0);
				 showVideoByChanel(0,1);
		} 
	} else {
		s_playback(starttime, endtime);

	}

}*/

function openvideo() {
	/*$(".sp .back_carmo").hide();
	$(".sp .back_carm").hide();*/
	clickLogin();
}

function startRealPlay(chanal){
	if(chanal!=null){
		g_iWndIndex=0;//默认第一个窗口
		iChannelID=chanal;//摄像头通道号
		//changeWndNum(1);//显示一个窗口
		clickStartRealPlay(chanal);
	}
	
}
//加载库房
function loadStore(){
	//初始化库房
	Ajax.custom({
		url : config.getWmsStore,
		data : {
			
		},//传递参数
		type : 'POST'
	}, function(json) {
		for(var i=0;i<json.data.length;i++){
			
        var	optionstring = "<option value=\"" + json.data[i].storeId + "\" >" + json.data[i].name+ "</option>";
        $("#store").append(optionstring);
        	 
        	
         	}
	}); 
	
}

//加载库区
function loadStoreArea(){
   
	//初始化库区
    if(isNaN(storeId) || storeId==0){
    	return;
    }
    
    Ajax.custom({
		url : config.getStoreAreaByStoreId,
		data : {
			"storeId":storeId
		},//传递参数
		type : 'POST'
	}, function(json) {
		//var s="";
    	$("#storeArea").empty();
    	var	 optionstring="<option value='-1'>选择库区</option>";
        $("#storeArea").append(optionstring);
    	for(var i=0;i<json.data.length;i++){
    		if(i==0){
    			storeAreaId = json.data[i].stroreAreaId;
    		}		
    		var	 optionstring = "<option value=\"" + json.data[i].stroreAreaId + "\" >" + json.data[i].name+ "</option>";
    		$("#storeArea").append(optionstring);
    	 
     	}
    	if($("#storeArea").val()==-1){
    		storeAreaId = -1;
    	}
       // $("#storeArea").html(s);  //初始化库区  
	}); 
    
}

//加载密集架列
function loadImsAndVms(){
     if(isNaN(storeId) || storeId==0){
    	return;
    }
    if(isNaN(storeAreaId) || storeAreaId==0){
    	return;
    } 
    //初始化列
    /*$.ajax({
        type:'post',
        url:'${BASE_URL}/deviceManage/getStoreDeviceListProperty.do?storeId='+storeId+'&stroreAreaId='+storeAreaId,
        dataType:'json',        
        async:false,
        success:function(data){       	
        	
        	
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
        	// 通常情况下textStatus和errorThown只有其中一个有值 
        	alert(errorThrown); 
       	}
    });*/
    Ajax.custom({
		url : config.getStoreDeviceListProperty,
		data : {
			"storeId":storeId,
			"stroreAreaId":storeAreaId
		},//传递参数
		type : 'POST'
	}, function(data) {
		//var tmpColoumNo = "0";
    	$("#lie").empty();
    	var	 optionstring="<option value='-1'>选择密集架</option>";
        $("#lie").append(optionstring);
    	for(var i=0;i<data.data.length;i++){
							
    		var	 optionstring = "<option value=\"" + data.data[i].deviceId + "\" >" + data.data[i].name+ "</option>";
    		$("#lie").append(optionstring);       	
     	}

    	if($("#store").val() == -1){
    		$("#titleTextId").text("请选择库房");
    	}else if($("#storeArea").val() == -1){
    		$("#titleTextId").text("请选择库区");
    		//$("#lie").html(ss);   //初始化列
    	}else if($("#lie").val() == null||$("#lie").val() == -1) {
    		//$("#lie").html("");   //初始化列
			$("#titleTextId").text("未找到或未选择密集架");
			slie = -1;
    	}else{
    		$("article .wrap .m-lie a").removeClass("m-act");
    		$("#imsCol0").addClass("m-act");
    		$("#titleTextId").text(data.data[0].name+"远程操控");
    		slie = data.data[0].deviceId;
    		
    	}
	});
    
}

function temp(){
	 
}    

function changeStore(){
   //$("#storeArea").empty();  //初始化库区
    //$("#lie").empty();
    storeAreaId=0;  //选中库区编号
    //imsId=-1;  //选中列
	storeId=$("#store").children('option:selected').val();//这就是selected的值
	storeAreaId=$("#storeArea").children('option:selected').val();
	//初始化库区
	loadStoreArea();
	//初始化密集列
	//initVms =false;
	if($("#store").val()==-1){
		$("#titleTextId").text("请选择库房");
	}else{
		$("#titleTextId").text("请选择库区");
	}
	loadImsAndVms();	
}
function changeStoreArea(){
	    //$("#lie").empty();
	    storeAreaId=0;  //选中库区编号
	    slie=-1;  //选中列
		storeId=$("#store").children('option:selected').val();//这就是selected的值
		storeAreaId=$("#storeArea").children('option:selected').val();
		//初始化密集列
		//initVms =false;
		loadImsAndVms();
	}
  


//得到选中列
function selectLie(name){
	 
	if($("#lie").val()==-1){
		$("#titleTextId").text("请选择密集架");
	}else{
	  $("#titleTextId").text(name+"远程操控");
	  initOpClass();
	  $("#tz").addClass("cz-act");		
	}
}

function initOpClass(){
	$("article .wrap .mjj-rb .mjj-rb-r li").removeClass("cz-qact");
	$("article .wrap .mjj-rb .mjj-rb-l li").removeClass("cz-act");
}
  
 
//密集架控制
//通风
/* var mjjL=$("article .house .kf .mjj>li"); */
//左打开
function openleft(slie){	
  if(slie==-1){
		alert('请选择你要打开的密集架!');
		return;
	 }
  
	Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":slie,// 设备id
	        "categoryId":5,// 设备类型
	        "actionType":4,// 操作类型
	        "isSelect":0// 全选表示
		},//传递参数
		type : 'POST'
	}, function(data) {
		
	});
	
}
//右打开
function openright(slie){
	if(slie==-1){
			alert('请选择你要打开的密集架!');
			return;
		 }
	Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":slie,// 设备id
	        "categoryId":5,// 设备类型
	        "actionType":5,// 操作类型
	        "isSelect":0// 全选表示
		},//传递参数
		type : 'POST'
	}, function(data) {
		
	});
	 
}
//停止
function stop(slie){
	  if(slie==-1){
			alert('请选择你要停止的密集架!');
			return;
		 }
		Ajax.custom({
			url : config.operateDevice,
			data : {
				"userId":1,// 用户id
		        "deviceId":slie,// 设备id
		        "categoryId":5,// 设备类型
		        "actionType":3,// 操作类型
		        "isSelect":0// 全选表示
			},//传递参数
			type : 'POST'
		}, function(data) {
			
		});
}
//全部合拢
function closeshelve1(slie){
	 if(slie==-1){
		alert('请选择你要合拢的密集架!');
		return;
	 }
	 if (confirm("确认合拢吗？")) {
		
		 
			Ajax.custom({
				url : config.operateDevice,
				data : {
					"userId":1,// 用户id
			        "deviceId":slie,// 设备id
			        "categoryId":5,// 设备类型
			        "actionType":7,// 操作类型
			        "isSelect":0// 全选表示
				},//传递参数
				type : 'POST'
			}, function(data) {
				
			});
	  }
	
}
//单列合拢
function closeshelve(slie){
	 if(slie==-1){
		alert('请选择你要合拢的密集架!');
		return;
	 }
	 /* $("#asdf").css({"display":"block"}); */
	 if (confirm("确认合拢吗？")) {
		/*  $(mjjL).stop().animate({"bottom":0},2000); */
		
			Ajax.custom({
				url : config.operateDevice,
				data : {
					"userId":1,// 用户id
			        "deviceId":slie,// 设备id
			        "categoryId":5,// 设备类型
			        "actionType":7,// 操作类型
			        "isSelect":0// 全选表示
				},//传递参数
				type : 'POST'
			}, function(data) {
				
			});
	  }
	
}
//通风
function wind(slie){
	 if(slie==-1){
		alert('请选择你要通风的密集架!');	
		return;
	 }
		 if (confirm("确认通风吗？")) {
				/* $(mjjL).stop().animate({"bottom":0},0);
				$(mjjL.eq(0)).stop().animate({"bottom":20},2000);
				$(mjjL.eq(1)).stop().animate({"bottom":10},2000);
				$(mjjL.eq(3)).stop().animate({"bottom":-10},2000);
				$(mjjL.eq(4)).stop().animate({"bottom":-20},2000);
				$("#asdf").css({"display":"block"}); */
				
				Ajax.custom({
					url : config.operateDevice,
					data : {
						"userId":1,// 用户id
				        "deviceId":slie,// 设备id
				        "categoryId":5,// 设备类型
				        "actionType":6,// 操作类型
				        "isSelect":0// 全选表示
					},//传递参数
					type : 'POST'
				}, function(data) {
					
				});
			 
		  }    
}



