var starOpenChanal = 1;//自动预览窗口数量
var openWinNum=1;//默认显示窗口数
var vmsList = new Array();
$(document).ready(function() {
	$(".kf .k-tip").hide();
    $(".kf .miTd").hide();
    var background = {
            type: 'linearGradient',
            x0: 0,
            y0: 0,
            x1: 0,
            y1: 1,
            colorStops: [{ offset: 0, color: '#d2e6c9' },
                         { offset: 1, color: 'white' }]
        };
    // 温湿度模块
    getHcs_1();
    // setInterval这个函数会根据后面定义的3000既每隔3秒执行一次前面那个函数
    setInterval("getHcs_1()",20000);
//    // 初始化插件参数及插入插件
    clickLogin();
	
});
//视频设置后，初始化页面
function initPage() {
	Ajax.custom({
		url : config.getVideoList,
		data : {

		},// 传递参数
		type : 'GET'
	}, function(data) {
		if (data.data != null) {
			vmsList = data.data.channel;
			// 获取设备通道
			registerVideoServer();
			getDevNames();
			getDevChan();
			for (var i = 0; i < vmsList.length; i++) {
				console.log(vmsList[i].threeId);
				showVideo1(vmsList[i].threeId, i);
			}
		}
	});
}
//--------------------------温湿度-------------------------------------
// 获取温湿度数据
function getHcs_1(){
	Ajax.custom({
		url : config.getNewHumiture,
		data : {
		},// 传递参数
		type : 'GET'
	}, function(data) {
		if(data.data != null){
			for(var i=0;i<data.data.length;i++){
				if(i==0){
					$("article  .kf a:eq(12) .k-tip").html("<span onclick=\"go2Hcs();\"> <em>"+data.data[i].avgtem+"</em>℃</span>  <span  onclick=\"go2Hcs();\"> <em>"+data.data[i].avghum+"</em>%</span>");
		      		$("article  .kf a:eq(13) .k-tip").stop().delay(200).fadeIn();
				}
				if(i==1){
					$("article  .kf a:eq(13) .k-tip").html("<span  onclick=\"go2Hcs();\"> <em>"+data.data[i].avgtem+"</em>℃</span>  <span  onclick=\"go2Hcs();\"> <em>"+data.data[i].avghum+"</em>%</span>");
	      			 $("article  .kf a:eq(12) .k-tip").stop().delay(200).fadeIn();
				}
			}
		}
	}); 
}
// 温湿度页面跳转
function go2Hcs(){
	 // window.location.href='${contentpath}/front/hcs/main.do';
	alert("页面跳转");
}

// --------------------------门禁-------------------------------------
// 控制门禁开关
function optionAsc(deciceId,flag,indexNo){
	var status;
	var temp = '你确定要打开此门吗？';
	if(window.confirm(temp)){
     	Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":deciceId,// 设备id
	        "categoryId":3,// 设备类型
	        "actionType":1,// 操作类型
	        "isSelect":0// 全选表示
		},// 传递参数
		type : 'GET'
	}, function(data) {
		 if(deciceId==1){
			 $("article .house aside:eq(0) a:eq(0)").addClass("active");
		 }
		 if(deciceId==3){
		    $("article .house aside:eq(0) a:eq(1)").addClass("active");
		 }
	})
	}else{
		// 取消
	}
}
// door-1--前台门
$("article .kf a:eq(5)").hover(function(){
      $("article .kf a .k-tip:eq(5)").html("<img src=\"/bis_app/resources/images/web/mj-tip-g.png\" onclick=\"optionAsc('20','1',5);\"/> <span><img src=\"/bis_app/resources/images/web/mj-tip-kg-g.png\" onclick=\"go2Door();\"/></span>");
      $("article  .kf a:eq(5)").children(".k-tip").stop().delay(200).fadeIn();
	}, function(){ $(this).children(".k-tip").stop().hide();})
// door-1--前台门2（靠过道）
$("article .kf a:eq(8)").hover(function(){
		$("article .kf a .k-tip:eq(8)").html("<img src=\"/bis_app/resources/images/web/mj-tip-g.png\" onclick=\"optionAsc('20','1',5);\"/> <span><img src=\"/bis_app/resources/images/web/mj-tip-kg-g.png\" onclick=\"go2Door();\"/></span>");
		$("article  .kf a:eq(8)").children(".k-tip").stop().delay(200).fadeIn();
}, function(){ $(this).children(".k-tip").stop().hide();})


// door-2--木门
$("article .kf a:eq(6)").hover(function(){
      $("article  .kf a .k-tip:eq(6)").html("<img src=\"/bis_app/resources/images/web/mj-tip-g.png\" onclick=\"optionAsc('18','1',6);\"/> <span><img src=\"/bis_app/resources/images/web/mj-tip-kg-g.png\" onclick=\"go2Door();\"/></span>");
      $("article  .kf a:eq(6)").children(".k-tip").stop().delay(200).fadeIn();
	}, function(){ $(this).children(".k-tip").stop().hide();})
	
	// door-3--玻璃门
$("article  .kf a:eq(7)").hover(function(){
      $("article .kf a .k-tip:eq(7)").html("<img src=\"/bis_app/resources/images/web/mj-tip-g.png\" onclick=\"optionAsc('17','1',7);\"/> <span><img src=\"/bis_app/resources/images/web/mj-tip-kg-g.png\" onclick=\"go2Door();\"/></span>");
      $("article .kf a:eq(7)").children(".k-tip").stop().delay(200).fadeIn();
	}, function(){ $(this).children(".k-tip").stop().hide();})
	
	// door-4---机房门--打不开的
$("article  .kf a:eq(9)").hover(function(){
      $("article .kf a .k-tip:eq(9)").html("<img src=\"/bis_app/resources/images/web/mj-tip-g.png\" onclick=\"optionAsc('17','1',7);\"/> <span><img src=\"/bis_app/resources/images/web/mj-tip-kg-g.png\" onclick=\"go2Door();\"/ ></span>");
      $("article  .kf a:eq(9)").children(".k-tip").stop().delay(200).fadeIn();
	}, function(){ $(this).children(".k-tip").stop().hide();})
	
	// door-5--库房门
$("article  .kf a:eq(10)").hover(function(){
      $("article  .kf a .k-tip:eq(10)").html("<img src=\"/bis_app/resources/images/web/mj-tip-g.png\" onclick=\"optionAsc('19','1',9);\"/> <span><img src=\"/bis_app/resources/images/web/mj-tip-kg-g.png\" onclick=\"go2Door();\"/></span>");
      $("article  .kf a:eq(10)").children(".k-tip").stop().delay(200).fadeIn();
	}, function(){ $(this).children(".k-tip").stop().hide();})

function go2Door(){
	alert("自动关闭");
}
//--------------------------------------------灯光----------------------------------------------
//控制灯光开关
function optionIls(deciceId,flag,indexNo){
	var status=flag;
	var temp;
	if(status=='1'){
		temp = '你确定要关闭此灯吗？';
	}else {
		temp = '你确定要打开此灯吗？';
	}
	if(window.confirm(temp)){
		$.ajax({
             type: "post",
             url: "${contentpath}/mobile/mobSendIlsDevice.do?deviceNumb=1&osVersion=1&token=1",
             dataType : 'json',
             data: {
            	 "userId":1,
                 "deviceId":deciceId,
                 "opMark":status
             },
             success: function (data) {
            	 if(status=='0'){
            		 kfdStauts=1;
            		 $("article .house aside:eq(0) a:gt(2)").addClass("active");
    			 }else{
    				 kfdStauts=0;
    				 $("article .house aside:eq(0) a:gt(2)").removeClass("active");
    			 }
            	//  $("article .wrap .kf a .k-tip:eq("+indexNo+")").html("<img src=\"/bis/images/dg-tip-k.png\" /> <span class=\"kg\"><img src=\"/bis/images/dg-tip-kg-k.png\" />");  
            	//  $("article .wrap .kf a:eq("+indexNo+")").children(".k-tip").stop().delay(200).fadeIn();
             },error:function(e) {    
                 //alert("网络出现异常");    
            }  
     	})
	}else{
		//取消	
	}
}

$("article .kf a:eq(11)").hover(function(){
	 $("article .kf a .k-tip:eq(11)").html("<img src=\"/bis_app/resources/images/web/dg-tip-g.png\"  onclick=\"optionIls('21','0',10);\"/> <span class=\"kg\"><img src=\"/bis_app/resources/images/web/dg-tip-kg-g.png\" onclick=\"go2Ils();\" /><span>");
	 $("article .kf a:eq(11)").children(".k-tip").stop().delay(200).fadeIn();
	 /* $.ajax({
        type: "post",
        url: "${contentpath}/mobile/mobIlsDeviceList.do?areaId=&token=1&storeId=1",
        dataType : 'json',
        data: {
            "userId":1
        },
        success: function (data) {
      	  if(data!=''){
      		  //status 0:关  1：开
      		  if( true){
      			  $("article .kf a .k-tip:eq(11)").html("<img src=\"/bis_app/resources/images/web/dg-tip-g.png\"  onclick=\"go2Ils();\"/> <span class=\"kg\"><img src=\"/bis_app/resources/images/web/dg-tip-kg-g.png\" onclick=\"optionIls('"+data.result[0].id+"','1',10);\" /><span>");
      			  $("article .kf a:eq(11)").children(".k-tip").stop().delay(200).fadeIn();
      		  }
      		  if( data.result[0].status == '1'){
      			  $("article  .kf a .k-tip:eq(11)").html("<img src=\"/bis_app/resources/images/web/dg-tip-k.png\"  onclick=\"go2Ils();\"/> <span class=\"kg\"><img src=\"/bis_app/resources/images/web/dg-tip-kg-k.png\" onclick=\"optionIls('"+data.result[0].id+"','0',10);\"/><span>");
      			  $("article  .kf a:eq(11)").children(".k-tip").stop().delay(200).fadeIn();
      		  } 
      	  }
        },error:function(e) {    
            //alert("网络出现异常");    
       }    
    });*/
	}, function(){ $(this).children(".k-tip").stop().hide();})
	function go2Ils(){
	alert("自动关闭");
}
//----------------------------------------------RFID---------------------------------------
//密集架控制
//通风
var mjjL=$("article .house .kf .mjj>li");
//左打开
function openleft(slie){	
    if(slie==-1){
		alert('请选择你要打开的密集架!');
		return;
	 }
    if(slie==11){
    	$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":14},2000);
    }
    if(slie==12){
    	$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":14},2000);
		$(mjjL.eq(1)).stop().animate({"bottom":14},2000);
    }
    if(slie==14){
    	$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(3)).stop().animate({"bottom":-14},2000);
		$(mjjL.eq(4)).stop().animate({"bottom":-14},2000);
    }
    if(slie==15){
    	$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(4)).stop().animate({"bottom":-14},2000);
    }
    $("#divPlugin").css({"display":"block"});
    Ajax.custom({
		url : config.operateDevice,
		data : {
			"userId":1,// 用户id
	        "deviceId":slie,// 设备id
	        "categoryId":5,// 设备类型
	        "actionType":4,// 操作类型
	        "isSelect":0// 全选表示
		},// 传递参数
		type : 'GET'
	}, function(data) {
		
	})
}
//右打开
function openright(slie){
	if(slie==-1){
			alert('请选择你要打开的密集架!');
			return;
		 }	
	 if(slie==10){
		 $(mjjL).stop().animate({"bottom":0},0);
			$(mjjL.eq(0)).stop().animate({"bottom":14},2000);
	 }
	 if(slie==11){
			$(mjjL).stop().animate({"bottom":0},0);
			$(mjjL.eq(0)).stop().animate({"bottom":14},2000);
			$(mjjL.eq(1)).stop().animate({"bottom":14},2000);
	 }
	 if(slie==12){
		 $(mjjL).stop().animate({"bottom":0},0);
			$(mjjL.eq(3)).stop().animate({"bottom":-14},2000);
			$(mjjL.eq(4)).stop().animate({"bottom":-14},2000);
	 }
	 if(slie==14){
			$(mjjL).stop().animate({"bottom":0},0);
			$(mjjL.eq(4)).stop().animate({"bottom":-14},2000);
			 
	 }
	 $("#divPlugin").css({"display":"block"});
	 Ajax.custom({
			url : config.operateDevice,
			data : {
				"userId":1,// 用户id
		        "deviceId":slie,// 设备id
		        "categoryId":5,// 设备类型
		        "actionType":5,// 操作类型
		        "isSelect":0// 全选表示
			},// 传递参数
			type : 'GET'
		}, function(data) {
			
		})
}
//停止
function stop(slie){
	  if(slie==-1){
			alert('请选择你要停止的密集架!');
			return;
		 }
	  $("#divPlugin").css({"display":"block"});
	  Ajax.custom({
			url : config.operateDevice,
			data : {
				"userId":1,// 用户id
		        "deviceId":slie,// 设备id
		        "categoryId":5,// 设备类型
		        "actionType":3,// 操作类型
		        "isSelect":0// 全选表示
			},// 传递参数
			type : 'GET'
		}, function(data) {
			
		})
}
//全部合拢
function closeshelve1(slie){
	 if(slie==-1){
		alert('请选择你要合拢的密集架!');
		return;
	 }
	 if (confirm("确认合拢吗？")) {
		// $("article .house aside:eq(0) a:eq(7) ").addClass("active").siblings("article .house aside:eq(0) a:gt(2)").removeClass("active");
		 $("article .house aside:eq(0) a:gt(2) ").removeClass("active");
		 $("article .house aside:eq(0) a:eq(7) ").addClass("active");
		 //$("article .house .kf .miTd").show();
		 $("#divPlugin").css({"display":"block"});
		 showVideo1(32,1);
		 Ajax.custom({
				url : config.operateDevice,
				data : {
					"userId":1,// 用户id
			        "deviceId":slie,// 设备id
			        "categoryId":5,// 设备类型
			        "actionType":7,// 操作类型
			        "isSelect":0// 全选表示
				},// 传递参数
				type : 'GET'
			}, function(data) {
				
			})
	  }
	
}
//单列合拢
function closeshelve(slie){
	 if(slie==-1){
		alert('请选择你要合拢的密集架!');
		return;
	 }
	 $("#divPlugin").css({"display":"block"});
	 if (confirm("确认合拢吗？")) {
		 $(mjjL).stop().animate({"bottom":0},2000);
		 Ajax.custom({
				url : config.operateDevice,
				data : {
					"userId":1,// 用户id
			        "deviceId":slie,// 设备id
			        "categoryId":5,// 设备类型
			        "actionType":7,// 操作类型
			        "isSelect":0// 全选表示
				},// 传递参数
				type : 'GET'
			}, function(data) {
				
			})
	  }
	
}
//通风
function wind(slie){
	 if(slie==-1){
		alert('请选择你要通风的密集架!');	
		return;
	 }
		 if (confirm("确认通风吗？")) {
				$(mjjL).stop().animate({"bottom":0},0);
				$(mjjL.eq(0)).stop().animate({"bottom":20},2000);
				$(mjjL.eq(1)).stop().animate({"bottom":10},2000);
				$(mjjL.eq(3)).stop().animate({"bottom":-10},2000);
				$(mjjL.eq(4)).stop().animate({"bottom":-20},2000);
				$("#divPlugin").css({"display":"block"});
			 Ajax.custom({
					url : config.operateDevice,
					data : {
						"userId":1,// 用户id
				        "deviceId":slie,// 设备id
				        "categoryId":5,// 设备类型
				        "actionType":6,// 操作类型
				        "isSelect":0// 全选表示
					},// 传递参数
					type : 'GET'
				}, function(data) {
					if(data!=null){
						$(".m-zt").html("<img src='/bis_app/resources/images/web/m-zt-tf.png'/>");
						removeClass();
						 $("#tf").addClass("cz-qact");
					}
				})
			 
		  }    
}

//底部右打开
function openright1(slie,index){
	 if(slie==-1){
			alert('请选择你要打开的密集架!');
			return;
	 }	
	 $("article .house aside:eq(0)  a:gt(2)").removeClass("active");
	 $("article .house aside:eq(0) a:eq("+index+")").addClass("active")
	 //$("article .house .kf .miTd").show();
	 $("#divPlugin").css({"display":"block"});
	 showVideo1(32,1);
	 Ajax.custom({
			url : config.operateDevice,
			data : {
				"userId":1,// 用户id
		        "deviceId":slie,// 设备id
		        "categoryId":5,// 设备类型
		        "actionType":5,// 操作类型
		        "isSelect":0// 全选表示
			},// 传递参数
			type : 'GET'
		}, function(data) {
			
		})
}
//底部通风
function wind1(slie){
	 if(slie==-1){
		alert('请选择你要通风的密集架!');	
		return;
	 }
		 if (confirm("确认通风吗？")) {
			// $("article .house aside:eq(0) a:eq(8) ").addClass("active").siblings("article .house aside:eq(0) a:gt(2)").removeClass("active");
			 $("article .house aside:eq(0) a:gt(2) ").removeClass("active");
			 $("article .house aside:eq(0) a:eq(8) ").addClass("active");
			 //$("article .house .kf .miTd").show();
			 $("#divPlugin").css({"display":"block"});
			 showVideo1(32,1);
			 Ajax.custom({
					url : config.operateDevice,
					data : {
						"userId":1,// 用户id
				        "deviceId":slie,// 设备id
				        "categoryId":5,// 设备类型
				        "actionType":6,// 操作类型
				        "isSelect":0// 全选表示
					},// 传递参数
					type : 'GET'
				}, function(data) {
					
				})
			 
		  }  

		  
}

//--------------------视频-------------
$("article  .kf a:eq(0)").hover(function(){
    $("article  .kf a:eq(0)").children(".k-tip").stop().delay(200).fadeIn();   
    //showVideo1(32,1);
}, function(){ $(this).children(".k-tip").stop().hide();})

$("article .kf a:eq(1)").hover(function(){
    $("article  .kf a:eq(1)").children(".k-tip").stop().delay(200).fadeIn();
    //showVideo1(36,0);
}, function(){ $(this).children(".k-tip").stop().hide();})

$("article  .kf a:eq(2)").hover(function(){
    $("article .kf a:eq(2)").children(".k-tip").stop().delay(200).fadeIn();
    //showVideo1(34,3);
}, function(){ $(this).children(".k-tip").stop().hide();})

$("article  .kf a:eq(3)").hover(function(){
    $("article  .kf a:eq(3)").children(".k-tip").stop().delay(200).fadeIn();
    //showVideo1(35,4);
}, function(){ $(this).children(".k-tip").stop().hide();})

$("article  .kf a:eq(4)").hover(function(){
    $("article  .kf a:eq(4)").children(".k-tip").stop().delay(200).fadeIn();
    //showVideo1(31,0);
}, function(){ $(this).children(".k-tip").stop().hide();})
//显示查看档案的信息上方信息
function findDaxx(id){
	$("#lay .article ul:eq(0) li tr td").remove();
	$("#lay .article ul:eq(1) li tr td").remove();
	if(flag==0){
		$("#zy").text("左侧");//设置每次点击查看档案为左侧
		$("#lay .article ul:eq(1)").hide();
	}else if(flag==1){
		$("#zy").text("右侧");
		$("#lay .article ul:eq(0)").hide();
	};
	
	if(id==-1){
		return;
	 }else{
		 slie=id;//列的ID
	 }
	/*for(var i=0;i<imsList.length;i++){
		if(imsList[i].id==slie){
			$("#dalie").text("第"+convertToChinese(imsList[i].columnNo)+"列");
		}
	}
	showInfo(sId,saId,slie);*/
}
function ChangeStatu(id){
	  //showVideoByChanel(id,0);
}
//右打开
function openright1(slie,index){
	 if(slie==-1){
			alert('请选择你要打开的密集架!');
			return;
	 }	
	 $("article .house aside:eq(0)  a:gt(2)").removeClass("active");
	 $("article .house aside:eq(0) a:eq("+index+")").addClass("active")
	 //$("article .house .kf .miTd").show();
	 $("#divPlugin").css({"display":"block"});
	    Ajax.custom({
			url : config.operateDevice,
			data : {
				"userId":1,// 用户id
		        "deviceId":slie,// 设备id
		        "categoryId":5,// 设备类型
		        "actionType":4,// 操作类型
		        "isSelect":0// 全选表示
			},// 传递参数
			type : 'GET'
		}, function(data) {
			
		})
}