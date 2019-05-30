/**
 * Filename:    FileUtil.java
 * Copyright:   Copyright (c)2016
 * Company:     Yves
 * @version:    1.0
 * Create at:   2017-8-10
 * Description:
 *
 * Author       Yves He
 */
package com.yveshe.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件工具类
 *
 * @author YvesHe
 *
 */
public class FileUtils {

    public static void main(String[] args) {
        try {
            String readFile = readFile("testFile.txt");
            System.out.println(readFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(String filePath) throws IOException {
        return readFile(new File(filePath));
    }

    public static String readFile(File file) throws IOException {
        if (file == null) {
            return null;
        } else if (!file.exists()) {
            return null;
        }

        StringBuffer result = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line + "\n");
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public static byte[] readBinary(String filePath) throws IOException {
        return readBinary(new File(filePath));
    }

    /**
     * 从文件中读取字节数组
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] readBinary(File file) throws IOException {// 209.71G
        if (file == null) {
            return null;
        } else if (!file.exists()) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream((int) file.length());// 2147483647
        BufferedInputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[2048];
            int len = 0;
            while (-1 != (len = input.read(buffer, 0, buffer.length))) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } finally {
            if (input != null) {
                input.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
