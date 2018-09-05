package com.bluemobi.serviceimpl.device.dp.base;


/**
 * 灯光设备数据感知处理方式构建类
 * @author Riven
 *
 */
public class BuilderIIsDp extends AbstractBuilder{
	private AbstractDeviceDp iIsDp ; 
	public BuilderIIsDp(AbstractDeviceDp iIsDp){
	    this.iIsDp = iIsDp; 
	}
	
	/**
	 * 组合方式
	 */
	public void buildPart() {
		//连接
		this.iIsDp.connect();
		//收集
		this.iIsDp.collect();
		//分析
		this.iIsDp.analysis();
		//处理
		this.iIsDp.handle();
	}
	/**
	 * 具体构建对象
	 * @return
	 */
	public AbstractDeviceDp buildDeviceDp() {
		return this.iIsDp;
	}
	/**
	 * 模版组合方式
	 */
	public void defaultTemplate() {
		iIsDp.defaultTemplate();	
	}
}
