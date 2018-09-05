//template config、common method
(function () {
    template.openTag = "<!--[";
    template.closeTag = "]-->";

    // 模板帮助方法，绝对化图片地址
    template.helper('$absImg', function (content, defaultValue) {
        if (!content) {
            return '../content/images/common/d300x300-1.png';
        }
        if (content && content.indexOf('http://') == 0) {
            return content;
        }
        return config.imageServer + content;
    });

    template.helper('$replace', function (content) {
        if (!content) {
            return '';
        }
        return content.replace('0', '电子书').replace('1', '实体书').replace('2', '课件').replace('3', '直播').replace(/,/g, "、");
    });

    //分隔字符串并取第一个
    template.helper('$replaceFirst', function (content) {
        if (!content) {
            return '';
        }
        var str = content.split(',');
        var id = '';
        if (str != null && str.length > 0) {
            id = str[0];
        }
        return id;
    });
    
    // 模板帮助方法，判断某元素在数组中是否存在
    template.helper('$iscunArr', function (arr, str) {
    	if(arr == null || arr.length == 0){
    		return false;
    	}
    	for(var i in arr){
    		if(arr[i][1] == str){
    			return true;
    		}
    	}
    	return false;
    });
    
    // 模板帮助方法，判断某字符串中是否包含子字符
    template.helper('$iscunStr', function (strs, str) {
    	if(strs == null || strs.length == 0){
    		return false;
    	}
    	if(strs.indexOf(str) != -1){
    		return true;
    	}
    	return false;
    });

    // 模板帮助方法，转换时间戳成字符串
    template.helper('$formatDate', function (content, type, defaultValue) {
        if (content) {
            if (content.length == 10)
                content = content + '000';
            return Tools.formatDate(content, type);
        } else {
            return defaultValue || '--';
        }
    });

    // 模板帮助方法，转换时间戳成字符串
    template.helper('$formatDateDian', function (content) {
        var str = '';
        if (content) {
            if (parseInt(content / 3600) > 0) {
                if (parseInt(content / 3600) < 10) {
                    str += '0' + parseInt(content / 3600) + ':';
                } else {
                    str += parseInt(content / 3600) + ':';
                }
                content = content % 3600;
            }else{
            	str += '00:';
            }
            if (parseInt(content / 60) > 0) {
                if (parseInt(content / 60) < 10) {
                    str += '0' + parseInt(content / 60) + ':';
                } else {
                    str += parseInt(content / 60) + ':';
                }
                content = content % 60;
            } else {
                str += '00:';
            }
            if (content < 10) {
                str += '0' + content;
            } else {
                str += content;
            }
            return str;
        } else {
            return str = '00:00:00';
        }
    });
    // 模板帮助方法，验证是否已登录
    template.helper('$isLogin', function () {
        return !!config.getId();
    });
    //获取用户信息
    template.helper('$getuserid', function () {
    	return config.getId();
    });
    //模板帮助方法，编码url参数
    template.helper('$encodeUrl', function (content) {
        return encodeURIComponent(content);
    });
    //模板帮助方法，格式化货币
    template.helper('$formatCurrency1', function (content, defaultValue, unit) {
        if (!content) {
            return defaultValue || '--';
        }

        var mod = content.toString().length % 3;
        var sup = '';
        if (mod == 1) {
            sup = '00';
        } else if (mod == 2) {
            sup = '0';
        }
        content = sup + content;
        content = content.replace(/(\d{3})/g, '$1,');
        content = content.substring(0, content.length - 1);
        if (sup.length > 0) {
            content = content.replace(sup, '');
        }

        return content + unit || '';
    });
    //模板帮助方法，\r\n替换换行
    template.helper('$convertRN', function (content) {
        if (!content) {
            return '--';
        }
        return content.replace(/\r\n/gi, '<br/>');
    });
    //模板帮助方法，根据序列值添加样式名
    template.helper('$addClassByIdx', function (i, v, className) {
        if (i == v) {
            return className || '';
        }
    });
    //模板帮助方法，格式化货币
    template.helper('$formatCurrency', function (content, i) {
        if (!content) {
            return '--';
        }

        //1200.55->1200<span class="c-red f-s">.55</span>
        var p, f, idx = content.indexOf('.');
        if (idx > 0) {
            p = content.substring(0, idx);
            f = content.substring(idx);
        } else {
            p = content;
            f = '.00';
        }
        return p + '<span class="f-s">' + f + '</span>';
    });
    //模板帮助方法，截取内容长度添加省略号
    template.helper('$ellipsis', function (content, length) {
        var v = content.replace(/[^\x00-\xff]/g, '__').length;
        if (v / 2 > length) {
            return content.substring(0, length) + '...';
        }
        return content;
    });
    //返回当前时间的long值
    template.helper('$nowDateLong', function () {
    	return (new Date()).getTime();
    });
})();

//debug
var log = function (m) {
    if (typeof console != 'undefined') {
        console.log(m);
    }
};
//jquery 扩展方法
//控制只允许输入数字
$.fn.numberLimit = function (maxval,callback) {
    this.bind("keypress", function (e) {
        var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE
        return code >= 48 && code <= 57;
    });
    this.bind("blur", function () {
        if (this.value.lastIndexOf(".") == (this.value.length - 1)) {
            this.value = this.value.substr(0, this.value.length - 1);
        } else if (isNaN(this.value)) {
            this.value = "";
        }
        //最大值限制
        this.value = maxVal(this.value);
        if($.isFunction(callback)){
            callback(this.value);
        }
    });
    this.bind("paste", function () {
        var s = clipboardData.getData('text');
        if (!/\D/.test(s));
        value = s.replace(/^0*/, '');
        return false;
    });
    this.bind("dragenter", function () {
        return false;
    });
    this.bind("keyup", function () {
        if (/(^0+)/.test(this.value)) {
            this.value = this.value.replace(/^0*/, '');
        }
        //最大值限制
        this.value = maxVal(this.value);
        if($.isFunction(callback)){
            callback(this.value);
        }
    });

    function maxVal(val){
        return parseInt(val) > maxval ? maxval:val;
    }
};

// 扩展Date 方法 start
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
// 扩展Date 方法 end

// 扩展String的方法 start
String.prototype.isSpaces = function () {
    for (var i = 0; i < this.length; i += 1) {
        var ch = this.charAt(i);
        if (ch != ' ' && ch != "\n" && ch != "\t" && ch != "\r") {
            return false;
        }
    }
    return true;
};

String.prototype.isValidMail = function () {
    return (new RegExp(
        /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)
        .test(this));
};

String.prototype.isPhone = function () {
    return (new RegExp(/^1\d{10}?$/).test(this));
};

String.prototype.isEmpty = function () {
    return (/^\s*$/.test(this));
};

String.prototype.isValidPwd = function () {
    return (new RegExp(/^([_]|[a-zA-Z0-9]){8,16}$/).test(this));
};

String.prototype.isPostCode = function () {
    return (new RegExp(/^\d{6}?$/).test(this));
};

String.prototype.getQueryValue = function (key) {
    var q = this, keyValuePairs = new Array();

    if (q.length > 1) {
        var idx = q.indexOf('?');
        q = q.substring(idx + 1, q.length);
    } else {
        q = null;
    }

    if (q) {
        for (var i = 0; i < q.split("&").length; i++) {
            keyValuePairs[i] = q.split("&")[i];
        }
    }

    for (var j = 0; j < keyValuePairs.length; j++) {
        if (keyValuePairs[j].split("=")[0] == key) {
            // 这里需要解码，url传递中文时location.href获取的是编码后的值
            // 但FireFox下的url编码有问题
            return decodeURI(keyValuePairs[j].split("=")[1]);

        }
    }
    return '';
};
// 扩展String的方法 end

// from
var from = location.href.getQueryValue('from');
/**
 * 将form中的值转换为键值对
 *
 * @param form
 *            表单对象
 */
var formJson = function (form) {
    var o = {};
    var a = $(form).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

// 基于jQuery 二次封装 ajax
var Ajax = {
    /**
     * 基于ajax的查询
     *
     * @param data
     *            封装请求url，请求数据，请求类型
     * @param callback
     *            ajax请求成功后执行的回调方法
     * @param callbackDone
     *            ajax请求成功后最后执行的方法
     */
    pageRequest: function (data, callback, callbackDone) {
        var renderFor = data.renderFor || 'data-list-tmpl', renderEle = data.renderEle || '#data-list';
        if(data!=undefined&&data.data!=undefined){
            data.data.r = Math.random();
        }
        $.ajax({
            url: data.url,
            data: data.data,
            type: data.type || 'GET',
            timeout: 7000
            //cache: false
        }).then(function (response, textStatus, jqXHR) {
        	//返回的结果为json数组，需要转换为对象 chenb on 2015-6-11
        	var response = eval('(' + response + ')');
        	response = response.data;
            if (response.code != 'OK') {
                return;
            }

            if ($('#' + renderFor).length) {
                var result = template.render(renderFor, {
                    'list': response.result || []
                });
                $(renderEle).html(result);
            }
            if ($.isFunction(callback)) {
                callback(response);
            }
        }).done(function (xhr, b) {
            if ($.isFunction(callbackDone)) {
                callbackDone();
            }
        }).fail(function (jqXHR, textStatus, errorThrown) {

        });

    },
    /**
     * 基于ajax的查询
     *
     * @param data
     *            封装请求url，请求数据，请求类型
     * @param callback
     *            ajax请求成功后执行的回调方法
     * @param callbackDone
     *            ajax请求成功后最后执行的方法
     */
    queryRecord: function (data, callback, callbackDone, callbackError) {
        var renderFor = data.renderFor || 'detail-data-tmpl', renderEle = data.renderEle || '#detail-data';
        if(data!=undefined&&data.data!=undefined){
            data.data.r = Math.random();
        }
        $.ajax({
            url: data.url,
            data: data.data,
            type: data.type || 'GET',
            timeout: 7000
        }).then(function (response) {
            if (response.code != 'OK') {
                log("request error!");
                return;
            }

            if ($('#' + renderFor).length && response.result) {
                var result = template.render(renderFor, response.result);
                $(renderEle).html(result);
            }
            if ($.isFunction(callback)) {
                callback(response);
            }
        }).done(function () {
            if ($.isFunction(callbackDone)) {
                callbackDone();
            }
        }).fail(function (jqXHR, textStatus, errorThrown) {
            if (textStatus == 'timeout') {
                $(renderEle).addClass('error').text(config.tips.timeout);
            } else {
                $(renderEle).addClass('error').text(config.tips.server);
            }
            if ($.isFunction(callbackError)) {
                callbackError();
            }
        });
    },
    /**
     * 基于ajax的表单提交
     *
     * @param data
     *            传入的参数
     * @param callback
     *            ajax请求成功后执行的回调方法
     * @param callbackDone
     *            ajax请求成功后最后执行的方法
     */
    submitForm: function (data, callback, callbackDone) {
        var formData;

        var isForm = !!data.data.length;
        if (isForm) {
            formData = data.data.serializeArray();
            data.data.find('input[type="submit"]').attr('disabled', true);
        } else {
            formData = data.data;
        }

        $.ajax({
            url: data.url,
            data: formData,
            type: data.type || 'POST',
            timeout: 7000
        }).then(function (response) {
            if (!response) {
                log('request error!');
            }
            if (from) {
                location.href = decodeURIComponent(from);
                return;
            }
            if (data.backUrl) {
                location.href = data.backUrl;
                return;
            }
            if ($.isFunction(callback)) {
                callback(response);
            }
        }).done(function () {
            if (isForm) {
                data.data.find('input[type="submit"]').removeAttr('disabled');
            }
            if ($.isFunction(callbackDone)) {
                callbackDone();
            }
        }).fail(function (jqXHR, textStatus, errorThrown) {
            if (isForm) {
                data.data.find('input[type="submit"]').removeAttr('disabled');
                log("request error!");
            }
        });
    },
    /**
     * 基于ajax的表单文件提交(注意此方法依赖于from.js插件)
     *
     * @param data
     *            传入的参数 fromId:表单ID
     * @param callback
     *            ajax请求成功后执行的回调方法
     * @param callbackDone
     *            ajax请求成功后最后执行的方法
     */
    submitFileForm: function (data, callback, callbackDone) {
        if (!$.isFunction(callback))
          return;
		var options = {
	            success: callback
	        };	
		$("#"+data.data.fromId).attr("action",data.data.url);
		$("#"+data.data.fromId).ajaxForm(options);
    },
    /**
     * 自定义请求
     * @param data 请求数据
     * @param callback ，回调
     * @param callbackError 请求错误回调
     */
    custom: function (data, callback, callbackError) {
       if(data!=undefined&&data.data!=undefined){
          data.data.r = Math.random();
       }
        $.ajax({
            url: data.url,
            data: data.data,
            type: data.type || 'GET',
            timeout: 15000,
            cache: false,
            async: data.async || true
        }).then(function (response, textStatus, jqXHR) {
        	//返回的结果为json数组，需要转换为对象 chenb on 2015-6-11
        	var response = eval('(' + response + ')');
            if (response.code == 'FAILED') {
                log('custom request FAILED!FAILED URL:' + data.url);
            }
            if (typeof callback == 'function') {
                callback(response);
            }
        }, function (jqXHR, textStatus, errorThrown) {
            log(textStatus + ':' + data.url);
            if (typeof callbackError == 'function') {
                callbackError(jqXHR, textStatus, errorThrown);
            }
        }).done(function () {
        });
    }
};

// 本地存储(html5)
var Storage = (function () {
    return {
        AUTH: 'SHSUN_AUTH',
        ACCOUNT: 'SHSUN_ACCOUNT',
        REMEMBER: 'SHSUN_REMEMBER',
        get: function (key, isSession) {
            if (!Storage.isLocalStorage()) {
                return;
            }
            var value = Storage.getStorage(isSession).getItem(key);
            if (value) {
                return JSON.parse(value);
            } else {
                return undefined;
            }
        },
        set: function (key, value, isSession) {
            if (!Storage.isLocalStorage()) {
                return;
            }
            value = JSON.stringify(value);
            Storage.getStorage(isSession).setItem(key, value);
        },
        remove: function (key, isSession) {
            if (!Storage.isLocalStorage()) {
                return;
            }
            Storage.getStorage(isSession).removeItem(key);
        },
        getStorage: function (isSession) {
            return isSession ? sessionStorage : localStorage;
        },
        isLocalStorage: function () {
            try {
                if (!window.localStorage) {
                    log('不支持本地存储');
                    return false;
                }
                localStorage.setItem('isLocalStorage', 'abc');
                localStorage.removeItem('isLocalStorage');
                return true;
            } catch (e) {
                log('本地存储已关闭');
                return false;
            }
        }
    };
})();

// 常用工具
var Tools = (function () {
    var preventDefault, panel, delay, panel0, toastPanel;
    return {
        formatDate: function (content, type) {
            var pattern = "yyyy-MM-dd hh:mm";
            // 2012年1月23日
            // 18:00
            // 2013.1.23
            // 2013/01/23
            switch (type) {
                case 1:
                    pattern = "yyyy年M月d日";
                    break;
                case 2:
                    pattern = "hh:mm";
                    break;
                case 3:
                    pattern = "yyyy.M.d";
                    break;
                case 4:
                    pattern = "yyyy-MM-dd hh:mm:ss";
                    break;
                case 5:
                    pattern = "yyyy年MM月";
                    break;
                case 6:
                    pattern = "yyyy-MM-dd";
                    break;
                case 7:
                    pattern = "yyyy/MM/dd hh:mm:ss";
                    break;
                case 8:
                    pattern = "yyyy/MM/dd";
                    break;
                default:
                    pattern = !!type ? type : pattern;
                    break;
            }
            if (isNaN(content) || content == null) {
                return content;
            } else if (typeof (content) == 'object') {
                var y = dd.getFullYear(), m = dd.getMonth() + 1, d = dd
                    .getDate();
                if (m < 10) {
                    m = '0' + m;
                }
                var yearMonthDay = y + "-" + m + "-" + d;
                var parts = yearMonthDay.match(/(\d+)/g);
                var date = new Date(parts[0], parts[1] - 1, parts[2]);
                return date.format(pattern);
            } else {
                var date = new Date(parseInt(content));
                return date.format(pattern);
            }
        },
        // 获取窗口尺寸，包括滚动条
        getWindow: function () {
            return {
                width: window.innerWidth,
                height: window.innerHeight
            };
        },
        // 获取文档尺寸，不包括滚动条但是高度是文档的高度
        getDocument: function () {
            var doc = document.documentElement || document.body;
            return {
                width: doc.clientWidth,
                height: doc.clientHeight
            };
        },
        // 获取屏幕尺寸
        getScreen: function () {
            return {
                width: screen.width,
                height: screen.height
            };
        },
        // 显示、禁用滚动条
        showOrHideScrollBar: function (isShow) {
            preventDefault = preventDefault || function (e) {
                e.preventDefault();
            };
            (document.documentElement || document.body).style.overflow = isShow ? 'auto'
                : 'hidden';
            // 手机浏览器中滚动条禁用取消默认touchmove事件
            if (isShow) {
                // 注意这里remove的事件必须和add的是同一个
                document
                    .removeEventListener('touchmove', preventDefault, false);
            } else {
                document.addEventListener('touchmove', preventDefault, false);
            }
        }
    };
})();

// cookie
var Cookie = {
    get: function (sname) {
        var sre = "(?:;)?" + sname + "=([^;]*);?";
        var ore = new RegExp(sre);
        if (ore.test(document.cookie)) {
            try {
                return unescape(RegExp["$1"]); // decodeURIComponent(RegExp["$1"]);
            } catch (e) {
                return null;
            }
        } else {
            return null;
        }
    },

    set: function (c_name, value, expires) {
        expires = expires || this.getExpDate(7, 0, 0);
        if (typeof expires == 'number') {
            expires = this.getExpDate(expires, 0, 0);
        }
        document.cookie = c_name + "=" + escape(value)
            + ((expires == null) ? "" : ";expires=" + expires) + "; path=/";
    },

    remove: function (key) {
        Cookie.set(key, '', -1);
    },

    getExpDate: function (e, t, n) {
        var r = new Date;
        if (typeof e == "number" && typeof t == "number"
            && typeof t == "number")
            return r.setDate(r.getDate() + parseInt(e)), r.setHours(r
                .getHours()
                + parseInt(t)), r.setMinutes(r.getMinutes() + parseInt(n)),
                r.toGMTString()
    }
};