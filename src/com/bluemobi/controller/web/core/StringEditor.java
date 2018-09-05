package com.bluemobi.controller.web.core;

import java.beans.PropertyEditorSupport;

import org.springframework.web.util.HtmlUtils;

/** 
* @ClassName: StringEditor 
* @Description: 处理表单中的html标签转义问题
* @author chenb
* @date 2015年3月6日 下午2:43:43  
*/
public class StringEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		return super.getAsText();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null) {
			text = HtmlUtils.htmlEscape(text);
			setValue(text);
		}
	}
}
