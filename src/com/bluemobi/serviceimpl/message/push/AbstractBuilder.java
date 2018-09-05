package com.bluemobi.serviceimpl.message.push;

/**
 * 推送各部件组装类
 * 
 * @author Riven
 * 
 */
public abstract class AbstractBuilder {
    /**
     * 自定义组合方式
     */
    public abstract void buildPart();

    /**
     * 具体构建对象
     * 
     * @return
     */
    public abstract AbstractPush buildPush();

    /**
     * 默认模版方法
     * 
     * @return
     */
    public abstract void defaultTemplate();

   
}
