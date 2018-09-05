var storeId=0;   //选中库房编号
var stroreAreaId=0;  //选中库区编号
var deviceId=0;  //选中列
var vmsChannel="";//视频通道
var initVms = false;//进入时初始化视频
var startLieId;//开始列
var endLieId;//结束列
var hkvideo=0;
var starOpenChanal = 1;//自动预览窗口数量
var openWinNum=1;//默认显示窗口数
var chanal=1;
$(document).ready(function(){
	//初始化库房
	loadStore();
	//初始化库区
	loadStroreArea();chang1()
	//初始化密集列
	loadImsAndVms();
	$("#store").change(function(){  
		changeStore();
    });
	$("#stroreArea").change(function(){  
		changeStroreArea();
    });
	$("#deviceManage").change(function(){ 
		loadRealPlay();
    });
});
function chang1(){
	$("#index").attr("class","no_select")
	$("#znfx_dahx").attr("class","no_select")
	$("#zntj_datj").attr("class","no_select")
	$("#smartlog1").attr("class","no_select")
	$("#ywcz_dacx").attr("class","no_select")
	$("#deviceop1").attr("class","select")
}
//加载库房
function loadStore(){
	//初始化库房
	$.ajax({
	     type:'POST',
	     url:config.getWmsStore,
	     dataType:'json',
	     async:false,
	     success:function(json) {
		var data=json.data;
		var	 optionstring="<option>选择库房</option>";
		for(var i=0;i<data.length;i++){
       		optionstring = optionstring+"<option value=\"" + data[i].storeId + "\" >" + data[i].name + "</option>";
       	 }
		$("#store").append(optionstring);
		var	 SAoptionstring="<option>选择库区</option>";
		$("#stroreArea").append(SAoptionstring);
		var	 DMoptionstring="<option>选择密集架</option>";
		$("#deviceManage").append(DMoptionstring);
        	}
			
	});
	
}
	     

//加载库区
function loadStroreArea(){
    //初始化库区
    if(isNaN(storeId) || storeId==0){
    	return;
    }
    $("#stroreArea").empty();
    $.ajax({
        type:'POST',
        url:config.getWmsStroreArea,
        data:{storeId:storeId},
        dataType:'json',
        async:false,
        success:function(json){
    		var data=json.data;
    		var	 optionstring="<option>选择库区</option>";
    		for(var i=0;i<data.length;i++){
           		optionstring = optionstring+"<option value=\"" + data[i].stroreAreaId + "\" >" + data[i].name + "</option>";
           		
    		}
    		$("#stroreArea").append(optionstring);
            	}
     });
}

//加载密集架列
function loadImsAndVms(){
	 if(isNaN(storeId) || storeId==0){
	    	return;
	    }
	 if(isNaN(stroreAreaId) || stroreAreaId==0){
	    	return;
	    }
	 $("#deviceManage").empty();
	    $.ajax({
	        type:'POST',
	        url:config.getStoreDeviceList,
	        data:{storeId:storeId,stroreAreaId:stroreAreaId,categoryId:5},
	        dataType:'json',
	        async:false,
	        success:function(json){
	    		var data=json.data;
	    		var	 optionstring="<option>选择密集架</option>";
	    		for(var i=0;i<data.length;i++){
	           		optionstring = optionstring+"<option value=\"" + data[i].deviceId + "\" >" + data[i].name + "</option>";	         	
	    		}
	    		$("#deviceManage").append(optionstring);	    		
	            	}
	    })
}
//改变库房得到库区
function changeStore(){
    $("#lie").empty();
    stroreAreaId=0;  //选中库区编号
    deviceId=1;  //选中列
	storeId=$("#store").children('option:selected').val();//这就是selected的值
	//初始化库区
	loadStroreArea();
}
//改变库区得到列
function changeStroreArea(){
      //初始化库区
    $("#lie").empty();
	storeId=$("#store").children('option:selected').val();//这就是selected的值
    stroreAreaId=$("#stroreArea").children('option:selected').val();  //选中库区编号
	//初始化密集架
	initVms =false;
	loadImsAndVms();
}
//通风
function wind(){
	deviceId=$("#deviceManage").children('option:selected').val();
	if(deviceId == 0){
		alert('请选择你要通风的密集架!');
		return;
	}
	if (confirm("确认通风吗？")) {
		 Ajax.custom({
				url : config.operateDevice,
				data : {
					"userId":1,// 用户id
			        "deviceId":deviceId,// 设备id
			        "categoryId":5,// 设备类型
			        "actionType":6,// 操作类型
			        "isSelect":0// 全选表示
				},// 传递参数
				type : 'GET'
			}, function(data) {
				
			})
	}
}

//合拢密集架
function closeshelve(){
	deviceId=$("#deviceManage").children('option:selected').val();
	if(deviceId== 0){
		alert('请选择你要合拢的密集架!');
		return;
	}
	if (confirm("确认合拢吗？")) {
		 Ajax.custom({
				url : config.operateDevice,
				data : {
					"userId":1,// 用户id
			        "deviceId":deviceId,// 设备id
			        "categoryId":5,// 设备类型
			        "actionType":7,// 操作类型
			        "isSelect":0// 全选表示
				},// 传递参数
				type : 'GET'
			}, function(data) {
				
			})
	}
}

//左打开
function openleft(){
	deviceId=$("#deviceManage").children('option:selected').val();
	if(deviceId == 0){
		alert('请选择你要打开的密集架!');
		return;
	}
	 Ajax.custom({
			url : config.operateDevice,
			data : {
				"userId":1,// 用户id
		        "deviceId":deviceId,// 设备id
		        "categoryId":5,// 设备类型
		        "actionType":4,// 操作类型
		        "isSelect":0// 全选表示
			},// 传递参数
			type : 'GET'
		}, function(data) {
			
		})

}

//右打开
function openright(){
	deviceId=$("#deviceManage").children('option:selected').val();
	if(deviceId == 0){
		alert('请选择你要打开的密集架!');
		return;
	}
	Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":deviceId,// 设备id
	        "categoryId":5,// 设备类型
	        "actionType":5,// 操作类型
	        "isSelect":0// 全选表示
		},// 传递参数
		type : 'GET'
	}, function(data) {
		
	})
}

//停止
function stop(){
	deviceId=$("#deviceManage").children('option:selected').val();
	if(deviceId == 0){
		alert('请选择你要停止的密集架!');
		return;
	}
	Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":deviceId,// 设备id
	        "categoryId":5,// 设备类型
	        "actionType":3,// 操作类型
	        "isSelect":0// 全选表示
		},// 传递参数
		type : 'GET'
	}, function(data) {
		
	})
}
