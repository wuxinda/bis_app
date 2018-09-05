package com.bluemobi.serviceimpl.device.dp;

import com.bluemobi.serviceimpl.device.dp.base.AbstractBuilder;
import com.bluemobi.serviceimpl.device.dp.base.BuilderFactory;
import com.bluemobi.serviceimpl.device.dp.base.BuilderHcsDp;

/**
 * 库房设备感知数据处理管理类
 * 
 * @author Riven
 *
 */
public class DeviceDpManage {
	//具体构建对象
	private AbstractBuilder builderDp = null;
	public DeviceDpManage(AbstractBuilder builder){
		this.builderDp = builder;
	}

	/**
	 * 执行设备感知数据逻辑-特定方法
	 */
	public void startBuildPart() { 
		builderDp.buildPart(); 
	  } 
	/**
	 * 执行设备感知数据逻辑-模板方法
	 */   
	public void startBuilddefault() { 
		builderDp.defaultTemplate();
	} 
	public static void main(String[] args) {
		DeviceDpManage manage = new DeviceDpManage(new BuilderHcsDp(BuilderFactory.getBayiHcsDp())); 
		manage.startBuildPart();
		manage.startBuilddefault();

	}

}
