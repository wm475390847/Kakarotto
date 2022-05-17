package com.opensource.grip.http.config;

/**
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public interface IConfig {

    /**
     * 获取当前配置
     *
     * @return 配置
     */
    IConfig currentConfig();


    void setConfig(IConfig config);

    /**
     * 配置为空返回true
     * 配置不为空返回false
     *
     * @return 是否为空
     */
    boolean isEmpty();
}
