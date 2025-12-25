package com.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 独立MD5加密工具类（无需Spring依赖，直接运行）
 * 盐值与yml配置保持一致
 */
public class passwdTest1 {

    // 与application.yml中一致的md5-salt，手动配置
    private static final String MD5_SALT = "M2025D125140";

    /**
     * 明文 + 固定盐值 生成MD5密文（与原有MyMD5Utils逻辑一致）
     * @param plainText 明文密码
     * @return 32位MD5密文
     */
    public static String md5WithSalt(String plainText) {
        // 处理空值
        if (plainText == null || plainText.trim().isEmpty()) {
            plainText = "";
        }

        // 明文拼接盐值（与原有逻辑一致，确保加密结果相同）
        String encryptSource = plainText + MD5_SALT;

        try {
            // MD5加密核心逻辑
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(encryptSource.getBytes()); // 拼接后加密
            byte[] digestBytes = md.digest();

            // 转换为32位十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digestBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 测试主方法
    public static void main(String[] args) {
        // 定义需要加密的明文密码
        String[] plainPasswords = {
                "123456",
                "admin888",
                "root"
        };

        // 批量生成密文
        System.out.println("===== 明文密码 → MD5加密密文 =====");
        for (String plainPwd : plainPasswords) {
            String encryptPwd = md5WithSalt(plainPwd);
            System.out.println("明文：" + plainPwd + " → 密文：" + encryptPwd);
        }
    }
}