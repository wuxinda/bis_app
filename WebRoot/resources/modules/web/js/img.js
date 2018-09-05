var storeId;
var type=0;
$(document).ready(function() {
	chang1()
	loadStore()
	imgLold(0)
	miniimg("none")
	$("#store").change(function(){  
		changeStore();
    });
	$("#tpsc").click(function(){  
		imgCloseSet()
    });
})
function chang1(){
	$("#index").attr("class","no_select")
	$("#znfx_dahx").attr("class","no_select")
	$("#zntj_datj").attr("class","no_select")
	$("#smartlog1").attr("class","no_select")
	$("#ywcz_dacx").attr("class","select")
	$("#deviceop1").attr("class","no_select")
}
function imgCloseSet(){
	var display=$(".images_close").css('display'); 
	if(display=='none'){
		$(".archive_inventory").html("修改完成");
		$(".images_close").css('display','block'); 
	}else{
		$(".archive_inventory").html("修改图片");
		$(".images_close").css('display','none'); 
	}
	
}

function deleteimg(imgUrlId){
	 $.ajax({
         type: "POST",
         url:config.deleteImg,
         data:{
        	 imgUrlId:imgUrlId
         },
         dataType:"json",
         success: function(data) {
         }
	  })
	    setTimeout(function(){miniimg("block");imgLold(0)},300)
}
function miniimg(style){
	 $("#thumburlShow_list p").empty();
	 $("#tpsc").empty();
	 $.ajax({
         type: "POST",
         url:config.getImgUrl,
         data:{
       	 userId:1,
       	 storeId:storeId  
         },
         dataType:"json",
         success: function(data) {
        	var imgdata="";
        	for(var i=0;i<data.data.length;i++){
        		imgdata=imgdata+"<a><img onclick='imgLold("+i+")' src=\'"+config.serverPath+"/resources/img"+data.data[i].imageUrl+"\' width='117px' height='85px'/><img class='images_close' style='display:"+style+"' onclick='deleteimg("+data.data[i].id+")' src=\'"+config.serverPath+"/resources/images/4.2/ams/delete.png'/></a>";
        	}
        	$("#thumburlShow_list p").append(imgdata);
        	if(data.data.length!=0){
        		if(style=='none'){
        			$("#tpsc").append("<button class='archive_inventory'>修改图片</button>");
        		}else{
        			$("#tpsc").append("<button class='archive_inventory'>修改完成</button>");
        		}
        		
        	}
         }
	  })
} 
function changeStore(){
    stroreAreaId=0;  //选中库区编号
    deviceId=1;  //选中列
	storeId=$("#store").children('option:selected').val();//这就是selected的值
	miniimg("none")
	imgLold(0)
}
//加载图片
function imgLold(i){
	$("#thumburlShow").empty();
	  $.ajax({
          type: "POST",
          url:config.getImgUrl,
          data:{
        	  userId:1,
        	storeId:storeId
          },
          dataType:"json",
          success: function(data) {
        	  if(i==-1){i=data.data.length-1}
        	  $("#thumburlShow").append("<img src=\'"+config.serverPath+"/resources/img"+data.data[i].imageUrl+"\' width='900px' height='500px'/>");
          }
	  })
}
function loadStore(){
	//初始化库房
	$.ajax({
	     type:'POST',
	     url:config.getWmsStore,
	     dataType:'json',
	     async:false,
	     success:function(json) {
		var data=json.data;
		var	 optionstring="";
		for(var i=0;i<data.length;i++){
       		optionstring = optionstring+"<option value=\"" + data[i].storeId + "\" >" + data[i].name + "</option>";
       	 }
		$("#store").append(optionstring);
		storeId=$("#store").children('option:selected').val();
        	}
			
	});
	
}
//图片上传
function setImg(obj){
	 $.ajax({
         type: "POST",
         url:config.getImgUrl,
         data:{
       	  userId:1,
       	storeId:storeId
         },
         dataType:"json",
         success: function(data) {
        	 if(data.data.length>=16){
        		 alert("图片上传过多，请先删除不需要的图片")
        	 }else{
        		//用于进行图片上传，返回地址
                 var f=$(obj).val();
                 if(f == null || f ==undefined || f == ''){
                     return false;
                 }
                 if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
                 {
                     alertLayel("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
                     $(obj).val('');
                     return false;
                 }
                 var data = new FormData();
                 $.each($(obj)[0].files,function(i,file){
                     data.append('avatar', file);
                 });
                 data.append('storeId',storeId);
                 $.ajax({
                     type: "POST",
                     url: config.imageService,
                     data: data,
                     cache: false,
                     contentType: false,    //不可缺
                     processData: false,    //不可缺
                     dataType:"json",
                     success: function(suc) {
                         if(suc.code==0){
                                 $("#thumbUrl").val(suc.message);//将地址存储好
                                 $("#thumburlShow").attr("src",suc.message);//显示图片     
                             }else{
                             alertLayel("上传失败");
                             $("#url").val("");
                             $(obj).val('');
                         }
                     },
                     error: function(XMLHttpRequest, textStatus, errorThrown) {
                         alertLayel("上传失败，请检查网络后重试");
                         $("#url").val("");
                         $(obj).val('');
                     }
                 });
                 setTimeout(function(){miniimg("none");imgLold(-1);$(obj).val("");},300)
        	 }
        }
	  })
        }