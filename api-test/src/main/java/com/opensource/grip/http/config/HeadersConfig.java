package com.opensource.grip.http.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 标头配置类
 *
 * @author wangmin
 * @date 2021/11/17 5:30 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HeadersConfig extends AbstractConfig {

    private String host;
    private String ip;
    private Map<String, String> requestHeaders = new HashMap<>();

    @Override
    protected HeadersConfig addConfig() {
        return this;
    }

    /**
     * 设置请求头中的referer
     *
     * @param referer referer
     * @return HeadersConfig
     */
    public HeadersConfig referer(String referer) {
        requestHeaders.put("Referer", referer);
        return this;
    }

    /**
     * 设置请求头中的token
     *
     * @param token token
     * @return HeadersConfig
     */
    public HeadersConfig token(String token) {
        requestHeaders.put("token", token);
        return this;
    }

    /**
     * 设置请求头中的cookie
     *
     * @param cookie cookie
     * @return HeadersConfig
     */
    public HeadersConfig cookie(String cookie) {
        requestHeaders.put("Cookie", cookie);
        return this;
    }

    /**
     * 设置请求头中的secretKey
     *
     * @param secretKey 一串加密字符串
     * @return HeadersConfig
     */
    public HeadersConfig secretKey(String secretKey) {
        requestHeaders.put("SecretKey", secretKey);
        return this;
    }

    /**
     * 设置请求头中其他的属性
     *
     * @param key   键
     * @param value 值
     * @return HeadersConfig
     */
    public HeadersConfig other(String key, String value) {
        requestHeaders.put(key, value);
        return this;
    }


    /**
     * 设置host
     *
     * @param host host
     * @return HeadersConfig
     */
    public HeadersConfig host(String host) {
        this.host = host;
        return this;
    }

    /**
     * 设置ip
     *
     * @param ip ip
     * @return HeadersConfig
     */
    public HeadersConfig ip(String ip) {
        this.ip = ip;
        return this;
    }
}
