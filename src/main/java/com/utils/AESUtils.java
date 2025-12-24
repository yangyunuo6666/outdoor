package com.utils;

import com.config.EncryptConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES加密工具类（从yml配置读取密钥）
 */
@Component
public class AESUtils {
    // 注入加密配置
    private static EncryptConfig encryptConfig;

    @Autowired
    public void setEncryptConfig(EncryptConfig config) {
        AESUtils.encryptConfig = config;
    }

    // 加密
    public static String encrypt(String content) {
        if (content == null || content.trim().isEmpty()) {
            return null;
        }
        try {
            // 从配置获取AES密钥
            String aesKey = encryptConfig.getAesKey();
            SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("AES加密失败", e);
        }
    }

    // 解密
    public static String decrypt(String encryptedContent) {
        if (encryptedContent == null || encryptedContent.trim().isEmpty()) {
            return null;
        }
        try {
            // 从配置获取AES密钥
            String aesKey = encryptConfig.getAesKey();
            SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedContent));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("AES解密失败", e);
        }
    }
}