package com.opensource.grip.http.api;

import com.opensource.grip.http.config.IConfig;
import com.opensource.grip.http.logger.ResponseLog;

/**
 * @author wangmin
 * @date 2022/5/16 17:52
 */
public interface IApi<T> {

    /**
     * 执行api的调用
     *
     * @return 自定义的返回值类型
     */
    ResponseLog<T> execute();

    /**
     * 获取配置
     * 整体配置，初始化一次，同类共用
     *
     * @return 配置
     */
    IConfig getConfig();
}
