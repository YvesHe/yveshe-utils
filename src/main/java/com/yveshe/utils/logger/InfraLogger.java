/**   
 * Filename:    InfraLog.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2017-9-4
 * Description:  
 *
 * Author       Yves He 
 */
package com.yveshe.utils.logger;

import org.apache.log4j.Logger;

/**
 * 日志类
 * 
 * (简单的封装Log4J)
 * 
 * 
 * @author Yves He
 * 
 */
public class InfraLogger {
    // 日志级别: 由低向高
    // logger.debug(message);
    // logger.info(message);
    // logger.warn(message);
    // logger.error(message);
    // logger.fatal(message);

    private final Logger logger;// Logger extends Category
                                // (Category中定义了一系列的日志方法.eg: debug...)

    public InfraLogger(Class<?> clazz) {
        logger = Logger.getLogger(clazz);

    }

    public void debug(CharSequence msg, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(formatMSG(msg, args));
        }
    }

    public void info(CharSequence msg, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(formatMSG(msg, args));
        }
    }

    public void warn(CharSequence msg, Object... args) {
        logger.warn(formatMSG(msg, args));
    }

    public void warn(CharSequence msg, Throwable t, Object... args) {
        logger.warn(formatMSG(msg, args), t);
    }

    public void error(CharSequence msg, Object... args) {
        logger.error(formatMSG(msg, args));
    }

    public void error(CharSequence msg, Throwable t, Object... args) {
        logger.error(formatMSG(msg, args), t);
    }

    public void fatal(CharSequence msg, Object... args) {
        logger.fatal(formatMSG(msg, args));
    }

    public void fatal(CharSequence msg, Throwable t, Object... args) {
        logger.fatal(formatMSG(msg, args), t);
    }

    /* 格式化消息 */
    private String formatMSG(CharSequence msg, Object... args) {
        return String.format(msg.toString(), args);
    }

    public boolean isDebug() {
        return logger.isDebugEnabled();
    }

}
