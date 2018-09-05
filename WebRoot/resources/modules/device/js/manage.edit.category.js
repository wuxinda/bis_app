$(document).ready(function() {
	
});
//库房选择
function categoryName1(id,name) {
	$("#category1").html(name);
	$("#category1").attr("data",id);
	$.ajax({
		type : 'POST',
		url : BASE_URL + '/wmsStoreArea/getStoreAreaByStoreId',
		data : 'storeId=' + id,
		dataType : 'json',
		timeout : 10000,
		success : function(data) {
			if (data.status == 0) {
			$("#categoryName4").html("");
			$("#addDevice").html("");
			$("#category4").html("请选择库区");
			$.each(data.data, function(index, item) {
			$("#categoryName4").append("<li onclick=\"categoryName4("+item.stroreAreaId+",'"+item.name+"')\">"+item.name+"</li>");
			});
			} else {
			}
			return false;
		}
	});
	addDevice();
}
//设备分类选择
function categoryName2(id,name) {
	$("#category2").html(name);
	$("#category2").attr("data",id);
	$.ajax({
			type : 'get',
			url : BASE_URL + '/deviceManage/getBrandByCategoryId',
			data : 'categoryId=' + id,
			dataType : 'json',
			timeout : 10000,
			success : function(data) {
				if (data.status == 0) {
				$("#categoryName3").html("");
				$("#addDevice").html("");
				$("#category3").html("请选择品牌");
				$.each(data.data, function(index, item) {
				$("#categoryName3").append("<li onclick=\"categoryName3("+item.brandId+",'"+item.name+"')\">"+item.name+"</li>");
				});
				} else {
				}
				return false;
			}
		});
}
//选择品牌
function categoryName3(id,name) {
	$("#category3").html(name);
	$("#category3").attr("data",id);
	addDevice();
}
//选择库区
var fixStoreArea="" ;
function categoryName4(id,name) {
	$("#category4").html(name);	
	$("#category4").attr("data",id);
	addDevice();
}
//添加设备－－满足条件方能添加
function addDevice() {
	$("#addDevice").html("");
	//组装分类参数 格式为id,name
	//库房
	var category1 = $("#category1").attr("data")+","+$("#category1").html();
	//设备分类
	var category2 = $("#category2").attr("data")+","+$("#category2").html();
	//品牌
	var category3 = $("#category3").attr("data")+","+$("#category3").html();
	//库区
	var category4 = $("#category4").attr("data")+","+$("#category4").html();
	//库房，分类和品牌选择的情况下，可以添加
	if($("#category1").html()!='请选择库房' &&$("#category2").html()!='请选择设备分类' && $("#category3").html()!='请选择品牌'&& $("#category4").html()!='请选择库区'){
		$("#addDevice").html("<a href='"+BASE_URL+"/deviceManage/add?category1="+category1+"&category2="+category2+"&category3="+category3+"&category4="+category4+"' class='btn btn-sm btn-default load-content'>添加</a>");
	}
}
