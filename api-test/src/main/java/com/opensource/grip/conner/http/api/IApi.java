package com.opensource.grip.conner.http.api;

import com.alibaba.fastjson.JSONObject;
import com.opensource.grip.conner.http.config.HeadersConfig;
import com.opensource.grip.conner.http.config.IConfig;
import com.opensource.grip.conner.http.logger.ResponseLog;

/**
 * api的访问接口
 * 执行各种类型的http请求调用
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public interface IApi<T> {

    /**
     * 执行api的调用
     *
     * @return 相应日志
     */
    ResponseLog<T> execute();

    /**
     * 获取配置类
     * 整体配置，初始化一次，同类共用
     *
     * @return 配置类
     */
    IConfig getConfig();

    /**
     * 设置配置类
     *
     * @param config 配置类
     */
    void setConfig(IConfig config);

    /**
     * 自定义请求体
     * <P>更新当前的请求体
     *
     * @param requestBody 请求体
     * @return IApi<T>
     */
    IApi<T> addRequestBody(Object requestBody);

    /**
     * 自定义请求体
     * <P>在现有的body体中添加一个请求体
     *
     * @param requestBody 请求体
     * @return IApi<T>
     */
    IApi<T> addRequestBody(JSONObject requestBody);

    /**
     * 自定义头部配置
     *
     * @param headersConfig 头部配置
     * @return IApi<T>
     */
    IApi<T> addHeadersConfig(HeadersConfig headersConfig);

    /**
     * 修改请求体
     *
     * @param key   键
     * @param value 值
     * @return IApi<T>
     */
    <Y> IApi<T> modifyBody(String key, Y value);

    /**
     * 设置页数量
     *
     * @param page 页数量
     * @return IApi<T>
     */
    IApi<T> page(Integer page);

    /**
     * 设置页大小
     *
     * @param size 页大小
     * @return IApi<T>
     */
    IApi<T> size(Integer size);

}
