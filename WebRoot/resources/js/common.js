/**
 * 定义全局变量
 */
var img_done = "<img src='" + STATIC_URL + "/images/ico-done.gif' border='0' />";
var img_delete = "<img src='" + STATIC_URL + "/images/ico-delete.gif' border='0' />";
var img_warning = "<img src='" + STATIC_URL + "/images/ico-warning.gif' border='0' />";
var img_loading_small = "<img src='" + STATIC_URL + "/images/loading-small.gif' border='0' />";
var img_loading_icon = "<i class='fa fa-spin fa-spinner'></i>";
var back_listing = false; // 编辑表单提交处理完毕后，是否返回列表管理

$(document).ready(function() {
	/**
	 * 载入导航轨迹
	 */
	loadNavigation();
	
	//全局的ajax访问，处理ajax清求时session超时 
    $.ajaxSetup({ 
	    contentType : "application/x-www-form-urlencoded;charset=utf-8", 
	    complete : function(XMLHttpRequest, textStatus) { 
	    	processSessionTimeout(XMLHttpRequest);
	    } 
    });
	
	/**
	 * 基于 html5 的 ajax + pushState 实现无刷新加载内容，并同时修改 URL 地址栏
	 */
	$.pjax({
        selector: 'a.load-content',
        container: '#content', // 内容替换的容器
        show: '',  // 展现的动画，支持默认和fade, 可以自定义动画方式，这里为自定义的function即可。
        cache: false,  // 是否使用缓存
        storage: false,  // 是否使用本地存储
        titleSuffix: '', // 标题后缀
        complete : function(xhr){
        	processSessionTimeout(xhr);
        }
    });
	
	//处理ajax和pjax的session登陆失效
	function processSessionTimeout(xhr) {
		var sessionstatus = xhr.getResponseHeader("sessionstatus");
		var redirectUri = xhr.getResponseHeader("redirectUri");
    	if (sessionstatus == "timeout") { 
	    	bootbox.alert("登陆超时,请重新登陆", function(){
	    		window.location.replace( BASE_URL + '/adminLogin/login '+ (redirectUri ? ('?urlContinue=' + redirectUri) : '')); 
	    		});
	    }
	    if (sessionstatus == "notPermission") { 
	    	bootbox.alert("您暂时没有该功能的访问权限！", function(){
	    		window.location.replace( BASE_URL + '/ '+ (redirectUri ? ('?urlContinue=' + redirectUri) : '')); 
	    		});
	    }
    	if (sessionstatus == "not") { 
	    	bootbox.alert("登陆超时,请重新登陆", function(){
	    		window.location.replace( BASE_URL + '/adminLogin/login '+ (redirectUri ? ('?urlContinue=' + redirectUri) : '')); 
	    		});
	    }
	}
	
	
	/**
	 * 切换导航
	 */
	$('li.nav-one').click(function(){
		if (! $(this).hasClass('active')) {
			$('li.nav-one').removeClass('active');
			$(this).addClass('active');
			
			$('.nav-content').removeClass('active in');
			$($(this).find('a').attr('href')).addClass('active in');
			
			$('#nav_one_now').text($(this).children('a:first').text());
		}
	});
	
	/**
	 * 初始化地区选单
	 */
	$("select.region").each(function(k){
		var pid = $(this).attr('data-init');
		if (k == 0) {
			pid = pid ? pid : 3743;
		}
		if (pid != '') {
			getRegionByPid(pid, $(this));
		}
	});
	
	/**
	 * 选择地区，动态加载子级地区
	 */
	$('body').delegate("select.region", 'change', function(){
		var $obj = $(this);
		var i = $obj.index();
		var pid = $(this).val();
		
		// 到最后一级时，不再动态加载子级地区 
		if (i == $obj.parent().find('select.region').size() - 1) {
			return false;
		}
		
		// 清空后面地区选单元素的选项 
		$obj.parent().find('select:gt(' + i + ')').find("option:gt(0)").remove();
		
		//动态加载子级地区 
		getRegionByPid(pid, $obj.next('select.region'));
	});
	
});

/**
 * 获取url参数用到
 */
(function ($) {
    $.getQueryValue = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURIComponent(r[2]); return null;
    }
})(jQuery);

/**
 * 浏览器大小改变时调用
 */
$(window).resize(function() {
	bmChangeHeight();
});

/**
 * 自适应高度：根据搜索条件栏高度调整列表的高度
 */
function bmChangeHeight() {
	var $bmHeader = $(".bmViewHeader");
	var $bmBody = $(".bmViewBody");
	var height = $bmHeader.outerHeight(true);
	$bmBody.attr("style", "top:" + height + "px;");
}

/**
 * 根据父级 ID 获取子级地区
 */
function getRegionByPid(pid, $obj) {
	$.ajax({
		type:'post',
		url:BASE_URL+'/systemRegion/list',
		data:'key=' + pid,
		dataType:'json',
		timeout:60000,
		success:function(data){
			if (data.status == 0) {
				/* 若已加载，则无需重复加载 */
				if ($obj.find('option').size() > 1) {
					return false;
				}
				
				/* 加载地区列表 */
				var selected_id = parseInt($obj.attr('data-selected'));
				$(data.data).each(function() {
					if (selected_id > 0 && selected_id == this.id) {
						$obj.append('<option value="' + this.id + '" selected="selected">' + this.name + '</option>');
					} else {
						$obj.append('<option value="' + this.id + '">' + this.name + '</option>');
					}
				});
			}
		}
	});
}



/**
 * 载入导航轨迹
 */
function loadNavigation() {
	var currentNavId = $('#currentNavId').val();
	if(currentNavId) {
		$('.nav-three a').each(function(){
			if ($(this).attr('url') == currentNavId) {
				$(this).parent().addClass('active');
				$(this).parent().parent().show();
				$(this).parent().parent().parent().addClass('active');
			}
		});
	}
}


/**
 * 提示信息处理，可定时清除
 * 
 * @param string
 *            obj_id 提示信息对象 ID
 * @param string
 *            msg 提示信息内容
 * @param boolean
 *            clear 是否自动清除
 * @param integer
 *            delayTime 延迟时间
 */
function notice(obj_id, msg, clear, delayTime) {
	$("#" + obj_id).html(msg);
	if (clear) {
		if (! delayTime) {
			delayTime = 5000;
		}
		setTimeout(function() {
			$("#" + obj_id).empty();
	    }, delayTime);
	}
}

/**
 * 消息通知 - pnotify
 * 
 * @param string
 *            $msg 必填。提示信息
 * @param string
 *            $type 选填。提示类型，一共四种：warning、info、success、error
 * @param string
 *            $title 选填。提示标题
 */
function pnotify($msg, $type, $title) {
    new PNotify({
        title: $title ? $title : '提醒',
        text: $msg,
        type: $type ? $type : 'warning',
        styling: 'bootstrap3',
    });
}

/**
 * 更新 CKEDITOR 的状态，即值 适用版本：4.0以上
 */
function CKupdate() {
    for (instance in CKEDITOR.instances)
        CKEDITOR.instances[instance].updateElement();
}

/**
 * 判断 js 数组包是否包含某个元素，类似 PHP 的数组函数 in_array() 使用方法： var arr = ["a","b"];
 * alert(arr.in_array("a"));
 */
Array.prototype.S = String.fromCharCode(2);
Array.prototype.in_array = function(e) {
	var r = new RegExp(this.S + e + this.S);
	return (r.test(this.S + this.join(this.S) + this.S));
}

/**
 * ajaxError
 */
function ajaxError(XMLHttpRequest, textStatus, errorThrown) {
//	alert(($.parseJSON(XMLHttpRequest.responseText)).msg);
	notice('edit_notice', img_delete + " " + ($.parseJSON(XMLHttpRequest.responseText)).msg, true, 5000);
	return false;
}

/**fuelxu datagrid load*/
function loadDataGridContent(columns, formatFuncName) {
	
	var DataGridDataSource = function () {};
	
	DataGridDataSource.prototype = {
	    columns: function () {
	        return columns;
	    },
	    data: function (options, callback) {
	    	var param = parseParam();
	    	var url = param.url;
	    	delete param.url;
    		var data = {
    				rstype:"json",
    				pageIndex: options.pageIndex + 1,
    				pageSize: options.pageSize,
    		};
    		data = $.extend({}, options, data, param);
    		
    		$.ajax(url, {
    			data: data,
    			dataType: 'json',
    			async: true,
    			type: 'POST'
    		}).done(function (response) {
    			var data = response.data;
    			if (! data) {
    				return false;
    			}
    			var count=response.count;//设置data.total
    			// PAGING
    			var startIndex = options.pageIndex * options.pageSize;
    			var endIndex = startIndex + options.pageSize;
    			var end = (endIndex > count) ? count : endIndex;
    			var pages = Math.ceil(count / options.pageSize);
    			var page = options.pageIndex + 1;
    			var start = startIndex + 1;
//    			alert(formatFuncName);
    			if (formatFuncName) {
    				try {
    					var formatFunc = eval(formatFuncName);
    					if(typeof(formatFunc) == 'function') {
    						new formatFunc(data);
    					} else {
    						alert(formatFuncName + '不是一个function');
    					}
    				} catch (e) {
    					alert(formatFuncName + '未定义');
    				}
    			}
    			
    			callback({ data: data, start: start, end: end, count: count, pages: pages, page: page });
    			
    			//刷新操作按钮权限
    			refreshPermission();
    			
    			//动态调整查询条件栏高度
    			bmChangeHeight();
    			
    		}).fail(function (e) {
    			
    		});
	    }
	};
	
	
	if(verifyColumns() && verifyParam()) {
		$('.datagrid').datagrid({
			dataSource: new DataGridDataSource(),
			loadingHTML: '<span><img src="'+STATIC_URL+'/images/loading.gif"><i class="fa fa-info-sign text-muted" "></i>正在加载……</span>',
			itemsText: '条',
			itemText: '页',
			dataOptions: { pageIndex: 0 }	
		});
	}
	
	function verifyColumns() {
		if(!columns) {
			alert('columns未定义');
			return false;
		}
		if(!JSON.stringify(columns).betweenWith('[',']')) {
			alert('columns必须是json数组');
			return false;
		} 
		if(columns.length == 0) {
			alert('columns信息为空');
			return false;
		}
		for(var i=0; i<columns.length; i++) {
			if(!columns[i].property) {
				alert('columns第'+(i+1)+'个元素中的property属性不存在或为空');
				return false;
			}
		}		
		return true;
	}
	
	function verifyParam() {
		var form = $('#searchForm');
		if(!form) {
			alert('searchForm未定义');
			return false;
		}
		var actionUrl = form.attr('action');
		if(!actionUrl) {
			alert('actionUrl未定义');
			return false;
		}
		return true;
	}
	
	function parseParam() {
		var form = $('#searchForm');
		var actionUrl = form.attr('action');
		var paramInArray = form.serializeArray();
		var paramJson = {};
		for(var i in paramInArray) {
			if (typeof (paramJson[paramInArray[i].name]) == 'undefined') 
				paramJson[paramInArray[i].name] = paramInArray[i].value; 
			else 
				paramJson[paramInArray[i].name] += "," + paramInArray[i].value; 
		}
		return $.extend({}, paramJson, {url : actionUrl} );
	}
	
}
