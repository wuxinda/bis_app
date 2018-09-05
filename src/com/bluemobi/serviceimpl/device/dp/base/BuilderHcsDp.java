package com.bluemobi.serviceimpl.device.dp.base;

import com.bluemobi.serviceimpl.device.dp.base.AbstractBuilder;
import com.bluemobi.serviceimpl.device.dp.base.AbstractDeviceDp;

/**
 * 温湿度设备数据感知处理方式构建类
 * @author Riven
 *
 */
public class BuilderHcsDp extends AbstractBuilder{
	private AbstractHcsDp hcsDp; 
	public BuilderHcsDp(AbstractHcsDp hcsDp){
	    this.hcsDp = hcsDp;
	}
	
	/**
	 * 组合方式
	 */
	public void buildPart() {
		//连接
		this.hcsDp.connect();
		//收集
		this.hcsDp.collect();
		//分析
		this.hcsDp.analysis();
		//处理
		this.hcsDp.handle();
	}
	/**
	 * 具体构建对象
	 * @return
	 */
	public AbstractDeviceDp buildDeviceDp() {
		return this.hcsDp;
	}
	/**
	 * 模版组合方式
	 */
	public void defaultTemplate() {
		hcsDp.defaultTemplate();	
	}
}
