package com.opensource.grip.http.api;

import com.google.common.base.Preconditions;
import okhttp3.Response;
import com.opensource.grip.http.config.Context;
import com.opensource.grip.http.config.HeadersConfig;
import com.opensource.grip.http.config.IConfig;
import com.opensource.grip.http.enums.MethodEnum;
import com.opensource.grip.http.logger.ResponseLog;

import java.util.Map;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public abstract class BaseHttpApi implements IApi<Response> {

    /**
     * 分隔符
     */
    private static final String SOLIDUS = "/";

    /**
     * 当前请求体
     */
    private Object currentBody;

    @Override
    public ResponseLog<Response> execute() {
        ResponseLog<Response> log = new ResponseLog<>();

        Api api = buildApi();

        // 访问接口的几个必要条件
        MethodEnum method = MethodEnum.findEnumByType(api.getMethod());

        HeadersConfig config;
        if (api.getHeaders().isEmpty()) {
            config = (HeadersConfig) getConfig();
        } else {
            Map<String, String> headers = api.getHeaders();
            config = new HeadersConfig();
            config.setRequestHeaders(headers);
        }

        // 构建完整的url
        String url = buildUrl(buildBaseUrl(config, api), api);

        long startTime = System.currentTimeMillis();
        Response response = method.getCommand().execute(config, url, api);
        log.setHeaders(config.getRequestHeaders()).setStartTime(startTime).setEndTime(System.currentTimeMillis())
                .setResponse(response).setUrl(url).setApi(api);
        return log;
    }

    @Override
    public IConfig getConfig() {
        return Context.configContainer.findConfig(new HeadersConfig());
    }

    /**
     * 构建api
     *
     * @return Api
     */
    protected abstract Api buildApi();

    /**
     * 获取当前请求体
     *
     * @return 请求体
     */
    protected abstract Object getBody();

    /**
     * 获取请求体
     *
     * @return 请求体
     */
    protected Object getCurrentBody() {
        return currentBody == null ? getBody() : currentBody;
    }

    /**
     * 构建基础url
     * 如果请求为get则没有？后面的内容
     *
     * @param headersConfig 配置
     * @param api           api
     * @return url
     */
    private String buildBaseUrl(HeadersConfig headersConfig, Api api) {
        Preconditions.checkNotNull(api, "api is null");
        String hostName = api.getHost() == null ? headersConfig.getHost() : api.getHost();
        String path = api.getPath();
        StringBuilder sb = new StringBuilder(hostName);
        if (path != null) {
            if (!hostName.endsWith(SOLIDUS)) {
                sb.append(SOLIDUS);
            }
            if (path.startsWith(SOLIDUS)) {
                path = path.replaceFirst(SOLIDUS, "");
            }
            sb.append(path);
        }
        return sb.toString();
    }

    /**
     * 获取完整url
     *
     * @param bashUrl 基础的url
     * @param api     api参数
     * @return 完整的url
     */
    private String buildUrl(String bashUrl, Api api) {
        Map<String, String> urlParams = api.getUrlParams();
        if (urlParams.isEmpty()) {
            return bashUrl;
        }
        StringBuilder sb = new StringBuilder();
        urlParams.forEach((key, value) -> sb.append(key).append("=").append(value).append("&"));
        if (sb.toString().endsWith("&")) {
            sb.replace(sb.length() - 1, sb.length(), "");
        }
        return bashUrl + "?" + sb;
    }
}
