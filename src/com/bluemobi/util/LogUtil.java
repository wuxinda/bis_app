package com.bluemobi.util;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.appcore.context.AppContext;
import com.appcore.util.JsonUtil;
import com.appcore.util.SessionManager;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.log.LogUserAction;
import com.bluemobi.service.log.LogUserActionService;

/**
 * 日志帮助类
 * @author Riven
 *
 */
public class LogUtil {
    
    /**
     * 异步纪录用户行为日志－暂时只纪录后台日志 手机端和web端日志还需要做登录session 方可接上
     * @param request
     * @param logType 1、后台日志   2、手机端日志   3、web端日志
     */
    public static void logUserAction(HttpServletRequest request, int logType){
        
        AdminUser adminUser = SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
        String ip = request.getRemoteHost();
        String servletPath = request.getServletPath();
        String param = JsonUtil.getJsonString(request.getParameterMap());
        //获取context对象
        ServletContext context = request.getSession().getServletContext();
        //获取需要纪录的用户行为url的集合
        @SuppressWarnings("unchecked")
		Map<String,String> systemUrlMap = (Map<String, String>) context.getAttribute("systemUrlMap");
        //不需要纪录的url不予处理
        if(null!=systemUrlMap&&null!=systemUrlMap.get(servletPath)){
            //创建日志对象
            LogUserAction log = new LogUserAction();
            
            log.setLogType(logType);
            
            int userId = 0;
            if(adminUser!=null){
                userId = adminUser.getUserId();
            }
            log.setUserid(userId);
            log.setUrl(servletPath);
            log.setParam(param);
            log.setIp(ip);
            log.setLogTime(new Date());
            
            LogUserActionService logUserActionService = AppContext.getBean("logUserActionService");
            //异步插入数据到数据库
            logUserActionService.asyncInsert(log);
        }    
    }
    
    
    

}
