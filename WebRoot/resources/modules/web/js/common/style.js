// JavaScript Document
$(function(){
	 cde();
function cde(){
     $("article .kf .mjj li a .k-tip .ck").click( function(){ $("#lay").fadeIn();});
	 $("#lay .qxlay").click(function(){$(this).parents("#lay").fadeOut();});
		  /*===========================点击查看档案与关闭 =========================================*/ 
		 
   var N=4;
   var liw=$("#lay .article li").width();
   $("#lay .article ul").css({"width":N*(liw+4)});
   
	   /*===========================4列=========================================*/
	
	$("#lay .article li:nth-child(4n+1)").hover( function(){  $("#lay .article li .li-m:eq(0)").addClass("li-m-bg");},function(){
	$("#lay .article li .li-m:eq(0)").removeClass("li-m-bg");});
	 $("#lay .article li:nth-child(4n+2)").hover( function(){  $("#lay .article li .li-m:eq(1)").addClass("li-m-bg");},function(){
	$("#lay .article li .li-m:eq(1)").removeClass("li-m-bg");});
	$("#lay .article li:nth-child(4n+3)").hover( function(){  $("#lay .article li .li-m:eq(2)").addClass("li-m-bg");},function(){
	$("#lay .article li .li-m:eq(2)").removeClass("li-m-bg");});
	$("#lay .article li:nth-child(4n+4)").hover( function(){  $("#lay .article li .li-m:eq(3)").addClass("li-m-bg");},function(){
	$("#lay .article li .li-m:eq(3)").removeClass("li-m-bg");});
	 
	  /*===========================列加上三角形=========================================*/

	 
	 
	 $("#lay .article li:lt(4)").hover( function(){  $("#lay .article li .li-hm:eq(0)").addClass("li-hm-bg");},function(){
	$("#lay .article li .li-hm:eq(0)").removeClass("li-hm-bg");});
	 $("#lay .article li:lt(8):gt(3)").hover( function(){  $("#lay .article li .li-hm:eq(1)").addClass("li-hm-bg");},function(){
	$("#lay .article li .li-hm:eq(1)").removeClass("li-hm-bg");});
	 $("#lay .article li:lt(12):gt(7)").hover( function(){  $("#lay .article li .li-hm:eq(2)").addClass("li-hm-bg");},function(){
	$("#lay .article li .li-hm:eq(2)").removeClass("li-hm-bg");});
	$("#lay .article li:lt(16):gt(11)").hover( function(){  $("#lay .article li .li-hm:eq(3)").addClass("li-hm-bg");},function(){
	$("#lay .article li .li-hm:eq(3)").removeClass("li-hm-bg");});
	$("#lay .article li:lt(20):gt(15)").hover( function(){  $("#lay .article li .li-hm:eq(4)").addClass("li-hm-bg");},function(){
	$("#lay .article li .li-hm:eq(4)").removeClass("li-hm-bg");});
	 $("#lay .article li:lt(24):gt(19)").hover( function(){  $("#lay .article li .li-hm:eq(5)").addClass("li-hm-bg");},function(){
	$("#lay .article li .li-hm:eq(5)").removeClass("li-hm-bg");});
	 /*===========================行加上三角形=========================================*/
	 
	 $("#lay .article ul:eq(1)").hide();
	 $("#lay .article .zy").toggle( function(){ $("#lay .article ul:eq(0)").slideUp(); $("#lay .article ul:eq(1)").show(); $("#lay .article p span").text("右侧");},
	 
	 function(){  $("#lay .article ul:eq(1)").hide(); $("#lay .article ul:eq(0)").slideDown();  $("#lay .article p span").text("左侧");});
	  /*===========================左右侧切换=========================================*/
	 }	 
	  
	  
	  
	  
	  
	  
	$(".fp li").click(function(){ 
	   $(this).addClass("def").siblings().removeClass("def");
	 });
	/*--------------------------首页视频---------------------------------------*/
	
    mxl(); zt();
	function mxl(){
		
	    var bw=$(window).width();
	    var bh=$(window).height();
		var lw=$(".log_pal").width();
		var lh=$(".log_pal").height();
		var l=(bw-lw)/2;
		var t=(bh-lh)/2;
		
		$(".login_bg").css({"width":bw,"height":bh});
        $(".log_pal").css({"left":l,"top":t});
		 
	    }
	
	$(window).resize( function(){
		
		mxl();
		zt();
		cde();
		cz();
    });
	
/*--------------------------登录---------------------------------------*/	
  $("article .energy nav a aside:eq(0)").css("opacity",0);
  
  $("article .energy nav a").click(function(){ $(this).addClass("def").siblings().removeClass("def"); 
                                               $(this).find("aside").slideDown().parents("a").siblings().find("aside").hide();
											   
					 });
 								
							
	$("article .energy aside:eq(1)").hide()	;				   
  $("article .energy nav a").each(function(i) {
	  
    $(this).click( function(){  $("article .energy aside:eq("+i+")").show().siblings("aside").hide();});
});	
	
	
  /*--------------------------能耗---------------------------------------*/	 
	function zt(){		 
	$("article .temp .temp_rt table tr:gt(1)").css({"color":"#686868","font-size":"14px"});
	 if($(window).innerWidth()<1380){
		$("article .temp .temp_rt table tr:gt(1)").css({"color":"#686868","font-size":"12px"});
		 }
		}
		
		
		
		
$("article .temp .temp_rt p:gt(0)").hide();		
$("article .temp .temp_rt .rt_top a").each(function(i) {
	 $(this).click( function(){
	             $(this).children("span").show().parent().siblings("a").children("span").hide();
				 $(this).addClass("acti").siblings().removeClass("acti");
				 $("article .temp .temp_rt p:eq("+i+")").show().siblings("p").hide();
				 });
});	
		
	/*--------------------------温湿度---------------------------------------*/		
		 $("article .alarm h3 p:gt(0)").hide();
		$("article .alarm h3 a").each(function(i) {
                   $(this).click(function(){ 
				               $(this).addClass("deft").siblings("a").removeClass("deft");
				               $("article .alarm h3 p:eq("+i+")").show().siblings("p").hide();
				   
				               });
                });
		/*--------------------------报警---------------------------------------*/
		
//	    $("article .video .vid_lf .menu_body:gt(0)").hide();
//		$("article .video .vid_lf .menu_head:eq(0)").addClass("cur");
//		$("article .video .vid_lf .menu_head").click(function(){   
//			                      $(this).addClass("cur").next(".menu_body").slideToggle(300).siblings(".menu_body").slideUp();
//								
//			                   $(this).siblings().removeClass("cur"); 
//			 
//			});
//		
//		
//		$("article .video .vid_lf .menu_list a").prepend("<em></em>");
//		$("article .video .vid_lf .menu_head").prepend("<em></em>");	
//		
/*--------------------------视频---------------------------------------*/		
		
		
		
		  $("article .kf a").hover( function(){ 
		        $(this).children(".k-tip").stop().delay(200).fadeIn();} , 
				function(){$(this).children(".k-tip").stop().hide();});
	 
	 
	 
        $("article .kf>.mjj>li").each(function(n) {
   
              $("article .kf>.mjj>li:eq("+n+")>a>.k-tip>.lie>li").each(function(i) {
	             $(this).css("background-position-X",-55*i);
	             $(this).click( function(){ $(this).addClass("tip-def").siblings().removeClass("tip-def");
                 $(this).parent(".lie").siblings(".quyu").children("li").removeClass("tip-def1");});
               });
				  
		 });

        $("article .kf>.mjj>li").each(function(n) {
                 $("article .kf>.mjj>li:eq("+n+")>a>.k-tip>.quyu>li").each(function(i) {
					$(this).css("background-position-X",-63*i);
					$(this).click( function(){ $(this).addClass("tip-def1").siblings().removeClass("tip-def1");
					$(this).parent(".quyu").siblings(".lie").children("li").removeClass("tip-def"); });
			     });
		});

/*---------密集架各列事件图标变换----------**/
						
	   $("article .kf .wsd a .k-tip").show();
       $("article .kf .wsd a .k-tip").hover(
	      function(){ $(this).children(".k-tip").stop().delay(200).show();} , 
		  function(){ $(this).children(".k-tip").stop().show();});
      $("article .kf .rfid a .k-tip").show();			
		

		cz();
 function cz(){
		$("article .house aside:gt(0)").hide();	
	/*------------------------默认后面几个aside隐藏-------------------------------------------------*/						
	  var Nn=$("article .house nav a").length;	
	  var sumW=0;
	  var mar=parseInt($("article .house nav a").css("margin-left"));
	  var marW=mar*Nn*2;
	  $("article .house nav a").each(function(i) {
        sumW+=$(this).innerWidth();
		
		$(this).click(function(){
			    $(this).addClass("activ").siblings("a").removeClass("activ");
				$("article .house aside:eq("+i+")").show().siblings("aside").hide();
				});

        });
	  
	 $("article .house nav").css("width",sumW+marW);
	/*------------------------nav的宽度 以及子标签a背景图片位置 ===点击a事件-====----------------------------------------------*/			
		
		$("article .house aside").each(function(i) {
            
			var Nn1=$("article .house aside:eq("+i+") a").length;
			var sumW1=0;
			var mar1=parseInt($("article .house aside:eq("+i+") a").css("margin-left"));
			var marW1=mar1*Nn1*2;
	        $("article .house aside:eq("+i+") a").each(function(i) {
                           sumW1+=$(this).innerWidth();
		                   $(this).css({"background-position-x":-70*i});
		                   $(this).click(function(){
			                           $(this).toggleClass("active");			
			               });
	
			 });	
		     $("article .house aside:eq("+i+")").css("width",sumW1+marW1+4);	
			
		      });	
			  
	/*------------------------每个aside的宽度 以及子标签a背景图片位置-------------------------------------------------*/	  
			$("article .house .kf .miTd").hide();
		/*$("article .house aside:eq(0) a:gt(2)").click(function(){
			 $(this).addClass("active").siblings("a:gt(2)").removeClass("active");
			 $(".miTd").show();	
			
        });*/ 
		$(".miTd_del").click(function(){$(".miTd").hide();});
	/*------------------------第一个aside密集架点击事件只能有一个存在-------------------------------------------------*/	
	    $("article .house aside:eq(1) a").click(function(){
			 $(this).addClass("active").siblings("a").removeClass("active");	
        });
		
			
	var mjjL=$("article .house .kf .mjj>li");
    var mjjcz=$("article .house aside:eq(0) a");
	
     $(mjjcz.eq(3)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":14},2000);
		 
		 });
	$(mjjcz.eq(4)).click(function(){
	
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":14},2000);
		$(mjjL.eq(1)).stop().animate({"bottom":14},2000);
		 
		 }); 
		 
	$(mjjcz.eq(5)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(3)).stop().animate({"bottom":-14},2000);
		$(mjjL.eq(4)).stop().animate({"bottom":-14},2000);
		 
		 }); 	 

   $(mjjcz.eq(6)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(4)).stop().animate({"bottom":-14},2000);
		 
		 }); 
		 
  
	$(mjjcz.eq(7)).click(function(){
		$(mjjL).stop().animate({"bottom":0},2000);
		 
		 });  

	$(mjjcz.eq(8)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":28},2000);
		$(mjjL.eq(1)).stop().animate({"bottom":14},2000);
		$(mjjL.eq(3)).stop().animate({"bottom":-14},2000);
		$(mjjL.eq(4)).stop().animate({"bottom":-28},2000);
		 });										   

			
			
			
			
			
			
			
			
			
			  		
		 if($(window).innerWidth()<1380){
		
		
		$("article .house aside").each(function(i) {
            
			var Nn1=$("article .house aside:eq("+i+") a").length;
			var sumW1=0;
			var mar1=parseInt($("article .house aside:eq("+i+") a").css("margin-left"));
			var marW1=mar1*Nn1*2.4;
	        $("article .house aside:eq("+i+") a").each(function(i) {
                           sumW1+=$(this).innerWidth();
		                   $(this).css({"background-position-x":-59*i});
						  
	
			 });	
		     $("article .house aside:eq("+i+")").css("width",sumW1+marW1+4);	
			
		      });	
            
			
			
			
			
	$(mjjcz.eq(3)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":10},2000);
		 
		 });
	$(mjjcz.eq(4)).click(function(){
	
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":10},2000);
		$(mjjL.eq(1)).stop().animate({"bottom":10},2000);
		 
		 }); 
		 
	$(mjjcz.eq(5)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(3)).stop().animate({"bottom":-10},2000);
		$(mjjL.eq(4)).stop().animate({"bottom":-10},2000);
		 
		 }); 	 

   $(mjjcz.eq(6)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(4)).stop().animate({"bottom":-10},2000);
		 
		 }); 
		 
  
	$(mjjcz.eq(7)).click(function(){
		$(mjjL).stop().animate({"bottom":0},2000);
		 
		 });  

	$(mjjcz.eq(8)).click(function(){
		$(mjjL).stop().animate({"bottom":0},0);
		$(mjjL.eq(0)).stop().animate({"bottom":20},2000);
		$(mjjL.eq(1)).stop().animate({"bottom":10},2000);
		$(mjjL.eq(3)).stop().animate({"bottom":-10},2000);
		$(mjjL.eq(4)).stop().animate({"bottom":-20},2000);
		 });										   

		    }
			

   }



	});	

