package com.opensource.grip.conner.http.api;

import com.opensource.grip.conner.http.enums.MethodEnum;
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

    private static final String SOLIDUS = "/";
    private static final String WITH = "&";

    private final Map<String, String> partParams = new HashMap<>();
    private final Map<String, String> urlParams = new HashMap<>();
    private final Map<String, String> partFiles = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();
    private final String contentType;
    private final boolean ignoreSsl;
    private final String method;
    private final MethodEnum methodEnum;
    private final String baseUrl;
    private final String path;
    private final String host;
    private final String sign;
    private final Integer port;

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
        this.methodEnum = builder.methodEnum;
        this.host = builder.host;
        this.path = builder.path;
        this.baseUrl = builder.baseUrl;
        this.port = builder.port;
        this.sign = builder.sign;
    }

    /**
     * 返回完整的url
     *
     * @return url
     */
    public String getUrl() {
        return buildFullUrl();
    }

    public static class Builder {
        private final Map<String, String> partParams = new HashMap<>();
        private final Map<String, String> urlParams = new HashMap<>();
        private final Map<String, String> partFiles = new HashMap<>();
        private final Map<String, String> headers = new HashMap<>();
        private String contentType = "application/json";
        private boolean ignoreSsl = true;
        private Object bodyContent;
        private MethodEnum methodEnum;
        private String method;
        private String path;
        private String baseUrl;
        private String host;
        private Integer port;
        private String sign;

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

        public Builder method(MethodEnum methodEnum) {
            this.methodEnum = methodEnum;
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

        public Builder port(Integer port) {
            this.port = port;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder sign(String sign) {
            this.sign = sign;
            return this;
        }

        public Api build() {
            return new Api(this);
        }
    }

    /**
     * 构建完整url
     * <P>构建POST、GET请求的完整url
     *
     * @return url 完整的url
     */
    private String buildFullUrl() {
        StringBuilder sb = new StringBuilder(baseUrl);
        // 组合成：http://xxx.xxx.xx/xxx/xxx
        String newPath = path;
        if (newPath != null) {
            if (!baseUrl.endsWith(SOLIDUS)) {
                sb.append(SOLIDUS);
            }
            if (newPath.startsWith(SOLIDUS)) {
                newPath = newPath.replaceFirst(SOLIDUS, "");
            }
            sb.append(newPath);
        }

        // 组合成：http://xxx.xxx.xx/xxx/xxx?xxx=11&sss=111
        if (urlParams.isEmpty()) {
            return sb.toString();
        }
        StringBuilder pathSb = new StringBuilder();
        urlParams.forEach((key, value) -> pathSb.append(key).append("=").append(value).append(WITH));
        if (pathSb.toString().endsWith(WITH)) {
            pathSb.replace(pathSb.length() - 1, pathSb.length(), "");
        }
        String url = sb + "?" + pathSb;
        return sign == null ? url : url + "&" + sign;
    }
}
