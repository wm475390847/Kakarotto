package com.opensource.grip.conner.http.api;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.http.config.Context;
import com.opensource.grip.conner.http.config.HeadersConfig;
import com.opensource.grip.conner.http.config.IConfig;
import com.opensource.grip.conner.http.logger.ResponseLog;
import okhttp3.Response;

/**
 * 调用http请求的基类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public abstract class BaseHttpApi implements IApi<Response> {

    /**
     * 当前请求体
     */
    private Object currentBody;
    private HeadersConfig headersConfig;

    @Override
    public ResponseLog<Response> execute() {
        Api api = buildApi();
        HeadersConfig config = headersConfig == null ? (HeadersConfig) getConfig() : headersConfig;
        api.setHeaderConfig(config);

        Response response = api.getMethodEnum().getCommand().execute(api);

        long startTime = System.currentTimeMillis();

        ResponseLog<Response> log = new ResponseLog<>();
        log.setEndTime(System.currentTimeMillis())
                .setStartTime(startTime)
                .setResponse(response)
                .setApi(api);
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
    public IApi<Response> addHeadersConfig(HeadersConfig headersConfig) {
        this.headersConfig = headersConfig;
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
}
