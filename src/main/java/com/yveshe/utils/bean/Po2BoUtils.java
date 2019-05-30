/**   
 * Filename:    Po2BoUtils.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2017-9-4
 * Description:  
 *
 * Author       Yves He 
 */
package com.yveshe.utils.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.yveshe.utils.logger.InfraLogger;

/**
 * Po转Bo(本质是Po与Bo之间的互转)
 * 
 * @author Yves He
 * 
 */
public class Po2BoUtils {

    private static InfraLogger logger = new InfraLogger(Po2BoUtils.class);

    public static <P, B> B getBo(P po, Class<B> clazz) {
        B bo = null;
        if (po != null) {
            try {
                bo = clazz.newInstance();
                // 内省获取bean信息
                BeanInfo poBeanInfo = Introspector.getBeanInfo(po.getClass());
                // 存放bean的读写方法以及属性类型
                PropertyDescriptor[] poPropertys = poBeanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : poPropertys) {
                    String attribute = property.getName();
                    // get方法
                    Method poReadMethod = property.getReadMethod();
                    Method boWriterMethod = getWriteMethod(attribute, bo);
                    if (poReadMethod != null && boWriterMethod != null) {
                        // 获取get值
                        Object value = poReadMethod.invoke(po, new Object[] {});
                        boWriterMethod.invoke(bo, new Object[] { value });
                    }
                }
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
        return bo;
    }

    public static <P, B> List<B> getBoList(List<P> poList, Class<B> clazz) {
        List<B> list = new ArrayList<B>();
        if (poList != null && !poList.isEmpty()) {
            // 存放bean的读写方法以及属性类型
            for (P po : poList) {
                B bo = getBo(po, clazz);
                list.add(bo);
            }
        }
        return list;
    }

    private static <T> Method getWriteMethod(String methodName, T t) throws IntrospectionException {
        // 内省获取bean的信息
        BeanInfo poBeanInfo = Introspector.getBeanInfo(t.getClass());
        // 存放bean的读写方法以及属性类型
        PropertyDescriptor[] poPropertys = poBeanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : poPropertys) {
            String attribute = property.getName();
            if (attribute.equals(methodName)) {
                Method method = property.getWriteMethod();
                return method;
            }
        }
        return null;
    }
}
