package com.bluemobi.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean工具类
 * @ClassName BeanUtils
 * @author liuyt
 * @date 2015-11-2 下午2:17:06
 * @version
 */
public class BeanUtils {

    /**
     * 将给定的bean转换为Map, 排除属性值为null的属性
     * @author liuyt
     * @date 2015-11-2 下午2:16:41
     * @param obj
     * @return
     * @throws Exception
     * @version
     */
    public static Map<String, Object> beanToMap(Object obj) throws Exception {
        if (obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            if(value != null) {
                map.put(key, value);
            }
        }

        return map;
    }

}
