package com.bluemobi.serviceimpl.device.dp.base;

/**
 * 抽象库房设备数据感知类
 * 
 * @author Riven
 *
 */
public abstract class AbstractDeviceDp {
	/**
	 * 设备连接
	 */
	public abstract void connect();

	/**
	 * 设备感知数据收集
	 */
	public abstract void collect();

	/**
	 * 设备感知数据分析，形成报警纪录
	 */
	public abstract void analysis();

	/**
	 * 设备感知数据分析后，操控设备，进行智能处置
	 */
	public abstract void handle();

	/**
	 * 默认模版方法，设备感知数据按此方法默认执行处理
	 * @return
	 */
	public final AbstractDeviceDp defaultTemplate() {
		connect();
		collect();
		analysis();
		handle();
		return this;// 返回当前对象，即默认组装方式的设备感知数据处理类
	}
}
