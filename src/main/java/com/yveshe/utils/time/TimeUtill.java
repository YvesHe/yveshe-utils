/**   
 * Filename:    TimeUtill.java   
 * Copyright:   Copyright (c)2016  
 * Company:     Yves  
 * @version:    1.0    
 * Create at:   2017-8-10
 * Description:  
 *
 * Author       Yves He 
 */
package com.yveshe.utils.time;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 理解 Timestamp的使用 和String.format方法 *
 * 
 * @author Yves He
 * 
 */
public class TimeUtill {

    public static void clearToMinute(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    public static void clearToDay(Calendar calendar) {
        clearToMinute(calendar);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
    }

    public static final Timestamp currentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static final String timeToStr(Timestamp timestamp) {
        return String.format("%tF %tT", timestamp, timestamp);
    }

    public static final String dateToStr(Date date) {
        return String.format("%tF", date);
    }

}
