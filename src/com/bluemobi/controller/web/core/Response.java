package com.bluemobi.controller.web.core;

/** 
* @ClassName: Response 
* @Description: 返回结果集封装接口
* @author chenb
* @date 2015年3月6日 下午2:03:08 
* @param <T> 
*/
public interface Response<T> {
    public static final String SUCCESS = "OK";
    public static final String FAILED = "FAILED";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";

    public static final int DEFAULT_PAGE = 0;
    public static final int PAGE_SIZE = 10;

    public String getCode();

    public String getMessage();

    public T getResult();
}
