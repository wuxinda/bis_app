package com.bluemobi.constant;
/**
 * token相关常量
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2016-6-24 下午3:16:56 
 *
 */
public class BMTokenConstant {
    
    /** 是否开启自动登录*/
    public static final boolean IS_AUTO_LOGIN = true;
    
    /** token过期时间  单位：秒 */
    public static final int TOKEN_EXPIRE_TIME = 60*24*3600;
    
    /** token名字，存储到客户端cookie中的名字*/
    public static final String TOKEN_NAME = "BMTOKENID";
    
    

}
