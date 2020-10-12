package com.myIsoland.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 读取Sms相关配置
 *
 * @author xuyong
 */
@Configuration
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {

    /** 用户名 */
    private static String username;
    /** 密钥id */
    private static String secretId;
    /** 密钥key */
    private static String secretKey;
    /** sdkAppId */
    private static String appId;
    /** 签名内容 */
    private static String sign;
    /** 短信模板id */
    private static String templateID;


    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        SmsConfig.username = username;
    }

    public static String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        SmsConfig.secretId = secretId;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        SmsConfig.secretKey = secretKey;
    }

    public static String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        SmsConfig.sign = sign;
    }

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        SmsConfig.appId = appId;
    }

    public static String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        SmsConfig.templateID = templateID;
    }
}
