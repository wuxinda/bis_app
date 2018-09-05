package com.bluemobi.controller.web.core;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/** 
* @ClassName: CustomerBindingDataType 
* @Description: 自定义全局属性类型转
* @author chenb
* @date 2015年3月6日 下午2:44:20  
*/
public class CustomerBindingDataType implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(String.class, new StringEditor());
	}

}
