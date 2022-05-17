package com.opensource.grip.http.config;

/**
 * 抽象配置类
 *
 * @author wangmin
 * @date 2022/5/17 13:05
 */
public abstract class AbstractConfig implements IConfig {
    private IConfig config;

    @Override
    public void setConfig(IConfig config) {
        this.config = config;
    }

    @Override
    public IConfig currentConfig() {
        return this.config;
    }

    @Override
    public boolean isEmpty() {
        return config == null;
    }
}
