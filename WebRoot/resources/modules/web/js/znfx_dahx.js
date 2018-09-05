var totalCount = 0;
var currentPage = 1;
var totalPage =1; 
$(document).ready(function() {
	$(".leftsidebar ul li:nth-child(1)").addClass("datanavclickstyle");
	$(".datj_right_sarch_submit").click(function(){
		archivesParticulars(1)
		currentPage = 1;
	})
	chang1()
});
function chang1(){
	$("#index").attr("class","no_select")
	$("#znfx_dahx").attr("class","select")
	$("#zntj_datj").attr("class","no_select")
	$("#smartlog1").attr("class","no_select")
	$("#ywcz_dacx").attr("class","no_select")
	$("#deviceop1").attr("class","no_select")
}
//右边导航栏
function change(obj) {
	var arr = $(".leftsidebar ul li").removeClass("datanavclickstyle");
	$(obj).addClass("datanavclickstyle");
}
//加载档案
function archivesParticulars(i){
	var keyWord=$(".datj_right_sarch_text").val();
	Ajax.custom({
		url : config.searchArchives,
		data : {
			data:keyWord,
			page:i
		},//传递参数
		type : 'post'
	}, function(data) {
		if (data.data.data != null) {
			$(".datj_right_top_table").html(template.render("archives-data-list-tmpl", {
				list : data.data.data
			}));
			totalCount = data.data.count;
			if (totalCount % 3 == 0) {
				totalPage = totalCount / 3;
			} else {
				totalPage = parseInt(totalCount / 3) + 1;
			}
			$("#totlePageCount").html(
					"共计" + totalPage + "页&nbsp " + totalCount + "条数据");
			$("#nowpage").val(currentPage);
		}
	});
}
//回车跳转页面
function enterJump(){
	var curpage = parseInt($("#nowpage").val());
	if (isNaN(curpage)) {
		alert("请输入数字");
		return;
	}
	if (curpage <= 1) {
		return;
	}
	if (curpage > totalPage) {
		// alert("超过总页数");
		return;
	}
	$("#nowpage").val(curpage);
	currentPage = curpage;
	archivesParticulars(curpage)
	
}
//上一页
function prePageAction() {
	var curpage = parseInt($("#nowpage").val());
	if (isNaN(curpage)) {
		alert("请输入数字");
		return;
	}
	if (curpage <= 1) {
		return;
	}
	if (curpage > totalPage) {
		// alert("超过总页数");
		return;
	}
	$("#nowpage").val(curpage - 1);
	currentPage = curpage - 1;
	archivesParticulars(currentPage);
}

// 下一页
function nextPageAction() {
	var curpage = parseInt($("#nowpage").val());
	if (isNaN(curpage)) {
		alert("请输入数字");
		return;
	}
	if (curpage >= totalPage || curpage < 0) {
		// alert("超过总页数或页数为负");
		return;
	}
	$("#nowpage").val(curpage + 1);
	currentPage = curpage + 1;
	archivesParticulars(currentPage);
}

// 首页
function sy() {
	if (currentPage == 1) {
		return;
	} else {
		currentPage = 1;
		$("#nowpage").val(1);
		archivesParticulars(1);
	}
}
// 尾页
function wy() {
	if (currentPage == totalPage) {
		return;
	} else {
		currentPage = totalPage;
		$("#nowpage").val(totalPage);
		archivesParticulars(totalPage);
	}
}