package com.opensource.grip.conner.http.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 头部配置类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class HeadersConfig extends AbstractConfig {

    private String baseUrl;
    private String host;
    private Integer port;
    private Map<String, String> requestHeaders = new HashMap<>();

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
     * 设置基础的url
     *
     * @param baseUrl host的名字
     * @return HeadersConfig
     */
    public HeadersConfig baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
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
     * @param port ip
     * @return HeadersConfig
     */
    public HeadersConfig port(Integer port) {
        this.port = port;
        return this;
    }
}
