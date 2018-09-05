package com.bluemobi.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appcore.context.AppContext;
import com.appcore.server.impl.AbstractAppServer;
import com.bluemobi.ServerInit;

/**
 * App服务器
 * @author haojian
 * Mar 29, 2013 3:53:06 PM
 */
public class AppServer extends AbstractAppServer{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AppServer.class);
    
    private static AppServer appServer;
    
    private static Object lock = new Object();
    
    /**
     * 创建服务器
     * @author hao
     * Mar 19, 2014 9:55:52 AM
     * @return
     */
    public static AppServer getInstance(){
        synchronized(lock){
            if(appServer==null){
                appServer = new AppServer();
                LOGGER.info("创建一个AppServer对象【{}】", new Object[]{appServer} );
            }
        }
        return appServer;
    }

    @Override
    public void init() {
        super.init();
        
        //初始化服务器数据
        ServerInit serverInit = AppContext.getBean("serverInit");
        serverInit.init();
        
    }
    
    
    
    
}
