package com.opensource.grip.http.api;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Api类
 * <P>接口实现调用所需要的属性
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
@Getter
public class Api {
    private final Map<String, String> partParams = new HashMap<>();
    private final Map<String, String> urlParams = new HashMap<>();
    private final Map<String, String> partFiles = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();
    private final String contentType;
    private final boolean ignoreSsl;
    private final String method;
    private final String path;
    private final String host;
    private final String ip;

    @Setter
    private Object bodyContent;

    public Api(Builder builder) {
        this.partParams.putAll(builder.partParams);
        this.urlParams.putAll(builder.urlParams);
        this.partFiles.putAll(builder.partFiles);
        this.headers.putAll(builder.headers);
        this.contentType = builder.contentType;
        this.bodyContent = builder.bodyContent;
        this.ignoreSsl = builder.ignoreSsl;
        this.method = builder.method;
        this.host = builder.host;
        this.path = builder.path;
        this.ip = builder.ip;
    }

    public static class Builder {
        private final Map<String, String> partParams = new HashMap<>();
        private final Map<String, String> urlParams = new HashMap<>();
        private final Map<String, String> partFiles = new HashMap<>();
        private final Map<String, String> headers = new HashMap<>();
        private String contentType = "application/json";
        private boolean ignoreSsl = true;
        private Object bodyContent;
        private String method;
        private String host;
        private String path;
        private String ip;

        public Builder headers(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder partParams(Map<String, String> partParams) {
            this.partParams.putAll(partParams);
            return this;
        }

        public Builder partParam(String key, String value) {
            this.partParams.put(key, value);
            return this;
        }

        public Builder partFile(String key, String filePath) {
            this.partFiles.put(key, filePath);
            return this;
        }

        public Builder partFiles(Map<String, String> partFiles) {
            this.partFiles.putAll(partFiles);
            return this;
        }

        public Builder urlParam(String key, Object object) {
            if (object != null) {
                this.urlParams.put(key, String.valueOf(object));
            }
            return this;
        }

        public Builder bodyContent(Object bodyContent) {
            this.bodyContent = bodyContent;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder ignoreSsl(boolean ignoreSsl) {
            this.ignoreSsl = ignoreSsl;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Api build() {
            return new Api(this);
        }
    }
}
