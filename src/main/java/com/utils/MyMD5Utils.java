package com.utils;

import com.config.EncryptConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * MD5加密工具类（使用yml配置的固定盐值，取消自动生成盐）
 */
@Component
public class MyMD5Utils {
    // 注入加密配置
    private static EncryptConfig encryptConfig;

    @Autowired
    public void setEncryptConfig(EncryptConfig config) {
        MyMD5Utils.encryptConfig = config;
    }

    /**
     * MD5加盐加密（盐值来自yml配置）
     * @param plainText 明文密码
     * @return 加密后的密码（格式：加密密文）
     */
    public static String md5WithSalt(String plainText) {
        if (plainText == null || plainText.trim().isEmpty()) {
            return null;
        }
        // 从配置获取固定盐值
        String salt = encryptConfig.getMd5Salt();
        return md5(plainText + salt);
    }

    /**
     * 验证密码
     * @param plainText 明文密码
     * @param encryptedText 加密后的密码
     * @return 验证结果
     */
    public static boolean verify(String plainText, String encryptedText) {
        if (plainText == null || encryptedText == null) {
            return false;
        }
        // 从配置获取固定盐值
        String salt = encryptConfig.getMd5Salt();
        return md5(plainText + salt).equals(encryptedText);
    }

    /**
     * 基础MD5加密
     */
    private static String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(plainText.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }
}