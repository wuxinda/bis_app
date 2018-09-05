package com.bluemobi.controller.web.core;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
public class BaseController {
	/** 日志文件生成器 */
	protected transient final Log log = LogFactory.getLog(getClass());
	
    /**
     * 对象转json字符串
     * @param o
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    protected String getJsonString(Object o){  	
		try {
	    	ObjectMapper mapper = new ObjectMapper();	
			return mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
		} catch (IOException e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
		}
		return null;
	}
}
