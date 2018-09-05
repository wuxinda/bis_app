var totalCount = 0;
var currentPage = 1;
$(document).ready(
		function() {
			var background = {
				type : 'linearGradient',
				x0 : 0,
				y0 : 0,
				x1 : 0,
				y1 : 1,
				colorStops : [ {
					offset : 0,
					color : '#d2e6c9'
				}, {
					offset : 1,
					color : 'white'
				} ]
			};
			// 获取档案出入
			Ajax.custom({
				url : config.getNewInOutAms,
				data : {

				},// 传递参数
				type : 'GET'
			}, function(data) {
				var year = new Array();
				var month = new Array();
				var day = new Array();
				if (data.data != null) {
					if (data.data.year != null) {
						for (var i = 0; i < data.data.year.length; i++) {
							var list = new Array(0, 0);
							list[0] = data.data.year[i].name
							list[1] = data.data.year[i].count
							year[i] = list
						}
					}
					if (data.data.month != null) {
						for (var i = 0; i < data.data.month.length; i++) {
							var list1 = new Array(0, 0);
							list1[0] = data.data.month[i].name
							list1[1] = data.data.month[i].count
							month[i] = list1
						}
					}
					if (data.data.day != null) {
						for (var i = 0; i < data.data.day.length; i++) {
							var list2 = new Array(0, 0);
							list2[0] = data.data.day[i].name
							list2[1] = data.data.day[i].count
							day[i] = list2
						}
					}
				}
				// ri
				$('#day').jqChart({
					noDataMessage : {
						text : '无数据',
						font : '20px sans-serif'
					},
					/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
					legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
						location : 'bottom',
						font : '10px sans-serif',
					},
					animation : {
						duration : 1
					},/* 图片动画 */
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					shadows : {
						enabled : true
					},
					series : [ {
						type : 'pie',
						labels : {
							stringFormat : '%.1f%%',
							valueType : 'percentage',
							font : '18px sans-serif',
							fillStyle : 'black'
						},
						data : day,
						labelsPosition : 'outside', // inside, outside 文字位置
						labelsAlign : 'circle', // circle, column 数字位置
						labelsExtend : 10,// 线长度
						leaderLineWidth : 2,// 线粗细
						leaderLineStrokeStyle : 'red' // 线颜色
					} ]
				});
				// 月
				$('#month').jqChart({
					noDataMessage : {
						text : '无数据',
						font : '20px sans-serif'
					},
					/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
					legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
						location : 'bottom',
						font : '10px sans-serif',
					},
					animation : {
						duration : 1
					},/* 图片动画 */
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					shadows : {
						enabled : true
					},
					series : [ {
						type : 'pie',
						labels : {
							stringFormat : '%.1f%%',
							valueType : 'percentage',
							font : '18px sans-serif',
							fillStyle : 'black'
						},
						data : month,
						labelsPosition : 'outside', // inside, outside 文字位置
						labelsAlign : 'circle', // circle, column 数字位置
						labelsExtend : 10,// 线长度
						leaderLineWidth : 2,// 线粗细
						leaderLineStrokeStyle : 'red' // 线颜色
					} ]
				});
				// 年
				$('#year').jqChart({
					noDataMessage : {
						text : '无数据',
						font : '20px sans-serif'
					},
					/* title: { text: 'Pie Chart Labels' }, *//* 图标上方标题 */
					legend : { /* title: { text: '档案类型',font: '15px sans-serif' }, *//* 图例标题 */
						location : 'bottom',
						font : '10px sans-serif',
					},
					animation : {
						duration : 1
					},/* 图片动画 */
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					shadows : {
						enabled : true
					},
					series : [ {
						type : 'pie',
						labels : {
							stringFormat : '%.1f%%',
							valueType : 'percentage',
							font : '18px sans-serif',
							fillStyle : 'black'
						},
						data : year,
						labelsPosition : 'outside', // inside, outside 文字位置
						labelsAlign : 'circle', // circle, column 数字位置
						labelsExtend : 10,// 线长度
						leaderLineWidth : 2,// 线粗细
						leaderLineStrokeStyle : 'red' // 线颜色
					} ]
				});
			});
			// 待执行档案列表
			getPageData(1);
		});

function chaDay(date1) {
	var date = new Date(date1);
	var date2 = new Date(); // 结束时间
	var date3 = date2.getTime() - date.getTime() // 时间差的毫秒数
	var days = Math.floor(date3 / (3600 * 1000))// 计算出相差小时数
	// alert(days);
	return days;

}

// 上一页
$(".arc_b_prev").click(function() {
	prePageAction();
});
// 下一页
$(".arc_b_next").click(function() {
	nextPageAction();
});
// 首页
$("#firstpage").click(function() {
	sy();
});
// 尾页
$("#lastpage").click(function() {
	wy();
});

// 上一页
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
	getPageData(currentPage);
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
	getPageData(currentPage);
}

// 首页
function sy() {
	if (currentPage == 1) {
		return;
	} else {
		currentPage = 1;
		$("#nowpage").val(1);
		getPageData(1);
	}
}
// 尾页
function wy() {
	if (currentPage == totalPage) {
		return;
	} else {
		currentPage = totalPage;
		$("#nowpage").val(totalPage);
		getPageData(totalPage);
	}
}
function getPageData(i) {
	Ajax.custom({
		url : config.getNewAuditAms,
		data : {
			"status" : "1",// 0.待审批 1.审核通过 2.审核拒绝 3.已完成
			"pageIndex" : i,
			"pageSize" : "9",
		},// 传递参数
		type : 'GET'
	}, function(data) {
		for (var i = 0; i < data.data.data.length; i++) {
			data.data.data[i].chaday = chaDay(data.data.data[i].mtime);
			data.data.data[i].mh = new Date(data.data.data[i].mtime)
					.format("hh:mm:ss");
			data.data.data[i].md = new Date(data.data.data[i].mtime)
					.format("yyyy-MM-dd");
		}
		$("#auditAms").html(template.render("auditAms-data", {
			list : data.data.data
		}));
		totalCount = data.data.count;
		if (totalCount % 9 == 0) {
			totalPage = totalCount / 9;
		} else {
			totalPage = parseInt(totalCount / 9) + 1;
		}
		$("#totlePageCount").html(
				"共计" + totalPage + "页&nbsp " + totalCount + "条数据");
		$("#nowpage").val(currentPage);

	});
}