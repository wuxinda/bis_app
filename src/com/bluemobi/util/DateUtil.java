package com.bluemobi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

public class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);
	/**
	 * Des: 只包含年月日的时间格式
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Des: 包含年月日带时、分、秒的时间格式，时间为24小时制的
	 */
	public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Des: 包含年月日带时、分的时间格式，时间为24小时制的
	 */
	public static final String DEFAULT_TIME_NO_SCE_FORMAT = "yyyy-MM-dd HH:mm";

	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Des: 只包含时、分、秒的时间格式，时间为24小时制的
	 */
	public static final String TIME_NO_YMD_FORMAT = "HH:mm:ss";

	// Excel报表中需要日期格式
	public static final String REPORT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * 转化成Excel报表中日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String rDate2String(Date date) {
		DateFormat format = new SimpleDateFormat(REPORT_DATE_FORMAT, Locale.US);
		return format.format(date);
	}

	/**
	 * Description 将date换化为yyyy-MM-dd格式的字符串
	 */
	public static String formatToDate(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return formater.format(date);
	}

	/**
	 * Description 将date换化为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @create time: 2007-3-2 下午02:46:35
	 * @version 1.0
	 * @modified records:
	 */
	public static String formatToTimestamp(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		return formater.format(date);
	}

	/**
	 * Description 将date换化为HH:mm:ss格式的字符串
	 * @modified records:
	 */
	public static String formatToTime(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(TIME_NO_YMD_FORMAT);
		return formater.format(date);
	}

	/**
	 * 
	 * <p>
	 * Description 将date换化为用户指定的格式
	 * @modified records:
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return "";
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		return formater.format(date);
	}

	/**
	 * 
	 * <p>
	 * Description 将指定格式的字符串转化为日期类型
	 * @modified records:
	 */
	public static Date parseToDate(String date, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		try {
			return formater.parse(date);
		} catch (ParseException e) {
			log.error("", e);
			return null;
		}
	}

	public static Date stringToDate(String date) {
		if (StringUtils.isEmpty(date))
			return null;
		return parseToDate(date, DEFAULT_DATE_FORMAT);
	}

	public static Date stringToDateTime(String date) {
		if (StringUtils.isEmpty(date))
			return null;
		return parseToDate(date, DEFAULT_DATE_TIME_FORMAT);
	}

	/**
	 * Description 将传入的日期加一天
	 */
	public static Date nextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * Description 将传入的日期减一天
	 * @param Date 被减的时间对象
	 */
	public static Date forwardDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	/**
	 * Description 将传入的日期加指定的天数
	 */
	public static Date addDay(Date date, int size) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, size);
		return calendar.getTime();
	}

	/**
	 * Description 将传入的字符串格式的日期增加指定的天，返回减后的字符串格式的日期 字符串日期格式必需为:yyyy-MM-dd
	 */
	public static String addDay(String date, int size) {
		Date d = parseToDate(date, DEFAULT_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_YEAR, size);
		d = calendar.getTime();
		return formatToDate(d);

	}

	/**
	 * Description 将传入的字符串格式的日期增加指定的天，返回减后的字符串格式的日期 字符串日期格式为用户指定
	 */
	public static String addDay(String date, int size, String pattern) {
		Date d = parseToDate(date, pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_YEAR, size);
		d = calendar.getTime();
		return formatDate(d, pattern);
	}

	/**
	 * Description 将传入的日期加指定的天数
	 */
	public static Date reduceDay(Date date, int size) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -size);
		return calendar.getTime();
	}

	/**
	 * 
	 * <p>
	 * Description 将传入的字符串格式的日期减去指定的天，返回减后的字符串格式的日期 字符串日期格式必需为:yyyy-MM-dd
	 */
	public static String reduceDay(String date, int size) {
		Date d = parseToDate(date, DEFAULT_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_YEAR, -size);
		return formatToDate(calendar.getTime());
	}

	/**
	 * 
	 * <p>
	 * Description 将传入的字符串格式的日期减一天，返回减后的字符串格式的日期 字符串日期格式为自己定义
	 */
	public static String reduceDay(String date, int size, String pattern) {
		Date d = parseToDate(date, pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_YEAR, -size);
		return formatDate(calendar.getTime(), pattern);
	}

	/**
	 * 
	 * <p>
	 * Description 将传入的日期加指定的月数
	 */
	public static Date addMonth(Date date, int size) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, size);
		return calendar.getTime();
	}

	/**
	 * 
	 * <p>
	 * Description 将传入的日期减去指定的月数
	 */
	public static Date reduceMonth(Date date, int size) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -size);
		return calendar.getTime();
	}

	/**
	 * 
	 * <p>
	 * Description 将传入的字符串日期格式减去指定的月数
	 */
	public static String reduceMonth(String date, int size) {
		Date d = parseToDate(date, DEFAULT_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, -size);
		d = calendar.getTime();
		return formatToDate(d);
	}

	/**
	 * Description 将传入的字符串格式的日期减去指定的月数，返回减后的字符串格式的日期 字符串日期格式为自己定义
	 */
	public static String reduceMonth(String date, int size, String pattern) {
		Date d = parseToDate(date, pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, -size);
		d = calendar.getTime();
		return formatDate(d, pattern);
	}

	/**
	 * Description 将传入的字符串格式的日期增加指定的月数
	 */
	public static String addMonth(String date, int size) {
		Date d = parseToDate(date, DEFAULT_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, size);
		d = calendar.getTime();
		return formatToDate(d);
	}

	/**
	 * Description 将传入的字符串格式的日期增加指定的月数，返回减后的字符串格式的日期 字符串日期格式为自己定义
	 * @modified records:
	 */
	public static String addMonth(String date, int size, String pattern) {
		Date d = parseToDate(date, pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, size);
		d = calendar.getTime();
		return formatDate(d, pattern);
	}

	/* ###############################返回给定日期############################## */
	/** 获得本月的第一天的日期 */
	public static Date getCurrMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal)) + "-01";
		return convertStrToDate(s, "yyyy-MM-dd");
	}

	/** 获得本月的最后一天的日期 */
	public static Date getCurrMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal)) + "-" + getDays(cal);
		return convertStrToDate(s, "yyyy-MM-dd");
	}

	/** 获得给定日历的年 */
	public static int getYear(Calendar cal) {
		return cal.get(Calendar.YEAR);
	}

	/** 获得给定日期的年 */
	public static int getYear(Date date) {
		return convertDateToCal(date).get(Calendar.YEAR);
	}

	/** 获得给定日期字符串对应的年 */
	public static int getYear(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.YEAR));
	}

	/** 获得给定日历的月 */
	public static int getMonth(Calendar cal) {
		return (cal.get(Calendar.MONTH) + 1);
	}

	/** 获得给定日期的月 */
	public static int getMonth(Date date) {
		return (convertDateToCal(date).get(Calendar.MONTH) + 1);
	}

	/** 获得给定日期字符串对应的月 */
	public static int getMonth(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.MONTH) + 1);
	}

	/** 获得给定日期的当天 */
	public static int getDay(Calendar cal) {
		return (cal.get(Calendar.DAY_OF_MONTH));
	}

	/** 获得给定日期的当天 */
	public static int getDay(Date date) {
		return (convertDateToCal(date).get(Calendar.DAY_OF_MONTH));
	}

	/** 获得给定日期的当天 */
	public static int getDay(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.DAY_OF_MONTH));
	}

	/** 获得给定日期当月的天数 */
	public static int getDays(Calendar cal) {
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/** 获得给定日期当月的天数 */
	public static int getDays(Date date) {
		return (convertDateToCal(date).getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/** 获得给定日期当月的天数 */
	public static int getDays(String date_str, String type) {
		return (convertStrToCal(date_str, type)
				.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/** 获得当前日期 */
	public static Date getCurrDate() {
		Calendar cal = Calendar.getInstance();
		String ymdhms = formatToTimestamp(cal.getTime());
		return stringToDateTime(ymdhms);

	}

	/** 获得当前年 */
	public static int getCurrYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/** 获得当前月 */
	public static int getCurrMonth() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.MONTH) + 1);
	}

	/** 获得当前天 */
	public static int getCurrDay() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.DAY_OF_MONTH));
	}

	/* ###############################字符格式和日期格式的转换############################## */
	/** 日期转换字符(动态格式转换) */
	public static String convertDateToStr(Date date, String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		return dateformat.format(date);
	}

	/** 字符转换日期(动态格式转换) */
	public static Date convertStrToDate(String date_str, String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		try {
			return dateformat.parse(date_str);
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	/** 字符转换日历(动态格式转换) */
	public static Calendar convertStrToCal(String date_str, String type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(convertStrToDate(date_str, type));
		return cal;
	}

	/** 日期转日历* */
	public static Calendar convertDateToCal(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 根据传入的日期取得当前的年
	 * 
	 * @author
	 * @param date
	 * @return int
	 */
	@SuppressWarnings("static-access")
	public static int getYearByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(cal.YEAR);
	}

	/* ###############################日期字符格式验证############################## */

	/**
	 * 判断日期格式是否正确
	 */
	public static boolean isDate(String date_str, String type) {
		SimpleDateFormat dateformat = new SimpleDateFormat(type);
		try {
			dateformat.setLenient(false);
			dateformat.parse(date_str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Date getCurDateAddDate(int amount, int type) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}

	public Date getDateAddDate(Date date, int type, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(date);
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 传入两个日期，返回时间差（date2>date1）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDateSubDate(Date date1, Date date2) {
		return (date2.getTime() - date1.getTime()) / 1000;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static java.sql.Date getCurrentDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 计算两个日子相隔的时间
	 * 
	 * @param startday
	 * @param endday
	 * @param function
	 *            (1000*60*60*24 天数/1 毫秒数/1000 秒数/1000*60 分钟数/1000*60*60 小时数)
	 * @return
	 */
	public static int getIntervalTime(Date startday, Date endday, int function) {
		// 确保startday在endday之前
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		// 分别得到两个时间的毫秒数
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		// 根据毫秒数计算间隔时间
		return (int) (ei / function);

	}

	public static long getTime() {
		return new Date().getTime();
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDaysBetween(Date startDate, Date endDate) {
		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		int days = calendarEndDate.get(Calendar.DAY_OF_YEAR)
				- calendarStartDate.get(Calendar.DAY_OF_YEAR);
		int y2 = calendarEndDate.get(Calendar.YEAR);
		while (calendarStartDate.get(Calendar.YEAR) < y2) {
			days += calendarStartDate.getActualMaximum(Calendar.DAY_OF_YEAR);
			calendarStartDate.add(Calendar.YEAR, 1);
		}

		return days;
	}

	/**
	 * 取得两个日期相差的周年数
	 * 
	 * @param startDate
	 *            最大的日期值
	 * @param endDate
	 *            最小的日期值
	 * @return
	 */
	public static int getYearsBetween(Date startDate, Date endDate) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(startDate);
		int year1 = cal1.get(Calendar.YEAR);
		int month1 = cal1.get(Calendar.MONTH) + 1;
		int date1 = cal1.get(Calendar.DATE);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(endDate);
		int year2 = cal2.get(Calendar.YEAR);
		int month2 = cal2.get(Calendar.MONTH) + 1;
		int date2 = cal2.get(Calendar.DATE);

		if (year1 < year2)
			return -1;

		int betweenYear = Math.abs(year1 - year2);
		if (betweenYear == 12 || betweenYear == 2) {
			if (month1 > month2)
				betweenYear++;
			else if (month1 == month2) {
				if (date1 > date2)
					betweenYear++;
				else if (date1 < date2)
					betweenYear--;
			} else {
				betweenYear--;
			}
		}

		return betweenYear;

	}

	/**
	 * 通过起飞日期取得前后七天的日期列表 用于查询列表页显示
	 * 
	 * @param deptime
	 *            格式如yyyy-MM-dd
	 */
	public static List<String> getSevenDateList(String deptime) {
		String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		List<String> dateList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		Date depDate = null;
		try {
			depDate = dateFormat.parse(deptime);
		} catch (ParseException e) {
			log.error("", e);
		}

		if (DateUtil.getDaysBetween(cal.getTime(), depDate) >= 3) {
			for (int i = 0; i <= 6; i++) {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(depDate);
				cal1.add(Calendar.DAY_OF_MONTH, -3);
				cal1.add(Calendar.DAY_OF_MONTH, i);
				int num = cal1.get(Calendar.DAY_OF_WEEK) - 1;
				dateList.add(dateFormat.format(cal1.getTime()) + "_"
						+ weeks[num]);
			}
		} else {
			for (int i = 0; i <= 6; i++) {
				Calendar cal1 = Calendar.getInstance();
				cal1.add(Calendar.DAY_OF_MONTH, i);
				int num = cal1.get(Calendar.DAY_OF_WEEK) - 1;
				dateList.add(dateFormat.format(cal1.getTime()) + "_"
						+ weeks[num]);
			}
		}
		return dateList;
	}

	/**
	 * 取得当前时间是星期几（返回：日：0，一：1，……，六：6）
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 时间比较
	 * 
	 * @param startTime
	 * @param endTime
	 * @param nowTime
	 * @return
	 */
	/*public static boolean timeCompare(String startTime, String endTime,
			String nowTime) {
		if (StringUtil.isEmpty(startTime) || StringUtil.isEmpty(endTime))
			return true;
		String[] st = startTime.split(":");
		String[] et = endTime.split(":");
		String[] nt = nowTime.split(":");
		if (st.length > 1 && et.length > 1 && nt.length > 1) {
			int stH = StringUtil.string2Int(st[0]);
			int stM = StringUtil.string2Int(st[1]);
			int etH = StringUtil.string2Int(et[0]);
			int etM = StringUtil.string2Int(et[1]);
			int ntH = StringUtil.string2Int(nt[0]);
			int ntM = StringUtil.string2Int(nt[1]);

			if (ntH > stH && ntH < etH) {
				return true;
			} else if (ntH == stH) {
				return ntM >= stM;
			} else if (ntH == etH) {
				return ntM <= etM;
			} else
				return false;
		}
		return true;
	}*/

	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyy-mm-dd hh:mm:ss
	 * 
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}

	/**
	 * 返回当前日期时间时间戳<br>
	 * 
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getTimeStamp() {
		String returnStr = null;
		Date date = new Date();
		long time = date.getTime();
		returnStr = time + "";
		return returnStr;
	}

	/**
	 * 两个日期相差多少秒
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getTimeDelta(Date date1, Date date2) {
		long timeDelta = (date1.getTime() - date2.getTime()) / 1000;// 单位是秒
		int secondsDelta = timeDelta > 0 ? (int) timeDelta : (int) Math
				.abs(timeDelta);
		return secondsDelta;
	}
	/**
	 * 
	 * addOneSecond:当前时间加秒  得到data类型数据
	 *
	 * @author bayi01
	 * @param date
	 * @return
	 * @since JDK 1.6
	 */
	public static Date addOneSecond(Date date,int second) {    
	    Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(date);    
	    calendar.add(Calendar.SECOND, second);    
	    return calendar.getTime();    
	}    
	
	
	/**
	 * 
	 * addOneSecond:当前时间加秒 得到string类型数据
	 *
	 * @author bayi01
	 * @param date
	 * @return
	 * @since JDK 1.6
	 */
	public static String addOneSecond(String date,int second) {    
	    Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(DateUtil.parseToDate(date, DEFAULT_DATE_TIME_FORMAT));    
	    calendar.add(Calendar.SECOND, second);    
	    
	    return DateUtil.formatToTimestamp(calendar.getTime()); 
	}    
}
