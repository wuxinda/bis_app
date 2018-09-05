$.fn.radio = function(options) {
	var _this = $(this);
	var defaults = {
		fp : false
	}
	var opts = $.extend(defaults, options);
	_this.find("label").click(
			function() {
				if ($(this).attr("class").indexOf("cp") > 0) {
					_this.find(".cp-address").stop().fadeIn('fast');
				} else {
					_this.find(".cp-address").stop().fadeOut('fast');
				}
				$(this).addClass("ra-checked").siblings("label").removeClass(
						'ra-checked');
//				$(this).find("input").attr("selected", "selected");
//				$(this).siblings().find("input").removeAttr("selected");
				$(this).find("input").attr("checked", "checked");
				$(this).siblings().find("input").removeAttr("checked");
			})
}
$.fn.check = function() {
	var _this = $(this);
	_this.find("label").click(function() {
		if ($(this).hasClass("ce-checked")) {
			$(this).removeClass("ce-checked");
			$(this).find("input").removeAttr("checked");
			$(this).find("input").prop("checked",false);
		} else {
			$(this).addClass("ce-checked");
			$(this).find("input").attr("checked", "checked");
			$(this).find("input").prop("checked",true);
		}
		return false;
	})
}
// 日期选择=控件
$.fn.date = function() {
	var _this = $(this);
	var id = _this.attr('id');
	var start = {
		elem : '#' + id,
		format : 'YYYY-MM-DD',
		//min : laydate.now(), // 设定最小日期为当前日期
		max : '2099-06-16 23:59:59', // 最大日期
		istime : false,
		istoday : false,
		choose : function(datas) {
			end.min = datas; // 开始日选好后，重置结束日的最小日期
			end.start = datas; // 将结束日的初始值设定为开始日
		}
	};
	var start = {
		elem : '#' + id,
		format : 'YYYY-MM-DD',
		//min : laydate.now(), // 设定最小日期为当前日期
		max : '2099-06-16 23:59:59', // 最大日期
		istime : false,
		istoday : false,
		choose : function(datas) {
			end.min = datas; // 开始日选好后，重置结束日的最小日期
			end.start = datas; // 将结束日的初始值设定为开始日
			// 验证日期
			if($('#'+id).val()!=null){
				$('#'+id).addClass('validate-success');
				$('#'+id).removeClass('validate-empty');
				$('#'+id).attr('data-validate-result',1);
				$('#'+id).next().children('.aValidate-result').removeClass('aValidate-result-show');
			}
		}
	};
	var end = {
		elem : '#' + id,
		format : 'YYYY-MM-DD hh:mm:ss',
		//min : laydate.now(),
		max : '2099-06-16 23:59:59',
		istime : true,
		istoday : false,
		choose : function(datas) {
			start.max = datas; // 结束日选好后，重置开始日的最大日期
		}
	};
	_this.click(function() {
		laydate(start);
	})
}
// 发送短信验证
$.fn.sentmsg = function(options) {
	var defaults = {
		phone_mun : 0,
		second : 5
	}
	var opts = $.extend(defaults, options);
	$(this).click(function() {
		var _this = $(this);
		var timer, text_temp = _this.text(), cont = opts.second - 1;
		_this.addClass('disabled').removeClass('button-g');
		_this.text(opts.second + "秒后可重新发送");
		// 1,发送成功
		timer = setInterval(function() {
			if (cont >= 0) {
				_this.text(cont + "秒后可重新发送");
				cont--;
			} else {
				_this.addClass('button-g').removeClass('disabled');
				_this.text(text_temp);
				clearInterval(timer);
			}
		}, 1000);
	})
}

// 弹窗口
jQuery.alert = function(title, html) {
	var shadow_Html = '<div class="shadow"></div>';
	var win_html = '<div class="alert">'
			+ '<div class="title"><span>关闭</span></div>'
			+ '<div class="con"></div>' + '</div>';
	var oTop, oLeft, tit_temp = '';
	$("body").prepend(shadow_Html);
	$("body").prepend(win_html);
	$(".shadow").css({
		"height" : $(document).outerHeight() + 'px'
	});
	if ($(".alert").outerHeight() - $(window).outerHeight() < 0) {
		$(".alert").css("position", "absolute");
		oTop = $(document).scrollTop();
	} else {
		oTop = Math
				.floor(($(window).outerHeight() - $(".alert").outerHeight()) / 2);
	}
	oLeft = Math.floor(($(window).outerWidth() - $(".alert").outerWidth()) / 2);
	$(".alert").css({
		"top" : oTop + "px",
		"left" : oLeft + "px"
	});
	$(".alert .con").html(html);
	tit_temp = title;
	$(".alert>.title").prepend(tit_temp);
	$(".alert").stop().fadeIn('fast');
	$(".alert .title>span").click(function() {
		$(".alert").stop().fadeOut('fast', function() {
			$(".shadow").stop().fadeOut('fast', function() {
				$(".shadow").remove();
			});
			$(".alert").remove();
		});
	})
}

//弹窗口
jQuery.confirm = function(title, html ,id,type) {
	var shadow_Html = '<div class="shadow"></div>';
	var win_html = '<div class="alert">'
			+ '<div class="title"></div>'
			+ '<div class="con"></div>' 
			+ '<div class="btnList"></div>'
			+ '</div>';
	var oTop, oLeft, tit_temp = '';
	$(".shadow").remove();
	$(".alert").remove();
	$("body").prepend(shadow_Html);
	$("body").prepend(win_html);
	
	$(".shadow").css({
		"height" : $(document).outerHeight() + 'px'
	});
	if ($(".alert").outerHeight() - $(window).outerHeight() < 0) {
		$(".alert").css("position", "fixed");
		oTop = $(document).scrollTop();
	} else {
		oTop = Math
				.floor(($(window).outerHeight() - $(".alert").outerHeight()) / 2);
	}
	oLeft = Math.floor(($(window).outerWidth() - $(".alert").outerWidth()) / 2);
	$(".alert").css({
		"top" : 70 + "px",
		"left" : oLeft + "px"
	});
	
	$(".alert .con").html(HtmlUtil.htmlDecode(html));
	$('.alert .con').css({'width':'auto','padding':'2%'});
	tit_temp = title;
	$(".alert>.title").prepend(tit_temp);
	$(".alert").stop().fadeIn('fast');
	var btn;
	if(type==4){
		btn = '<div class="btn_list" style="margin: 20px auto;overflow:hidden;width:60%;">'
			+'<a class="btn sure" id="yes" style="float:left;width:45%;display:block;cursor: pointer;height:30px;line-height:30px;background:#005434;color:#fff;text-align:center;border-radius:5px;font-size:14px;">确定</a>'
			+ '</div>';
	}else{
		btn = '<div class="btn_list" style="margin: 20px auto;overflow:hidden;width:60%;">'
			+'<a class="btn yes" id="yes" style="float:left;width:45%;display:block;cursor: pointer;height:30px;line-height:30px;background:#005434;color:#fff;text-align:center;border-radius:5px;font-size:14px;">我已阅读并同意</a>'
			+'<a class="btn no" id="no" style="float:left;width:45%;margin-left:10%;cursor: pointer;display:block;height:30px;line-height:30px;background:#005434;color:#fff;text-align:center;border-radius:5px;font-size:14px;">不同意</a>'
			+ '</div>';
	}
	
	$('.btnList').html(btn);
	
	$('.btn_list a').click(function(){
		if($(this).hasClass('yes')){
			$(".shadow").stop().fadeOut('fast', function() {
				$(".shadow").remove();
			});
			$(".alert").remove();
			$("#"+id).find(".checkbox-a").addClass('ce-checked');
			$("#"+id).find(".checkbox-a input").attr("checked", "checked");
		}else if($(this).hasClass('sure')){
			$(".shadow").stop().fadeOut('fast', function() {
				$(".shadow").remove();
			});
			$(".alert").remove();
		}else{
			$(".shadow").stop().fadeOut('fast', function() {
				$(".shadow").remove();
			});
			$(".alert").remove();
			$("#"+id).find(".checkbox-a").removeClass("ce-checked");
			$("#"+id).find(".checkbox-a input").removeAttr("checked");
		}
	});
	
};

function showTerms(id, type, title, content){
	if(!content){
		$.ajax({
		    url: config.getServiceTermsInfo,
		    async : false ,//同步执行
		    type: 'GET',
		    dataType: 'json',
		    data: {'type':type},
		    success: function (data){
		    	if(data.status){
		    		content = data.data;
		    	}
		    }
		});
	}
	if(!!content){
		$.confirm(title,content, id,type);
	} else {
    	alert("请求条款数据失败，请刷新重试!");
	}
};

// 显示遮罩
function showShadow() {
	var shadow = '<div class="shadow"></div>';
	$("body").prepend(shadow);
	$(".shadow").css("height", $(document).outerHeight() + "px");
	$(".shadow").stop().fadeIn('fast');
}
// 隐藏遮罩
function hideShadow() {
	$(".shadow").stop().fadeOut('fast', function() {
		$(".shadow").remove();
	})
}
// 星星
$.fn.stars = function(options) {
	var defaults = {
		load : false,
		num : 1,
		callback : ''
	}
	var opts = $.extend(defaults, options);
	var _this = $(this);
	if (opts.load) {
		for ( var i = 0; i < opts.num; i++) {
			_this.find("i").eq(i).addClass("active");
		}
	} else {
		var a_index = _this.find('.active').length - 1;
		var star = _this.find('i');
		star.hover(function() {
			addActive($(this));
		}, function() {
			addActive(star.eq(a_index))
		}).click(function() {
			a_index = $(this).index();
			addActive($(this));
			if (opts.callback) {
				opts.callback();
			}
			_this.find("input[type='hidden']").val(a_index + 1);
		})
	}
	function addActive(obj) {
		star.removeClass('active');
		var nIndex = obj.index();
		for ( var i = 0; i <= nIndex; i++) {
			star.eq(i).addClass("active");
		}
	}
}

$.fn.addressSelect = function(id) {
	//加载省数据
	Ajax.custom({
		url : config.loadBasisData,
		data : {
			type : 1
		},
		type : 'GET'
	}, function(data) {
		var optionHtml = '';
		$.each(data.data, function(v, k) {
			optionHtml += '<option value="' + k.code + '">' + k.name + '</option>';
		})
		var firSelect = $("#"+id).find('select').eq(0);
		var secSelect = $("#"+id).find('select').eq(1);
		var thdSelect = $("#"+id).find('select').eq(2);
		//填充省
		firSelect.append(optionHtml);
	    var firseleVal = firSelect.val();
	    //省值改变事件
	    firSelect.change(function() {
			//var val = secSelect.find("option:selected").attr("value");
			var firseleVal = firSelect.val();
			//填充市县
			Ajax.custom({
				url : config.loadBasisData,
				data : {
					type : 2,
					codeFK:firseleVal
				},
				type : 'GET'
			}, function(data) {
				var optionHtml = '';
				$.each(data.data, function(v, k) {
					optionHtml += '<option value="' + k.code + '">' + k.name + '</option>';
					var secSelect = $("#"+id).find('select').eq(1);
				})
				secSelect.empty().append(optionHtml).fadeIn('fast');
				var secseleVal = secSelect.val();
			    //填充县
				Ajax.custom({
					url : config.loadBasisData,
					data : {
						type : 3,
						codeFK:secseleVal
					},
					type : 'GET'
				}, function(data) {
					var optionHtml = '';
					$.each(data.data, function(v, k) {
						optionHtml += '<option value="' + k.code + '">' + k.name + '</option>';
						var secSelect = $("#"+id).find('select').eq(1);
					})		
					thdSelect.empty().append(optionHtml).fadeIn('fast');
				});	
				
			});
		})
	    //填充市
		Ajax.custom({
			url : config.loadBasisData,
			data : {
				type : 2,
				codeFK:firseleVal
			},
			type : 'GET'
		}, function(data) {
			var optionHtml = '';
			$.each(data.data, function(v, k) {
				optionHtml += '<option value="' + k.code + '">' + k.name + '</option>';
				var secSelect = $("#"+id).find('select').eq(1);
			})
			secSelect.append(optionHtml);
			var secseleVal = secSelect.val();
		    //填充县
			Ajax.custom({
				url : config.loadBasisData,
				data : {
					type : 3,
					codeFK:secseleVal
				},
				type : 'GET'
			}, function(data) {
				var optionHtml = '';
				$.each(data.data, function(v, k) {
					optionHtml += '<option value="' + k.code + '">' + k.name + '</option>';
					var secSelect = $("#"+id).find('select').eq(1);
				})		
				thdSelect.append(optionHtml);
			});	
			 //市值改变事件
			secSelect.change(function() {
				//var val = secSelect.find("option:selected").attr("value");
				var secseleVal = secSelect.val();
				//填充县
				Ajax.custom({
					url : config.loadBasisData,
					data : {
						type : 3,
						codeFK:secseleVal
					},
					type : 'GET'
				}, function(data) {
					var optionHtml = '';
					$.each(data.data, function(v, k) {
						optionHtml += '<option value="' + k.code + '">' + k.name + '</option>';
						var secSelect = $("#"+id).find('select').eq(1);
					})
					thdSelect.empty().append(optionHtml).fadeIn('fast');					
				});
			})
		});
        
	});
}
// 重写分页插件 pageNav.go方法 isPageClick：是否分页点击加载 0、否 1是
pageNav.go = function(p, pn, isPageClick) {
	$("#pageNav").html(this.nav(p, pn));
	if (isPageClick != 0) {
		this.fn(this.p, this.pn);
	}
};
// 时间格式化
var getTimeYhm = function(data) {
	return data.substr(0, 4) + "-" + data.substr(4, 2) + "-"
			+ data.substr(6, 2);
};
var getTimeYhmHms = function(data) {
	return data.substr(0, 4) + "-" + data.substr(4, 2) + "-"
			+ data.substr(6, 2) + " " + data.substr(8, 2) + ":"
			+ data.substr(10, 2) + ":" + data.substr(12, 2);
};

// 对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

var HtmlUtil = {
    /* 1.用浏览器内部转换器实现html转码 */
    htmlEncode:function (html){
        // 1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement ("div");
        //2.然后将要转换的字符串设置为这个元素的innerText(ie支持)或者textContent(火狐，google支持)
        (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
        //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
        var output = temp.innerHTML;
        temp = null;
        return output;
    },
    /*2.用浏览器内部转换器实现html解码*/
    htmlDecode:function (text){
        //1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement("div");
        //2.然后将要转换的字符串设置为这个元素的innerHTML(ie，火狐，google都支持)
        temp.innerHTML = text;
        //3.最后返回这个元素的innerText(ie支持)或者textContent(火狐，google支持)，即得到经过HTML解码的字符串了。
        var output = temp.innerText || temp.textContent;
        temp = null;
        return output;
    }
	};