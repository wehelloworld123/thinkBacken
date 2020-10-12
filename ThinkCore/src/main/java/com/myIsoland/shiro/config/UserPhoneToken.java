package com.myIsoland.shiro.config;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class UserPhoneToken implements HostAuthenticationToken,RememberMeAuthenticationToken{
    // 手机号码
    private String phone;
    private boolean rememberMe;
    private String host;

    /**
     * 重写getPrincipal方法
     */
    public Object getPrincipal() {
        return phone;
    }

    /**
     * 重写getCredentials方法
     */
    public Object getCredentials() {
        return phone;
    }

    public UserPhoneToken() { this.rememberMe = false; }

    public UserPhoneToken(String phone) { this(phone, false, null); }

    public UserPhoneToken(String phone, boolean rememberMe) { this(phone, rememberMe, null); }

    public UserPhoneToken(String phone, boolean rememberMe, String host) {
        this.phone = phone;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
