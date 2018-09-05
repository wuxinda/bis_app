package com.bluemobi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 抽象的API控制器
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-15 上午10:08:40 
 *
 */
public abstract class AbstractAPIController extends AbstractController{
    /**
     * 获取userId
     * @author haojian
     * @date 2015-10-15 上午10:09:30 
     * @return
     * @return int
     */
    /*@Override
    public int getUserid() {

        CustBase custBase = this.getCustBase();
        
        int baseid = 0;
        if(custBase!=null){
            baseid = custBase.getId();
        }

        return baseid;
    }*/
    
    
    /**
     * 获取用户名
     * @author haojian
     * @date 2015-12-2 上午9:48:47 
     * @param request
     * @return
     * @return String
     */
    /*public String getUsername(){
        CustBase custBase = this.getCustBase();
        if(custBase!=null){
            return custBase.getName();
        }else{
            return String.valueOf(getUserid());
        }
        
    }*/
    
    /**
     * 获取普通用户
     * @author haojian
     * @date 2015-12-2 上午9:48:53 
     * @param request
     * @return
     * @return CasUser
     */
   /* public CustBase getCustBase(){
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CustBase custBase = SessionManager.getAttribute(request, BaseInfoConstant.KEY_CUST_BASE);
        return custBase;
    
    }*/
    
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    
	@Override
	public int getUserId() {
		return 0;
	}

	@Override
	public String getUserName() {
		return null;
	}
}
