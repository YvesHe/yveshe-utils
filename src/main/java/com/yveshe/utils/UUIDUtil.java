/**
 * Copyright:   Copyright (c)2016
 * Company:     YvesHe
 * @version:    1.0
 * Create at:   2019年4月9日
 * Description:
 *
 * Author       YvesHe
 */
package com.yveshe.utils;

import java.util.UUID;

public class UUIDUtil {

    public static String genUUID() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println(genUUID());
    }

}
