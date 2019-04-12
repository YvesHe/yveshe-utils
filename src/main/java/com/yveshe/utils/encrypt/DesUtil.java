package com.yveshe.utils.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 标准加密工具 <br>
 * Data encryption standard
 *
 * @author YvesHe
 *
 */
public class DesUtil {

    private static final String DES = "DES";
    private static final String STRING_ENCODE = "UTF-8";
    private static final String SALT = "2b2dc4cc-8cd6-458f-885e-1a2151bfabe4"; /* 加密盐 */
    private static final String DEFAULT_KEY = "yveshe_des_key";

    public static String encrypt(String data) {
        try {
            return encrypt(data, DEFAULT_KEY);
        } catch (Exception e) {
            return data;
        }
    }

    public static String decrypt(String data) {
        try {
            return decrypt(data, DEFAULT_KEY);
        } catch (Exception e) {
            return data;
        }
    }

    /**
     * 加密
     *
     * @param data
     *            待加密字符串
     * @param key
     *            密钥(长度应大于8字节)
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        if (data == null) {
            return null;
        }
        if (key == null) {
            throw new Exception("the encrypt key is null");
        }
        data = SALT + data;
        try {
            byte abyte0[] = key.getBytes(STRING_ENCODE);
            if (abyte0.length < 8) {// 将字符串Key转化为特定的字节数组,如果长度小于8字节,不可以进行加密,这是DES的要求
                throw new Exception("the key " + key + " length must large than 8 bytes");
            }

            byte abyte1[] = encrypt(data.getBytes(STRING_ENCODE), key.getBytes(STRING_ENCODE));
            return bytes2Hex(abyte1);
        } catch (Exception exception) {
            throw exception;
        }
    }

    /**
     * 解密
     *
     * @param data
     *            待加密字符串
     * @param key
     *            密钥(长度应大于8字节)
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        if (data == null) {
            return null;
        }
        if (key == null) {
            throw new Exception("the decrypt key is null");
        }

        try {
            byte srcData[] = hex2Bytes(data);
            byte abyte1[] = decrypt(srcData, key.getBytes(STRING_ENCODE));
            String result = new String(abyte1, STRING_ENCODE);
            if (result != null && result.length() < SALT.length()) {
                throw new Exception("the decrypt data " + data + " is not correct");
            }

            return result.substring(SALT.length());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 根据Key字节数组加密data字节数组的数据,返回加密后的字节数组
     *
     * @param data
     *            待加密的字节数组
     * @param key
     *            密钥数组,密钥数组必须大于等于8个字节
     * @return
     * @throws Exception
     */
    private static synchronized byte[] encrypt(byte data[], byte key[]) throws Exception {
        // 生成一个随机数源
        SecureRandom random = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec spec = new DESKeySpec(key);

        // 创建工密钥工程,生成密钥
        SecretKeyFactory factory = SecretKeyFactory.getInstance(DES);
        javax.crypto.SecretKey secretkey = factory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, secretkey, random);

        return cipher.doFinal(data);
    }

    private static synchronized byte[] decrypt(byte data[], byte key[]) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec spec = new DESKeySpec(key);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(DES);
        javax.crypto.SecretKey secretKey = factory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(2, secretKey, random);
        return cipher.doFinal(data);
    }

    private static String bytes2Hex(byte[] bytes) throws Exception {
        if (bytes == null) {
            throw new Exception("the byte array is null");
        }
        StringBuilder stringbuilder = new StringBuilder();
        String zero = "0";
        for (int j = 0; j < bytes.length; j++) {
            String s1 = Integer.toHexString(0xFF & bytes[j]);
            if (s1.length() == 1)
                stringbuilder.append(zero);
            stringbuilder.append(s1);
        }

        return stringbuilder.toString().toUpperCase();
    }

    private static byte[] hex2Bytes(String data) throws Exception {
        if (data == null) {
            throw new Exception("the data is null");
        }

        byte abyte0[] = data.getBytes();
        if (abyte0.length % 2 != 0) {
            throw new Exception("the decrypt data " + data + " is not correct");
        }

        byte[] result = new byte[abyte0.length / 2];
        for (int i = 0; i < abyte0.length / 2; i++)
            result[i] = (byte) Integer.parseInt(new String(abyte0, 2 * i, 2), 16);

        return result;
    }

    public static void main(String[] args) {
        String pwd = "123456789";
        String des_key = "yveshe-key";
        try {
            String encrypt = DesUtil.encrypt(pwd, des_key);
            System.out.println(encrypt);

            String decrypt = DesUtil.decrypt(encrypt, des_key);
            System.out.println(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
