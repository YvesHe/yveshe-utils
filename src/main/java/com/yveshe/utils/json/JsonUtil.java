/**   
 * Filename:    JSONUtil.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2017-8-10
 * Description:  
 *
 * Author       Yves He 
 */
package com.yveshe.utils.json;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Json转换
 * 
 * @author Yves He
 * 
 */
public class JsonUtil {

    /**
     * T
     * 
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * String
     * 
     * @param obj
     * @return
     */
    public static <T> String toJson(T obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * List<T>
     * 
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> List<T> fromJsonToArray(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

}
