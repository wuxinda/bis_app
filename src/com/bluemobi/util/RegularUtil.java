package com.bluemobi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式util
 * 
 * @author zhangzheng
 * @date 2016-1-20
 * 
 */
public class RegularUtil {

    /**
     * 匹配字符串，regex中的*号可以是【0到多个】字符
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午5:10:30
     * @param regex
     *            正则表达式
     * @param str
     *            需要校验的字符串
     * @return
     */
    public static boolean match(String regex, String str) {
        regex = "^\\" + regex;
        regex = regex.replace("*", "(\\b.*|\\B.*|$)");
        return extracted(str, regex);
    }

    /**
     * 匹配字符串，regex中的*号必须是【1到多个】字符
     * 
     * @auther zhangzheng
     * @date 2016-3-4 下午5:10:30
     * @param regex
     *            正则表达式
     * @param str
     *            需要校验的字符串
     * @return
     */
    public static boolean matchAtLeastOneCharacter(String regex, String str) {
        regex = "^\\" + regex;
        regex = regex.replace("*", ".+");
        return extracted(str, regex);
    }

    /**
     * 匹配数字
     * 
     * @auther zhangz
     * @date 2016-5-4 下午1:35:17
     * @param str
     * @return
     */
    public static boolean matchNumber(String str) {
        String regex = "^[0-9]+$";
        return extracted(str, regex);
    }

    /**
     * 匹配英文和数字
     * 
     * @auther zhangz
     * @date 2016-5-4 下午1:35:17
     * @param str
     * @return
     */
    public static boolean matchEnglishAndNumber(String str) {
        String regex = "^[A-Za-z0-9]+$";
        return extracted(str, regex);
    }

    /**
     * 匹配中文、英文和数字
     * 
     * @auther zhangz
     * @date 2016-5-4 下午1:39:10
     * @param str
     * @return
     */
    public static boolean matchChineseAndEnglishAndNumber(String str) {
        String regex = "^[\u4E00-\u9FA5A-Za-z0-9]+$";
        return extracted(str, regex);
    }

    /**
     * 校验手机号，包括以下号码
     * <em>移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、182、187、188</em>
     * <em>联通：130、131、132、152、155、156、185、186 </em>
     * <em>电信：133、153、180、189、（1349卫通）</em>
     * 
     * @auther zhangz
     * @date 2016-5-10 下午3:30:44
     * @param mobiles
     * @return true:是手机号;false:不是手机号;
     */
    public static boolean isMobileNO(String mobiles) {
        // String regex =
        // "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$";
        String regex = "^(1[0-9][0-9])\\d{8}$";
        return extracted(mobiles, regex);
    }

    private static boolean extracted(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String regEx = "/abc/*";
        String str = "/abc/dd";
        boolean b = RegularUtil.match(regEx, str);
        // boolean b = RegularUtil.matcherAtLeastOneCharacter(regEx, str);
        System.out.println(b);
    }

}
