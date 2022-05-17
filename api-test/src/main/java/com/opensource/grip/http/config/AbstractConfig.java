package com.opensource.grip.http.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象配置类
 *
 * @author wangmin
 * @date 2022/5/16 17:00
 */
public abstract class AbstractConfig implements IConfig {

    private static final Map<String, IConfig> CONFIG_HASH_MAP = new HashMap<>(16);

    @Override
    public boolean init() {
        IConfig iConfig = addConfig();
        CONFIG_HASH_MAP.put(iConfig.getClass().getSimpleName(), iConfig);
        Context.CONFIG = this;
        return Context.CONFIG.isEmpty();
    }

    @Override
    public IConfig currentConfig() {
        return this;
    }

    @Override
    public <T extends IConfig> T getChildrenConfig(T t) {
        Map.Entry<String, IConfig> entry = CONFIG_HASH_MAP.entrySet().stream()
                .filter(e -> t.getClass().getSimpleName().equals(e.getKey()))
                .findFirst().orElse(null);
        return (T) entry.getValue();
    }

    @Override
    public boolean isEmpty() {
        return CONFIG_HASH_MAP.isEmpty();
    }

    /**
     * 添加配置
     * 子类实现此方法
     *
     * @return 返回子类配置
     */
    protected abstract IConfig addConfig();
}
