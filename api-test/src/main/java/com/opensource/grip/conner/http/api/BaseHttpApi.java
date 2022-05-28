package com.opensource.grip.conner.http.api;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.opensource.grip.conner.http.config.Context;
import com.opensource.grip.conner.http.config.HeadersConfig;
import com.opensource.grip.conner.http.config.IConfig;
import com.opensource.grip.conner.http.enums.MethodEnum;
import com.opensource.grip.conner.http.logger.ResponseLog;
import okhttp3.Response;

import java.util.Map;

/**
 * 调用http请求的基类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public abstract class BaseHttpApi implements IApi<Response> {

    private static final String SOLIDUS = "/";
    private static final String WITH = "&";

    /**
     * 当前请求体
     */
    private Object currentBody;

    @Override
    public ResponseLog<Response> execute() {
        ResponseLog<Response> log = new ResponseLog<>();

        Api api = buildApi();

        MethodEnum method = MethodEnum.findEnumByType(api.getMethod());

        HeadersConfig config = api.getHeaders().isEmpty() ? (HeadersConfig) getConfig()
                : new HeadersConfig().setRequestHeaders(api.getHeaders());

        String url = buildUrl(buildBaseUrl(config, api), api);

        long startTime = System.currentTimeMillis();
        Response response = method.getCommand().execute(config, url, api);
        log.setConfig(config).setApi(api).setStartTime(startTime).setEndTime(System.currentTimeMillis())
                .setResponse(response).setUrl(url);
        return log;
    }

    @Override
    public void setConfig(IConfig config) {
        Context.configContainer.setConfig(config);
    }

    @Override
    public IConfig getConfig() {
        if (Context.configContainer == null) {
            return null;
        }
        return Context.configContainer.findConfig(new HeadersConfig());
    }

    @Override
    public IApi<Response> addRequestBody(Object body) {
        this.currentBody = body;
        return this;
    }

    @Override
    public IApi<Response> addRequestBody(JSONObject requestBody) {
        JSONObject currentBody = (JSONObject) getCurrentBody();
        currentBody.putAll(requestBody);
        this.currentBody = currentBody;
        return this;
    }

    @Override
    public <Y> IApi<Response> modifyBody(String key, Y value) {
        JSONObject currentBody = (JSONObject) getCurrentBody();
        currentBody.put(key, value);
        this.currentBody = currentBody;
        return this;
    }

    @Override
    public IApi<Response> page(Integer pageIndex) {
        setPage(pageIndex);
        return this;
    }

    @Override
    public IApi<Response> size(Integer pageSize) {
        setSize(pageSize);
        return this;
    }

    /**
     * 设置页码
     * <P>有需要的子类实现
     *
     * @param page 页码
     */
    protected void setPage(Integer page) {

    }

    /**
     * 设置页大小
     * <P>有需要的子类实现
     *
     * @param size 大小
     */
    protected void setSize(Integer size) {

    }

    /**
     * 构建api类，api类中包含了调用http请求的参数
     * <P>POST: new Api.Builder().path("").method("POST").contentType("application/json").bodyContent({@link #getCurrentBody()}).build();
     * <p>GET: new Api.Builder().path("").method("GET").contentType("application/json").urlParam("a", a).urlParam("b", b).build();
     *
     * @return Api类
     */
    protected abstract Api buildApi();

    /**
     * 构建请求体，body中包含请求所需要的参数
     * <p>Object is null: return null; Object is JSONObject: return JSONObject; Object is Array: return Array
     *
     * @return 请求体
     */
    protected abstract Object buildBody();

    /**
     * 获取当前请求体
     * <P>当使用自定义的请求体时，更新类中包含的变量{@link #currentBody}，此变量的优先级高于子类构建的请求体{@link #buildBody()}
     *
     * @return 当前请求体
     */
    protected Object getCurrentBody() {
        return currentBody == null ? buildBody() : currentBody;
    }

    /**
     * 构建基础url
     * <P>POST请求则为完整的url，GET请求则为?前面的url
     *
     * @param config 头部配置类
     * @param api    Api类
     * @return url 基础的url
     */
    private String buildBaseUrl(HeadersConfig config, Api api) {
        Preconditions.checkNotNull(api, "api is null");
        String hostName = api.getHost() == null ? config.getHost() : api.getHost();
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
     * 构建完整url
     *
     * @param bashUrl 基础的url
     * @param api     Api类
     * @return 完整的url
     */
    private String buildUrl(String bashUrl, Api api) {
        Map<String, String> urlParams = api.getUrlParams();
        if (urlParams.isEmpty()) {
            return bashUrl;
        }
        StringBuilder sb = new StringBuilder();
        urlParams.forEach((key, value) -> sb.append(key).append("=").append(value).append(WITH));
        if (sb.toString().endsWith(WITH)) {
            sb.replace(sb.length() - 1, sb.length(), "");
        }
        return bashUrl + "?" + sb;
    }
}
