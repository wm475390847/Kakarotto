package com.opensource.grip.http.container;

import com.google.common.base.Preconditions;
import com.opensource.grip.http.config.Context;
import com.opensource.grip.http.config.IConfig;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 配置容器基类
 *
 * @author wangmin
 * @date 2022/5/17 16:19
 */
public abstract class AbstractConfigContainer implements IConfigContainer {

    private final Map<String, IConfig> configMap = new HashMap<>(16);

    @Override
    public void init() {
        Context.configContainer = this;
    }

    /**
     * 添加属性
     *
     * @param t   属性
     * @param <T> T
     */
    @Override
    public abstract <T> void addProperties(T t);

    @Override
    public IConfig[] getConfigs() {
        List<IConfig> configs = new LinkedList<>(configMap.values());
        return configs.toArray(new IConfig[0]);
    }

    @Override
    public <C> IConfig findConfig(C key) {
        Preconditions.checkArgument(key != null, "获取指定配置key不能为空");
        Map.Entry<String, IConfig> entry = configMap.entrySet().stream()
                .filter(e -> e.getKey().equals(key.getClass().getSimpleName()))
                .findFirst().orElse(null);
        Preconditions.checkArgument(entry != null, "获取指定配置失败");
        return entry.getValue();
    }

    @Override
    public boolean isEmpty() {
        return configMap.isEmpty();
    }

    /**
     * 添加配置类
     *
     * @param config 配置类
     */
    protected void addConfig(IConfig config) {
        configMap.put(config.getClass().getSimpleName(), config);
    }
}
