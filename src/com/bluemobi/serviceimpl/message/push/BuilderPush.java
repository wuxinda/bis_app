package com.bluemobi.serviceimpl.message.push;

/**
 * 推送处理方式构建类
 * 
 * @author Riven
 * 
 */
public class BuilderPush extends AbstractBuilder {
    private AbstractPush alarmPush;

    public BuilderPush(AbstractPush alarmPush) {
	this.alarmPush = alarmPush;
    }

    /**
     * 组合方式
     */
    public void buildPart() {
	// 检查
	this.alarmPush.check();
	// 推送
	this.alarmPush.push();

    }

    /**
     * 模版组合方式
     */
    public void defaultTemplate() {
	alarmPush.defaultTemplate();
    }


    /* 
     *具体构建
     * 
     */
    @Override
    public AbstractPush buildPush() {
	return this.alarmPush;
    }
}
