package com.test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * 独立AES加密工具类（无需Spring依赖，直接运行）
 * 用于批量处理数据库中未加密的身份证号数据
 * 密钥与系统中AESUtils保持一致
 */
public class IdCardEncryptor {

    // 与系统中AESUtils一致的密钥（16位，AES-128），需手动配置
    private static final String AES_KEY = "2025a12a14sbzlxt"; // 替换为实际密钥
    // 加密算法配置（与系统保持一致）
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    /**
     * 对身份证号进行AES加密（与原有AESUtils逻辑一致）
     * @param plainIdCard 明文身份证号
     * @return 加密后Base64编码字符串
     */
    public static String encryptIdCard(String plainIdCard) {
        // 处理空值和非身份证号格式（避免无效加密）
        if (plainIdCard == null || plainIdCard.trim().isEmpty()) {
            return null;
        }
        // 简单校验身份证号格式（18位）
        if (plainIdCard.length() != 18) {
            System.out.println("警告：" + plainIdCard + " 不是有效的18位身份证号，已跳过");
            return null;
        }

        try {
            // 初始化密钥
            SecretKeySpec keySpec = new SecretKeySpec(AES_KEY.getBytes(), ALGORITHM);
            // 初始化加密器
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // 加密并转为Base64
            byte[] encryptedBytes = cipher.doFinal(plainIdCard.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 测试主方法：批量加密未处理的身份证号
    public static void main(String[] args) {
        // 模拟数据库中未加密的身份证号列表
        String[] plainIdCards = {
                "520422200303210001",
                "520422200303210002",
                "520422200303210003",
                "520422200303210004",
                "520422200303210005",
                "520422200303210006"
        };

        // 批量加密并输出结果
        System.out.println("===== 明文身份证号 → AES加密密文（Base64） =====");
        for (String plainId : plainIdCards) {
            String encryptedId = encryptIdCard(plainId);
            if (encryptedId != null) {
                System.out.println("明文：" + plainId + " → 密文：" + encryptedId);
            }
        }
    }
}