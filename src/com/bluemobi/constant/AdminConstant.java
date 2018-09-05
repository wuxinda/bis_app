package com.bluemobi.constant;


/**
 * admin模块相关常量
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-11-24 下午3:27:36 
 *
 */
public class AdminConstant {
    
    
    /**存放到【用户对象】的key */
    public static final String KEY_ADMIN_USER = "ADMIN_USER";
    
    /**存放到【权限map】的key */
    public static final String KEY_PERMISSION_MAP = "PERMISSION_MAP";
   
    /**存放到 【导航list】的key */
    public static final String KEY_NAVIGATION_LIST = "NAVIGATION_LIST";
    
    /**admin帐号id值－不可在数据库进行改变*/
    public static final Integer USER_ADMIN_ID = 1;
    
    
    /**后台用户类型--1、后台管理员 */
    public static final int USER_TYPE_ADMIN = 0;
    /**前端用户类型--2、领导者端用户 */
    public static final int USER_TYPE_LEADER = 1;
    /**前端用户类型--2、操作者端用户 */
    public static final int USER_TYPE_OPERATION= 2;
    

}
