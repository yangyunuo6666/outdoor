package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 加密配置读取类
 * 读取application.yml中的encrypt节点配置
 */
@Configuration
@ConfigurationProperties(prefix = "encrypt")
public class EncryptConfig {
    // MD5固定盐值
    private String md5Salt;
    // AES密钥
    private String aesKey;

    // getter/setter方法
    public String getMd5Salt() {
        return md5Salt;
    }

    public void setMd5Salt(String md5Salt) {
        this.md5Salt = md5Salt;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}