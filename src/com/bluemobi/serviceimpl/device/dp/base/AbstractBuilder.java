package com.bluemobi.serviceimpl.device.dp.base;

/**
 * 库房设备感知数据处理各部件组装类
 * 
 * @author Riven
 *
 */
public abstract class AbstractBuilder {
	/**
	 * 组合方式
	 */
	public abstract void buildPart();

	/**
	 * 具体构建对象
	 * @return
	 */
	public abstract AbstractDeviceDp buildDeviceDp();
	/**
	 * 默认模版方法，设备感知数据按此方法默认执行处理
	 * @return
	 */
	public abstract void defaultTemplate() ;
}
