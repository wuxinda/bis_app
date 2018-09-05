/**
 * 框架js的工具类,包含对于原生js的扩展和所需要的工具方法,不依赖与任何第三方插件
 */

/** 数字金额转为大写 */
function digit_uppercase(n) {
	var fraction = [ '角', '分' ];
	var digit = [ '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' ];
	var unit = [ [ '元', '万', '亿' ], [ '', '拾', '佰', '仟' ] ];
	var head = n < 0 ? '欠' : '';
	n = Math.abs(n);
	var s = '';
	for (var i = 0; i < fraction.length; i++) {
		s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i])
				.replace(/零./, '');
	}
	s = s || '整';
	n = Math.floor(n);
	for (var i = 0; i < unit[0].length && n > 0; i++) {
		var p = '';
		for (var j = 0; j < unit[1].length && n > 0; j++) {
			p = digit[n % 10] + unit[1][j] + p;
			n = Math.floor(n / 10);
		}
		s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
	}
	return head
			+ s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/,
					'零元整');
}


/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 例子： (new
 * Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
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
}

/** 时间日期pattern枚举 */
var PATTERN_ENUM = {'time' : 'hh:mm:ss', 'date' : 'yyyy-MM-dd', 'datetime' : 'yyyy-MM-dd hh:mm:ss'};

/**
 * 时间日期类型内容转换方法 pattern: 需要的显示格式,可以从PATTERN_ENUM中取值，也可以自己指定格式 millionSeconds:
 * 毫秒数
 */
function dateConverter(millionSeconds, pattern) {
	if (!millionSeconds || !pattern) {
		return '';
	}
	return new Date(millionSeconds).Format(pattern);
}

/** 比较给定的2个日期大小 */
function compareDatStr(begin, end) {
	var begin = getDateFromStr(begin);
	var end = getDateFromStr(end);
	if(begin.getTime() > end.getTime()) {
		return false;
	}
	return true;
}

// js startWith() endWith() 方法
String.prototype.endWith = function(str) {
	if(str == null || str == "" || this.length == 0 || str.length > this.length)
	  return false;
	if(this.substring(this.length - str.length) == str)
	  return true;
	else
	  return false;
}

String.prototype.startWith = function(str) {
	if(str == null || str == "" || this.length == 0 || str.length > this.length)
	  return false;
	if(this.substr(0,str.length) == str)
	  return true;
	else
	  return false;
}

String.prototype.betweenWith = function(startStr, endStr) {
	if(this.startWith(startStr) && this.endWith(endStr)) 
	  return true;
	else 
	  return false;
}