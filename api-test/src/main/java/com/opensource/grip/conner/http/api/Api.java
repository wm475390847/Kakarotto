package com.opensource.grip.conner.http.api;

import com.opensource.grip.conner.http.config.HeadersConfig;
import com.opensource.grip.conner.http.enums.MethodEnum;
import com.shuwen.openapi.gateway.util.SignHelperV2;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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
    private final Map<String, String> sign = new HashMap<>();
    private final String contentType;
    private final boolean ignoreSsl;
    private final boolean isSign;
    private final MethodEnum methodEnum;
    private final String path;
    private final String method;
    private final String url;

    private String host;
    private Integer port;
    private String baseUrl;
    private Object requestBody;

    public Api(Builder builder) {
        this.partParams.putAll(builder.partParams);
        this.urlParams.putAll(builder.urlParams);
        this.partFiles.putAll(builder.partFiles);
        this.headers.putAll(builder.headers);
        this.sign.putAll(builder.sign);
        this.isSign = builder.isSign;
        this.contentType = builder.contentType;
        this.requestBody = builder.requestBody;
        this.methodEnum = builder.methodEnum;
        this.ignoreSsl = builder.ignoreSsl;
        this.baseUrl = builder.baseUrl;
        this.url = builder.url;
        this.method = builder.method;
        this.host = builder.host;
        this.path = builder.path;
        this.port = builder.port;
    }

    /**
     * 将通用头部配置更新到api类中，如果没有就不进行更新
     *
     * @param config 通用头部配置类
     */
    public void setHeaderConfig(HeadersConfig config) {
        if (config != null) {
            // 如果config中host不为空并且api中host为空则使用config中的host，否则使用api中的host
            if (config.getHost() != null && StringUtils.isEmpty(host)) {
                this.host = config.getHost();
            }
            // 如果config中基础url不为空并且api中基础url为空则使用config中的基础url，否则使用api中的基础url
            if (config.getBaseUrl() != null && StringUtils.isEmpty(baseUrl)) {
                this.baseUrl = config.getBaseUrl();
            }
            // 如果config中port不为空并且api中port为空则使用config中的port，否则使用api中的port
            if (config.getPort() != null && port == null) {
                this.port = config.getPort();
            }
            // 如果config中的加签参数不为空并且api中允许加签则进行加签处理，否则不加签
            if (!config.getSign().isEmpty() && isSign) {
                this.sign.putAll(config.getSign());
            }

            if (!config.getRequestHeaders().isEmpty()) {
                this.headers.putAll(config.getRequestHeaders());
            }
        }
    }

    /**
     * 设置请求体
     *
     * @param requestBody 请求体
     */
    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    /**
     * 返回完整的url
     *
     * @return url
     */
    public String getUrl() {
        return url == null ? buildFullUrl() : url;
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
            if (!sign.isEmpty()) {
                String signUrl = addSign(sign, null);
                return sb + "?" + signUrl;
            } else {
                return sb.toString();
            }
        }

        if (!sign.isEmpty()) {
            String signUrl = addSign(sign, urlParams);
            return sb + "?" + signUrl;
        } else {
            StringBuilder pathSb = new StringBuilder();
            urlParams.forEach((key, value) -> pathSb.append(key).append("=").append(value).append(WITH));
            if (pathSb.toString().endsWith(WITH)) {
                pathSb.replace(pathSb.length() - 1, pathSb.length(), "");
            }
            return sb + "?" + pathSb;
        }
    }

    /**
     * 加签
     *
     * @param sign 鉴权内容
     * @param map  get的请求参数
     * @return 加签后的url
     */
    private String addSign(Map<String, String> sign, Map<String, String> map) {
        String signUrl = null;
        for (Map.Entry<String, String> entry : sign.entrySet()) {
            signUrl = SignHelperV2.getSignUrl(entry.getKey(), entry.getValue(), map);
        }
        return signUrl;
    }

    public static class Builder {
        private final Map<String, String> partParams = new HashMap<>();
        private final Map<String, String> urlParams = new HashMap<>();
        private final Map<String, String> partFiles = new HashMap<>();
        private final Map<String, String> headers = new HashMap<>();
        private final Map<String, String> sign = new HashMap<>();
        private String contentType = "application/json";
        private boolean ignoreSsl = true;
        private boolean isSign = true;
        private Object requestBody;
        private MethodEnum methodEnum;
        private String method;
        private String path;
        private String url;
        private String baseUrl;
        private String host;
        private Integer port;

        public Builder headers(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder sign(String ak, String sk) {
            this.sign.put(ak, sk);
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

        public Builder requestBody(Object requestBody) {
            this.requestBody = requestBody;
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

        public Builder isSign(boolean isSign) {
            this.isSign = isSign;
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

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Api build() {
            return new Api(this);
        }
    }
}
