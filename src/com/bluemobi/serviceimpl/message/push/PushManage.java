package com.bluemobi.serviceimpl.message.push;



/**
 * 库房设备感知数据处理管理类
 * 
 * @author Riven
 *
 */
public class PushManage {
	//具体构建对象
	private AbstractBuilder builderPush = null;
	public PushManage(AbstractBuilder builder){
		this.builderPush = builder;
	}

	/**
	 * 特定方法
	 */
	public void startBuildPart() { 
	    builderPush.buildPart(); 
	  } 
	/**
	 *模板方法
	 */   
	public void startBuilddefault() { 
	    builderPush.defaultTemplate();
	} 
	public static void main(String[] args) {
		PushManage manage = new PushManage(new BuilderPush(new AlarmPush())); 
		manage.startBuildPart();
		manage.startBuilddefault();

	}

}
