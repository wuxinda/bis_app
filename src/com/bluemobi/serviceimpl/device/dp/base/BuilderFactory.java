package com.bluemobi.serviceimpl.device.dp.base;

import com.bluemobi.serviceimpl.device.dp.BayiHcsDp;
import com.bluemobi.serviceimpl.device.dp.ZKIIsDp;

/**
 * Builder具体对象创建工厂
 * @author Administrator
 *
 */
public class BuilderFactory {
    /**
     * 创建八益温湿度构建具体对象
     * @return
     */
    public static  AbstractHcsDp getBayiHcsDp(){
	return new BayiHcsDp();
    } 
    /**
     * 创建八益温湿度构建具体对象
     * @return
     */
    public static  AbstractIIsDp getZKIIsDp(){
	return new ZKIIsDp();
    } 
}
