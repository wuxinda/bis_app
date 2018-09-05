package com.bluemobi.serviceimpl.message.push;

/**
 * 推送服务抽象类
 * 
 * @author Riven
 * 
 */
public abstract class AbstractPush {
    /**
     * 数据验证
     */
    public abstract void check();

    /**
     * 具体推送
     */
    public abstract void push();

    /**
     * 默认模版方法，设备感知数据按此方法默认执行处理
     * 
     * @return
     */
    public final AbstractPush defaultTemplate() {
	check();
	push();
	return this;// 返回当前对象
    }
}
