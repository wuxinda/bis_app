package com.bluemobi.conf;

import java.util.Properties;

import com.appcore.conf.AbstractPropertiesConfig;

/**
 * 图片服务器配置
 * 图片或文件通过sftp跨服务器传递的时候，需使用此配置，并调用SftpUtil中的静态方法来传递
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2016-3-10 下午2:52:51
 * 
 */
public class ImageServerConfig extends AbstractPropertiesConfig {

    public static final long serialVersionUID = 1L;

    private static Properties p = getProperties("imageServerConfig.properties");

    /** 图片服务器ip */
    public static String HOST = p.getProperty("HOST");
    /** ssh端口 */
    public static Short PORT = Short.parseShort(p.getProperty("PORT"));
    /** 服务器用户名 */
    public static String USER_NAME = p.getProperty("USER_NAME");
    /** 服务器密码 */
    public static String PASSWORD = p.getProperty("PASSWORD");
    /** 存放图片的路径，绝对地址 */
    public static String BASE_IMAGE_PATH = p.getProperty("BASE_IMAGE_PATH");

}
