var storeId = 0; // 选中库房编号
var stroreAreaId = 0; // 选中库区编号
$(document).ready(function() {
	// 初始化库房
	loadStore();
	// 初始化库区
	loadStroreArea();
	// 初始化密集列
	loadImsAndVms();
	$("#store").change(function() {
		changeStore();
	});
	$("#stroreArea").change(function() {
		changeStroreArea();
	});
	$(".button").click(function() {
		selectArchivesList();
	})
	// 全选框
	$("#checkall").click(function() {
		if (this.checked) {
			$("[name=checkbox2]").prop("checked", true);
		} else {
			$("[name=checkbox2]").prop("checked", false);
		}
		check(null)
	});
	// 确认借档
	$(".timing_btn").click(function() {
		timing()
		$(".table_sbjc_archive_table").empty();
		$(".police_list_selected").empty();
		$("[name=checkbox2]").prop("checked", false);
		$(".check_all").prop("checked", false);
		selectArchivesList();
		check(null)
	});
	$(".close_img").click(function() {
		timing()
		$(".table_sbjc_archive_table").empty();
		$(".police_list_selected").empty();
		$("[name=checkbox2]").prop("checked", false);
		$(".check_all").prop("checked", false);
		selectArchivesList();
		check(null)
	});
	chang1()
});
function chang1(){
	$("#index").attr("class","no_select")
	$("#znfx_dahx").attr("class","no_select")
	$("#zntj_datj").attr("class","no_select")
	$("#smartlog1").attr("class","no_select")
	$("#ywcz_dacx").attr("class","select")
	$("#deviceop1").attr("class","no_select")
}
// 加载库房
function loadStore() {
	// 初始化库房
	$
			.ajax({
				type : 'POST',
				url : config.getWmsStore,
				dataType : 'json',
				async : false,
				success : function(json) {
					var data = json.data;
					var optionstring = "<option>选择库房</option>";
					for (var i = 0; i < data.length; i++) {
						optionstring = optionstring + "<option value=\""
								+ data[i].storeId + "\" >" + data[i].name
								+ "</option>";
					}
					$("#store").append(optionstring);
					var SAoptionstring = "<option>选择库区</option>";
					$("#stroreArea").append(SAoptionstring);
					var DMoptionstring = "<option>选择密集架</option>";
					$("#deviceManage").append(DMoptionstring);
				}

			});
}

// 加载库区
function loadStroreArea() {
	// 初始化库区
	if (isNaN(storeId) || storeId == 0) {
		return;
	}
	$("#stroreArea").empty();
	$.ajax({
		type : 'POST',
		url : config.getWmsStroreArea,
		data : {
			storeId : storeId
		},
		dataType : 'json',
		async : false,
		success : function(json) {
			var data = json.data;
			var optionstring = "<option>选择库区</option>";
			for (var i = 0; i < data.length; i++) {
				optionstring = optionstring + "<option value=\""
						+ data[i].stroreAreaId + "\" >" + data[i].name
						+ "</option>";

			}
			$("#stroreArea").append(optionstring);
		}
	});
}

// 加载密集架列
function loadImsAndVms() {
	if (isNaN(storeId) || storeId == 0) {
		return;
	}
	if (isNaN(stroreAreaId) || stroreAreaId == 0) {
		return;
	}
	$("#deviceManage").empty();
	$.ajax({
		type : 'POST',
		url : config.getStoreDeviceList,
		data : {
			storeId : storeId,
			stroreAreaId : stroreAreaId,
			categoryId : 5
		},
		dataType : 'json',
		async : false,
		success : function(json) {
			var data = json.data;
			var optionstring = "<option>选择密集架</option>";
			for (var i = 0; i < data.length; i++) {
				optionstring = optionstring + "<option value=\""
						+ data[i].sortOrder + "\" >" + data[i].name
						+ "</option>";
			}
			$("#deviceManage").append(optionstring);
		}
	})
}
// 改变库房得到库区
function changeStore() {
	$("#lie").empty();
	stroreAreaId = 0; // 选中库区编号
	deviceId = 1; // 选中列
	storeId = $("#store").children('option:selected').val();// 这就是selected的值
	// 初始化库区
	loadStroreArea();
}
// 改变库区得到列
function changeStroreArea() {
	// 初始化库区
	$("#lie").empty();
	storeId = $("#store").children('option:selected').val();// 这就是selected的值
	stroreAreaId = $("#stroreArea").children('option:selected').val(); // 选中库区编号
	// 初始化密集架
	initVms = false;
	loadImsAndVms();
}
// 加载密集架列
function selectArchivesList() {
	$.ajax({
				type : 'POST',
				url : config.selectArchivesListFroPlace,
				data : {
					inflag : '1',
					stroreId : $("#store").children('option:selected').val(),
					stroreAreaId : $("#stroreArea").children('option:selected')
							.val(),
					storeColumn : $("#deviceManage")
							.children('option:selected').val(),
					storeLr : $("#storeLr").children('option:selected').val(),
					storeSection : $("#storeSection").children(
							'option:selected').val(),
					storeLayer : $("#storeLayer").children('option:selected')
							.val(),
					archiveno : $("#input-box").val()
				},
				dataType : 'json',
				async : false,
				success : function(data) {
					$(".table_sbjc_archive_table").empty();
					var security = null;
					for (var i = 0; i < data.data.length; i++) {
						if (data.data[i].security == 0) {
							security = '普通'
						} else if (data.data[i].security == 1) {
							security = '秘密'
						} else if (data.data[i].security == 2) {
							security = '机密'
						} else if (data.data[i].security == 3) {
							security = '绝密'
						}
						$(".table_sbjc_archive_table")
								.append(
										'<tr onclick="check(this)">'
												+ '<td width="50px">'
												+ data.data[i].archivesId
												+ '</td>'
												+ '<td width="101px">'
												+ data.data[i].archiveno
												+ '</td>'
												+ '<td width="110px">'
												+ data.data[i].name
												+ '</td>'
												+ '<td width="228px">'
												+ data.data[i].title
												+ '</td>'
												+ '<td width="253px">'
												+ data.data[i].storeplace
												+ '</td>'
												+ '<td width="50px">'
												+ security
												+ '</td>'
												+ '<td width="50px">'
												+ '	<input type="checkbox" name="checkbox2" class="check_box"/>'
												+ '</td>' + '</tr>')
					}
				}
			})
}
// 提交调档方法
function check(ent) {
	$(".police_list_selected").empty();
	$("input[name='checkbox2']:checked").each(
			function() {
				var archivesNo = $(this).parent().siblings().eq(1).html()
				var type = $(this).parent().siblings().eq(2).html()
				var security = $(this).parent().siblings().eq(4).html()
				var archivesId = $(this).parent().siblings().eq(0).html()
				$(".police_list_selected").append(
						'<tr>' + '<td width="51px">' + archivesId + '</td>'
								+ '<td width="143px">' + type + '</td>'
								+ '<td width="105px">' + archivesNo + '</td>'
								+ '</tr>')
			});
	var archivesNo = $(ent).children("td").eq(1).html()
	var name = $(ent).children("td").eq(2).html()
	$(".camera_police_particulars_police").empty();
	if(name!=undefined||archivesNo!=undefined){
	$.ajax({
		type : 'POST',
		url : config.selectArchivesListFroPlace,
		data : {
			name : name,
			archiveno : archivesNo
		},
		dataType : 'json',
		async : false,
		success : function(data) {
			$(".camera_police_particulars_police").append(
					'<div class="camera_police_left ">'
							+ '<p>档案名称：<i class="ranking">'
							+ data.data[0].title + '</i></p>'
							+ '<p>存址：<i class="ranking">'
							+ data.data[0].storeplace + '</i></p>'
							+ '<p>RFID号：<i class="ranking">'
							+ data.data[0].rfidnum + '</i></p>' + '</div>'
							+ '<div class="camera_police_right">'
							+ '<p>现有数量：<i class="ranking">'
							+ data.data[0].nowNumber + '</i></p>'
							+ '<p>总数量：<i class="ranking">'
							+ data.data[0].totalNumber + '</i></p>'
							+ '<p>保管年限：<i class="ranking">'
							+ data.data[0].keepyear + '</i></p>' + '</div>')
		}
	})
	}
}
function timing() {
	$(".police_list_selected").children().children().each(function() {
		var archivesId=$(this).children().eq(0).html()
			$.ajax({
				type : 'POST',
				url : config.insertArchivesAuditIn,
				data : {
					type : 0,
					archivesId : archivesId
				},
				dataType : 'json',
				async : false,
				success : function(data) {
			}
		})
	})
}