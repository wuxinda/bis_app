package com.bluemobi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bluemobi.server.AppServer;
/**
 * 启动服务器
 * @author haojian
 * Mar 6, 2012 2:38:47 PM
 */
public class ServerStart {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger("ServerStartAndStopLog");
    
    private ServerStart(){
        
    }
    
    /**
     * 启动app服务器
     * @author haojian
     * @date 2014-9-25 下午12:00:04 
     * @return void
     */
    public static void main(String[] args) {
        
        long begin = System.currentTimeMillis();
        
        AppServer appServer = AppServer.getInstance();
        
        appServer.start();
        
        long end = System.currentTimeMillis();
        
        LOGGER.info("【App】服务器启动成功！启动耗时：【{}】秒", new Object[]{(end-begin)/1000d} );    
        
    }
    
    

}
