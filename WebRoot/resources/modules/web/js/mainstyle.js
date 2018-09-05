// JavaScript Document

$(function(){
	
	  login();
		
	  function login()
	  { var bht=window.innerHeight;
	  
	  $(".log-arti").css("height",bht-60);
	  }
	  
	  
	   /*-----------------------------------------登录页面的整屏---------------------------------------*/
	//$("article .wrap .wsd li a span:eq(0)").css({"margin-top":10});
	$("article .wrap .wsd2 table tr:nth-child(2n+3)").css("background-color","rgba(0,0,0,.06)");
	$("article .wrap .wsd2 table tr:gt(0)").hover(function(){ $(this).css("background","rgba(100,202,255,.4)");},function(){ $(this).css("background","none");$("article .wrap .wsd2 table tr:nth-child(2n+3)").css("background-color","rgba(0,0,0,.06)");});
	
	$("article .wrap .dor-r table tr:nth-child(2n+3)").css("background-color","rgba(0,0,0,.05)");
	$("article .wrap .dor-r table tr:gt(0)").hover(function(){ $(this).css("background","rgba(100,202,255,.4)");},function(){ $(this).css("background","none");$("article .wrap .dor-r table tr:nth-child(2n+3)").css("background-color","rgba(0,0,0,.05)");});
	$("article .wrap .dor-r table td:nth-child(4n)").css("color","#ff4c43");
	
	$("article .wrap .dor-r table td:nth-child(4n+1)").each(function(i) {
	     $(this).text(i+1).css({"color":"#ff4c43","font-size":"18px","font-family":"Arial"});
	});
	
	/*---------------------------------门禁列表---------------------------------------------*/
	
	$("article .wrap .crumb p a").click(function(){
		$(this).addClass("cru-act").siblings().removeClass("cru-act");
	});
		
	/*------------------------------------------------------------------------------*/	
	$(document).ready(function(){
	   setTimeout(function () { abc(); }, 2000);
	});
		
	function abc(){
		$("article .wrap .wsd .w-wd .w-wd-nei").animate( {opacity:'0.1'},2000);
	}
	
	
	show();
	
	function show(){
		var bw=$(window).width();	
		if(bw>1370){
			$("nav .wrap li span").each(function(i) {
				$(this).css("background-position-x",-39*i);
			});
	
			$("nav .wrap li span:gt(1)").css("margin-left",52);
			$("nav .wrap li span:lt(1)").css("margin-left",52);
			$("nav .wrap li span:eq(2)").css("margin-left",50);
			$("nav .wrap li span:eq(1)").css("margin-left",52);
		
			$("nav .wrap li").click(function(){
				$(this).addClass("defat");
				$(this).siblings("li").removeClass("defat");
			});
			
			$("footer .wrap p:eq(0)").css("margin-top",20);
	 
			$(".kf2 table th:eq(0)").css({"width":66,"height":41});
			$(".kf2 table th:eq(1)").css({"width":108});
			$(".kf2 table th:eq(2)").css({"width":80});
			$(".kf2 table th:eq(3)").css({"width":46});
	 
			$("article .wrap .kf2 .kf2-l figure.bjxx  table  th:eq(0)").css({"width":66});
			$("article .wrap .kf2 .kf2-l figure.bjxx  table  th:eq(1)").css({"width":108});
			$("article .wrap .kf2 .kf2-l figure.bjxx  table  th:eq(2)").css({"width":152});
	 
	 		if ($("article .wrap .kf2").width()==1300){
				//$("article").css("height",786);  
				//$("article .wrap").css("width",1300);
			};
	
			$("article .wrap .wsd2 table th:eq(0)").css({"width":232});
			$("article .wrap .wsd2 table th:eq(1)").css({"width":165});
			$("article .wrap .wsd2 table th:eq(2)").css({"width":165});
			
			$("article .wrap .dor-r table th:eq(0)").css({"width":75});
			$("article .wrap .dor-r table th:eq(1)").css({"width":194});
			$("article .wrap .dor-r table th:eq(2)").css({"width":204});
	
	 /*-----------------------------------------库房1 和库房2 切换---------------------------------------*/	
		}
		if(bw<=1370){
			$("nav .wrap li span").each(function(i) {
				$(this).css("background-position-x",-34*i);
		    });
		
			$("nav .wrap li span:gt(1)").css("margin-left",40);
			$("nav .wrap li span:lt(1)").css("margin-left",38);
			$("nav .wrap li span:eq(2)").css("margin-left",24);
			$("nav .wrap li span:eq(1)").css("margin-left",40);
			
			$("nav .wrap li").click(function(){
				$(this).addClass("defat");
				$(this).siblings("li").removeClass("defat");
			});
			
			$("footer .wrap p:eq(0)").css("margin-top",10);
			
			$(".kf2 table th:eq(0)").css({"width":42});
	 
			if ($("article .wrap .kf2").width()==1000){
				$("article").css("height",630);
				$("article .wrap").css("width",1000);
			};
			
			$("article .wrap .kf2 .kf2-l figure.bjxx  table  th:eq(0)").css({"width":34});
	       	$("article .wrap .kf2 .kf2-l figure.bjxx  table  th:eq(1)").css({"width":122});
	       	$("article .wrap .kf2 .kf2-l figure.bjxx  table  th:eq(2)").css({"width":102});
	
			$("article .wrap .wsd2 table th:eq(0)").css({"width":170});
			$("article .wrap .wsd2 table th:eq(1)").css({"width":122});
			$("article .wrap .wsd2 table th:eq(2)").css({"width":122});
			
			$("article .wrap .dor-r table th:eq(0)").css({"width":58});
			$("article .wrap .dor-r table th:eq(1)").css({"width":152});
			$("article .wrap .dor-r table th:eq(2)").css({"width":158});
	
	 
	 /*-----------------------------------------库房1 和库房2 切换---------------------------------------*/		
		}
	}
		
		
		
	/*--------------------------------------------------------------------------*/
	mjj();
	function mjj(){
		var bw=$(window).width();	
		if(bw>1370){
			$("article .wrap .m-lie a").click(function(){
				$(this).addClass("m-act").siblings().removeClass("m-act");
			});
	
	
			$("article .wrap .mjj-rb .mjj-rb-l li").each(function(i) {
				$(this).children("span").css("background-position-x",-109*i);
		    });
	
			/*$("article .wrap .mjj-rb .mjj-rb-l li").click(function(){
				$(this).addClass("cz-act").siblings().removeClass("cz-act");
				$("article .wrap .mjj-rb .mjj-rb-r li").removeClass("cz-qact");
			});
	*/
			$("article .wrap .mjj-rb .mjj-rb-r li").each(function(i) {
				$(this).children("span").css("background-position-x",-121*i);
			});
	
			/*$("article .wrap .mjj-rb .mjj-rb-r li").click(function(){
				$(this).addClass("cz-qact").siblings().removeClass("cz-qact");
				$("article .wrap .mjj-rb .mjj-rb-l li").removeClass("cz-act");
			});*/
			
			var sw=$("header .wrap p a span");
			sw.css({"right":4-sw.width()/2});
		}
		if(bw<=1370){
			$("article .wrap .m-lie a").click(function(){
				$(this).addClass("m-act").siblings().removeClass("m-act");
			});
	
			$("article .wrap .mjj-rb .mjj-rb-l li").each(function(i) {
				$(this).children("span").css("background-position-x",-84*i);
		    });
	
			$("article .wrap .mjj-rb .mjj-rb-l li").click(function(){
				$(this).addClass("cz-act").siblings().removeClass("cz-act");
				$("article .wrap .mjj-rb .mjj-rb-r li").removeClass("cz-qact");
			});
	
	
			$("article .wrap .mjj-rb .mjj-rb-r li").each(function(i) {
				$(this).children("span").css("background-position-x",-92*i);
			});
	
			$("article .wrap .mjj-rb .mjj-rb-r li").click(function(){
				$(this).addClass("cz-qact").siblings().removeClass("cz-qact");
				$("article .wrap .mjj-rb .mjj-rb-l li").removeClass("cz-act");
			});
		
		
			var sw=$("header .wrap p a span");
			sw.css({"right":-sw.width()/2});
		}
	}
	/*------------------------------------------------------------------------------*/	
		
	dg();
	function dg(){
		var bw=$(window).width();
		if(bw>1370){
			$("article .wrap  .a-top-gd .crumb p .qcz").each(function(i) {
				$(this).children("span").css("background-position-x",-52*i);
			});
			
			$("article .wrap  .a-top-gd .crumb p .qcz").click(function(){
				$(this).addClass("qcz-act").siblings().removeClass("qcz-act");
			});
		}
		
		if(bw<=1370){
			$("article .wrap  .a-top-gd .crumb p .qcz").each(function(i) {
				$(this).children("span").css("background-position-x",-41*i);
			});
			
			$("article .wrap  .a-top-gd .crumb p .qcz").click(function(){
				$(this).addClass("qcz-act").siblings().removeClass("qcz-act");
			});
		}
	}	
	/*--------------------------------------------------------------------------------------*/		
		
	
		
	$(window).resize(function() {
		dg();
		show();
		mjj();
		login();
	});
		
	/*---------------------------------------------------------------------------------*/	
		
		
		
	$("article .wrap .R-bj li a").append("<h5></h5>");
	$("article .wrap .R-bj li a h5").hide();
		
	$("article .wrap .R-bj li a").hover(function(){ $(this).children("h5").fadeIn();},function(){$(this).children("h5").fadeOut(0);});
		
	function meng(){
		$("article .wrap .R-bj ul span s").each(function(i) {
			$(this).text(i+1);
	    });
	}
		
	meng();
		
	$("article .wrap .R-bj li a h5").click(function(){
		$(this).parent("a").detach();
		meng();
	});
			
	$("article .wrap .R-bj ul .morebj").hide();
	$("article .wrap .R-bj ul .morebjxx2").hide();
		
	$("article .wrap .R-bj ul .morebjxx").mousedown( function(){ $(".morebj").slideDown();
		$(this).hide();
		$("article .wrap .R-bj ul .morebjxx2").show();
	});
		
	$("article .wrap .R-bj ul .morebjxx2").mousedown( function(){ $(".morebj").slideUp();
		$(this).hide();
		$("article .wrap .R-bj ul .morebjxx").show();
	});
		
	var leg=$("article .wrap .R-bj li").length;
	if(leg>4){
		$("article .wrap .R-bj ul .morebjxx").show();
	}else{
		$("article .wrap .R-bj ul .morebjxx").hide(); 
	}
	
});
	
	
	
