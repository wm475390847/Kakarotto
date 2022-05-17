package com.opensource.grip.http.config;

/**
 * @author wangmin
 * @date 2022/5/16 17:00
 */
public interface IConfig {
    /**
     * 初始化配置
     *
     * @return 是否成功
     */
    boolean init();

    /**
     * 获取当前配置
     *
     * @return 配置
     */
    IConfig currentConfig();

    /**
     * 获取子类配置
     *
     * @param t 子类类型
     * @return 子类
     */
    <T extends IConfig> T getChildrenConfig(T t);

    /**
     * 配置为空返回true
     * 配置不为空返回false
     *
     * @return 是否为空
     */
    boolean isEmpty();
}
