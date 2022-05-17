package com.opensource.grip.http.container;

import com.opensource.grip.http.config.IConfig;

/**
 * 配置容器，约束接口
 *
 * @author wangmin
 * @date 2022/5/17 16:05
 */
public interface IConfigContainer {

    /**
     * 初始化配置容器
     */
    void init();

    /**
     * 添加属性
     *
     * @param <T> T
     * @param t   属性
     */
    <T> void addProperties(T t);

    /**
     * 获取所有的配置类
     *
     * @return 配置类
     */
    IConfig[] getConfigs();

    /**
     * 查询指定配置
     *
     * @param key key
     * @param <C> C
     * @return 配置
     */
    <C> IConfig findConfig(C key);

    /**
     * 判断配置容器是否为空
     *
     * @return true or false
     */
    boolean isEmpty();
}
