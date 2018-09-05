var starOpenChanal = 9;//自动预览窗口数量
var openWinNum=3;//默认显示窗口数
var vmsList = new Array();
$(document).ready(function() {
    var background = {
            type: 'linearGradient',
            x0: 0,
            y0: 0,
            x1: 0,
            y1: 1,
            colorStops: [{ offset: 0, color: '#d2e6c9' },
                         { offset: 1, color: 'white' }]
        };
   //获取基础设备数据
	Ajax.custom({
		url : config.getVideoList,
		data : {

		},//传递参数
		type : 'GET'
	}, function(data) {
		if(data.data != null){
			vmsList = data.data.channel;
			$("#videoList").html(
				template.render("baseNum-data", {
					baseData : data.data
				}));
		}
		$("article .video .vid_lf .menu_body:gt(0)").hide();
		$("article .video .vid_lf .menu_head:eq(0)").addClass("cur");
		$("article .video .vid_lf .menu_head").click(function(){   
			              $(this).addClass("cur").next(".menu_body").slideToggle(300).siblings(".menu_body").slideUp();
			              $(this).siblings().removeClass("cur"); 
			});
		$("article .video .vid_lf .menu_list a").prepend("<em></em>");
		$("article .video .vid_lf .menu_head").prepend("<em></em>");
		
		$("article .video .vid_lf .con_head").click(function(){   
			$(this).addClass("cur").next(".menu_body").slideToggle(300).siblings(".menu_body").slideUp();
			$(this).siblings().removeClass("cur"); 
		});
	}); 
	clickLogin();
});
function startRealPlay(chanal){
	if(chanal!=null){
		g_iWndIndex=0;//默认第一个窗口
		iChannelID=chanal;//摄像头通道号
		changeWndNum(1);//显示一个窗口
		clickStartRealPlay(chanal);
	}
	
}
function changeWndNums(WinNum){
	changeWndNum(WinNum);
	clickStartRealPlay(chanalFixNos[0]);
}
